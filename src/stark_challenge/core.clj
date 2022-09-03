(ns stark-challenge.core
  (:require [ring.adapter.jetty :as j]
            [clojure.data.json :as json]
            [starkbank.settings :as sset]
            [stark-challenge.user :as proj-setup])
  (:gen-class))

(defn webhook
  "Checks if the webhook is valid and handles it."
  [request]
  (let [headers (:headers request)
        payload (slurp (:body request))
        parsed-payload  (json/read-json payload keyword)]
;; (case (webhook-signature/check-payload-signature GITHUB_WEBHOOK_SECRET x-hub-signature payload)
;;   ::webhook-signature/missing-signature {:status 400 :body "x-hub-signature header is missing"}
;;   ::webhook-signature/wrong-signature {:status 401 :body "x-hub-signature does not match"}
      ;; process your webhook here
    {:status 200 :body (str "200 OK: \n parsed-payload:" parsed-payload "\nheaders: " headers)}))

(defn handler [request]
  {:status 200
   :headers {"Content-type" "text/pain"}
   :body (str request)})

(defn -main
  [& args]
  (sset/user proj-setup/buddhilw)
  (j/run-jetty handler {:port 3130}))
