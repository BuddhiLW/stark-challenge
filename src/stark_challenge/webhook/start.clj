(ns stark-challenge.webhook.start
  (:require [starkbank.webhook :as swh]
            [scripting.ngrok :as ngrok]))

;; TODO: create env variable with pipedream's generated-URL
(defn -main []
  (def webhook
    (swh/create
     {:url ngrok/ngrok-url
      :subscriptions ["invoice" "transfer"]})))
