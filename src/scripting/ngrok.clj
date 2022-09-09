(ns scripting.ngrok
  (:require [clojure.java.shell :as shell])
  (:gen-class))

(defn strip [coll chars]
  (apply str (remove #((set chars) %) coll)))

;; (def stop-ngrok-process (ngrok/start {:port 8844 :protocol "http" :region "sa" :bin-path "ngrok"}))
;; (System/getProperty "ngrok-tunnel-url")

(defn -main
  []
  (def ngrok-url (-> (shell/sh "./scripts/ngrok-url.sh")
                     (:out)
                     (strip "\n")))
  (clojure.pprint/pprint (str "ngrok url: " ngrok-url)))
