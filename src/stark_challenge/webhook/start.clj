(ns stark-challenge.webhook.start
  (:require [starkbank.webhook :as swh]))

;; url: https://cdbc-2804-431-e7c6-ebff-5e62-a7ec-6c56-f3dc.sa.ngrok.io
(def webhook
  (swh/create
   {:url "https://b79b-2804-431-e7c6-ebff-5e62-a7ec-6c56-f3dc.sa.ngrok.io"
    :subscriptions ["transfer" "invoice"]}))

(comment
  (def webhooks (swh/query))

  (println webhook)
  (println (map :url webhooks))

  (:url webhook)
  (slurp "https://b79b-2804-431-e7c6-ebff-5e62-a7ec-6c56-f3dc.sa.ngrok.io")

  (swh/get "4834065156931584"))
;; => {:id "4834065156931584",
;;     :url "https://b79b-2804-431-e7c6-ebff-5e62-a7ec-6c56-f3dc.sa.ngrok.io",
;;     :subscriptions ["invoice" "transfer"]}

(comment
  ;; Example in documentation: https://github.com/starkbank/sdk-clojure#process-webhook-events
  (def response (listen)); this is the function you made to get the events posted to your webhook
  (def event
    (starkbank.event/parse
     (:content response)
     (:Digital-Signature (:headers response)))))

(println event)
