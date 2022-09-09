(ns transfer.core
  (:require [starkbank.settings :as sset]
            [invoice.helpers :as inv-help]
            [stark-challenge.user :as proj-setup]
            [starkbank.transfer :as transf])
  (:gen-class))

(sset/user proj-setup/buddhilw)
;; Exemplary transaction
(comment
  (starkbank.transaction/create
   [{:amount 100; (R$ 1,00)
     :receiver-id "6341320293482496"
     :description "Transaction back to Stark Bank S.A."
     :external-id (str (inv-help/r-two-digits) (inv-help/r-two-digits) (inv-help/r-two-digits)); so we can block anything you send twice by mistake
     :tags ["provider"]}] proj-setup/buddhilw))

(defn trans-s-bank
  [liq-amount ext-id]
  (transf/create
   [{:amount (long liq-amount)
     :external-id (str ext-id) ;;"4786489095356416"
     :name "Stark Bank S.A."
     :bank-code "20018183"; Pix
     :account-number "6341320293482496"
     :branch-code "0001"
     :tax-id "20.018.183/0001-80"
     :account-type "payment"
     :description "some descr"
     :tag ["random tag"]}]))

;; (trans-s-bank 200 5285034105765888)

(comment (starkbank.transaction/get "5490467957374976")
         (clojure.pprint/pprint (:id proj-setup/buddhilw))
         (trans-s-bank (long 10) (str 5519647231180800))
         (starkbank.transfer/create
          [{:amount 100
            :branch-code "0001"
            :account-number "10000-0"
            :account-type "checking"
            :external-id "my-internal-id-12345"
            :tax-id "012.345.678-90"
            :name "Tony Stark"
            :tags ["iron" "suit"]}])
         (starkbank.user/project (:environment proj-setup/buddhilw)
                                 (:id proj-setup/buddhilw)
                                 (:private-key proj-setup/buddhilw)))
