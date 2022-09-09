(ns stark-challenge.core
  (:require [starkbank.settings :as sset]
            [stark-challenge.user :as proj-setup]
            [invoice.core :as ginv]
            [web.core :as server]
            [scripting.ngrok :as ngrok]
            [stark-challenge.webhook.start :as webhook])
  (:gen-class))

(defn -main
  [& args]
  (sset/user proj-setup/buddhilw)
  (server/-main)
  (ngrok/-main)
  (webhook/-main)
  (ginv/scheduled-invoices))
