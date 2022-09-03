(ns stark-challenge.gen-transactions.helpers
  (:require [clojure.math.numeric-tower :as math]))

(defn random-id []
  (math/round (rand (math/expt 10 16))))

(defn inst-timestamp []
  (-> (drop-last 4 (str (jt/instant)))
      (clojure.string/join)
      (str "+00:00")))
;; =>  "2022-09-03T20:54:53.360554+00:00"
;;:due "2022-09-03T19:55:00.000000+00:00"

;; Generate random transaction map
(defn gen-trans-map [amount id descr ext-id tags]
  {:amount amount
   :receiver-id (str id)
   :description (str descr)
   :external-id (str ext-id)
   :tags tags})

;; Exemplary transaction
;; (def transactions
;;   (starkbank.transaction/create
;;    [{:amount 100; (R$ 1,00)
;;      :receiver-id "5768064935133184"
;;      :description "Transaction to dear provider"
;;      :external-id "12345"; so we can block anything you send twice by mistake
;;      :tags ["provider"]}
;;     {:amount 234; (R$ 2,34)
;;      :receiver-id "5768064935133184"
;;      :description "Transaction to the other provider"
;;      :external-id "12346"; so we can block anything you send twice by mistake
;;      :tags ["provider"]}]))
