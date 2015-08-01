(defproject korma-example "0.1.0-SNAPSHOT"
            :description "TODO"
            :url "TODO"
            :license {:name "TODO: Choose a license"
                      :url  "http://choosealicense.com/"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [korma "0.4.2-SNAPSHOT"]
                           [org.postgresql/postgresql "9.2-1002-jdbc4"]]
            :main korma-example
            :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.7"]]
                             :source-paths ["dev"]}})
