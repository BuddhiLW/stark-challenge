(ns stark-challenge.gen-transactions.transactions
  (:require [stark-challenge.gen-transactions.helpers :as help]))

(defn gen [amount id descr ext-id tags]
  {:amount amount
   :receiver-id (str id)
   :description (str descr)
   :external-id (str ext-id)
   :tags tags})

(comment
  (gen 100 (help/random-id) "Some description" 12312 ["provider" "lucky"]))

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
