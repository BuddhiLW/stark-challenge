(ns stark-challenge.gen-transactions.transactions
  (:require [stark-challenge.gen-transactions.helpers :as help]
            [starkbank.transaction :as trans]
            [starkbank.invoice :as invoice]
            [java-time :as jt]))

(comment
  (vector (help/gen-trans-map 100 (help/random-id) "Some description" 12312 ["provider" "lucky"]))
  (trans/create (vector (help/gen-trans-map 100 5768064935133184 "Some description" 12312 ["provider" "lucky"])))
  ;; example
  (def invoices (invoice/create
                 [{:tags ["immediate"]
                   :amount 400000
                   :due "2022-09-03T19:19:00.000000+00:00"
                   :tax-id "012.345.678-90"
                   :name "Iron Bank S.A."
                   :expiration 123456789
                   :fine 2.5
                   :interest 1.3
                   :descriptions [{:key "Product X"
                                   :value "big"}]}]))

  (invoice/create
   [{:tags ["immediate"]
     :amount 300
     :due "2022-09-03T19:55:00.000000+00:00"
     :tax-id "012.345.678-90"
     :name "BuddhiLW's Account"
     :expiration 123456789
     :fine 2.5
     :interest 1.3
     :descriptions [{:key "Product Z"
                     :value "small"}]}]))
  ;; Current time -- datetime format
(comment (l/local-now)
         (str (l/local-now) "+00:00"))
;; =>  "2022-09-03T17:33:03.370030+00:00"
;; =>  "2022-09-03T17:34:36.419030+00:00"
;; =>  "2022-09-03T17:35:15.418030+0:00")
;; =>  "2022-09-03T17:35:55.259030+00:00")
;;:due "2022-09-03T19:55:00.000000+00:00"
;; (time (java.util.Date.))
;;
(defn inst-timestamp []
  (-> (drop-last 4 (str (jt/instant)))
      (clojure.string/join)
      (str "+00:00")))
;; =>  "2022-09-03T20:54:53.360554+00:00"
;;:due "2022-09-03T19:55:00.000000+00:00"
