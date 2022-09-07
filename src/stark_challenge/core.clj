(ns stark-challenge.core
  (:require [starkbank.settings :as sset]
            [stark-challenge.user :as proj-setup]
            [stark-challenge.gen-invoice.invoice :as ginv])
  (:gen-class))

(defn -main
  [& args]
  (sset/user proj-setup/buddhilw)
  (ginv/scheduled-invoices))

