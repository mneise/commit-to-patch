# contrib-patch

Download all your contributions to a GitHub project as patches using the GitHub API.

You can pass the following options to the library:

| Short Option | Long Option             | Description |
| -------------| ----------------------- | ----------- |
| -o           | --owner OWNER           | Owner of the repository. |
| -r           | --repository REPOSITORY | The repository to get the patches from. |
| -a           | --author AUTHOR         | GitHub login or email address by which to filter by commit author. |
| -s           | --since DATE            | Only commits after this date will be returned. This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ. |
| -u           | --until DATE            | Only commits before this date will be returned. This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ. |

The following example will download all commits to the project `clojure/clojurescript` made by `username` to the current directory.

```
$ lein run -- -o clojure -r clojurescript -a username
```

# LICENSE

Copyright © 2015 Maria Geller

Distributed under the MIT License.
