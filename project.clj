(defproject stark-challenge "0.1.0-SNAPSHOT"
  :description "Stark API integration - generate and process payments"
  :url "http://example.com/FIXME"

  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/math.numeric-tower "0.0.5"]
                 [ring/ring-core "1.9.5"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [clj-time "0.15.2"]
                 [starkbank/sdk "2.5.2"]]

  :main ^:skip-aot stark-challenge.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
