(ns stark-challenge.gen-invoice.invoice
  (:require [stark-challenge.gen-invoice.helpers :as help]
            [starkbank.transaction :as trans]
            [starkbank.invoice :as invoice]
            [clj-time.local :as l]))

(comment
  (vector (help/gen-trans-map 100 (help/random-id) "Some description" 12312 ["provider" "lucky"]))
  (trans/create (vector (help/gen-trans-map 100 5768064935133184 "Some description" 12312 ["provider" "lucky"])))
  ;; example
  (def invoices (invoice/create
                 [{:tags ["immediate"]
                   :amount 400000
                   :due "2022-09-02T18:50:00.000000+00:00"
                   :tax-id "012.345.678-90"
                   :name "Iron Bank S.A."
                   :expiration 123456789
                   :fine 2.5
                   :interest 1.3
                   :descriptions [{:key "Product X"
                                   :value "big"}]}]))
  ;; Current time -- datetime format
  (l/local-now))
