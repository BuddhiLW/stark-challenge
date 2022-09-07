(defproject stark-challenge "0.1.0-SNAPSHOT"
  :description "Stark API integration - generate and process payments"
  :url "http://example.com/FIXME"

  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/math.numeric-tower "0.0.5"]
                 [org.clojure/data.json "2.4.0"]
                 [ring/ring-core "1.9.5"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [aleph "0.5.0"]
                 [metosin/reitit "0.5.18"]
                 [metosin/muuntaja "0.6.8"]
                 [mount "0.1.16"]
                 [com.xtdb/xtdb-core "1.21.0"]
                 [eigenhombre/namejen "0.1.23"]
                 [clojure.java-time "0.3.3"]
                 [cadastro-de-pessoa "0.4.0"]
                 [jarohen/chime "0.3.3"]
                 [starkbank/sdk "2.5.2"]]

  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :resource-paths ["resources"]

  :main ^:skip-aot stark-challenge.core
  :target-path "target/%s"
;;   :profiles {:uberjar {:aot :all
;;                        :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
;; :uberjar {:omit-source true
;;              :aot :all
;;              :uberjar-name "starbank-challenge.jar"
;;              :source-paths ["env/prod/clj"]
;;              :resource-paths ["env/prod/resources"]}

  :profiles
  {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})

   ;; :dev           [:project/dev :profiles/dev]
   ;; :test          [:project/dev :project/test :profiles/test]

   ;; :project/dev  {:jvm-opts ["-Dconf=dev-config.edn"]
   ;;                :dependencies [[pjstadig/humane-test-output "0.10.0"]
   ;;                               [prone "2020-01-17"]
   ;;                               [ring/ring-devel "1.8.2"]
   ;;                               [ring/ring-mock "0.4.0"]]
   ;;                :plugins      [[com.jakemccrary/lein-test-refresh "0.24.1"]
   ;;                               [jonase/eastwood "0.3.5"]]

   ;;                :source-paths ["env/dev/clj"]
   ;;                :resource-paths ["env/dev/resources"]
   ;;                :repl-options {:init-ns user
   ;;                               :timeout 120000}
   ;;                :injections [(require 'pjstadig.humane-test-output)
   ;;                             (pjstadig.humane-test-output/activate!)]}
   ;; :project/test {:jvm-opts ["-Dconf=test-config.edn"]
   ;;                :resource-paths ["env/test/resources"]}})
