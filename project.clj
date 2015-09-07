(defproject contrib-patch "0.1.0-SNAPSHOT"
  :description "Download GitHub contributions as patches."
  :url "https://github.com/mneise/contrib-patch"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [clj-http "2.0.0"]
                 [org.clojure/data.json "0.2.6"]]
  :main contrib-patch.core)
