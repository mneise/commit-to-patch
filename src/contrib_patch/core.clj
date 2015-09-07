(ns contrib-patch.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.string :as string])
  (:gen-class))

(def cli-options
  [["-o" "--owner OWNER" "Owner of the repository."]
   ["-r" "--repository REPOSITORY" "The repository to get the patches from."]
   ["-a" "--author AUTHOR" "GitHub login or email address by which to filter by commit author."]
   ["-s" "--since DATE" "Only commits after this date will be returned. This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ."]
   ["-u" "--until DATE" "Only commits before this date will be returned. This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ."]
   ["-h" "--help"]])

(def commit-url "https://api.github.com/repos/%s/%s/commits")

(defn download-patches [options]
  (let [{:keys [owner repository author since until]} options
        url (format commit-url owner repository)
        query-params (merge {:author author}
                            (select-keys options [:since :until]))
        commits (-> (client/get url {:as :json
                                     :query-params query-params})
                    :body
                    (json/read-str :key-fn keyword))]
    (doseq [{:keys [html_url sha]} commits]
      (spit (str sha ".patch")
            (:body (client/get (str html_url ".patch") {:as :json}))))))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

(defn exit [status & msgs]
  (doseq [msg msgs]
    (println msg))
  (System/exit status))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) (exit 0 summary)
      (not (contains? options :owner)) (exit 1 "Please specify a repository owner." summary)
      (not (contains? options :repository)) (exit 1 "Please specify a repository." summary)
      (not (contains? options :author)) (exit 1 "Please specify an author." summary)
      errors (exit 1 (error-msg errors)))
    (download-patches options)))













