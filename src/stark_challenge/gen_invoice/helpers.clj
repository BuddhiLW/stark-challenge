(ns stark-challenge.gen-invoice.helpers
  (:require [clojure.math.numeric-tower :as math]
            [java-time :as jt]))

(comment
  ;; Our model
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

(defn inst-timestamp
  "Generates timestamp for current instant, complient to =:due= requested-format, on invoice/create."
  []
  (-> (drop-last 4 (str (jt/instant)))
      (clojure.string/join)
      (str "+00:00")))

(defn r-tax-id []
  (let [max (math/expt 10 3)
        r (rand max)]
    (if (< r (/ max 10))
      (r-tax-id)
      (math/round r))))

(r-tax-id)

;; Generate invoice-map
(defn gen-invoice-map
  [amount due-datetime id name descr exp-time fine interest descr-vect-maps]
  {:tags ["scheduled"]
   :amount (float amount)
   :due (str due-datetime)
   :tax-id (str id)
   :name (str name)
   :expiration (float exp-time)
   :fine (float fine)
   :interest (float interest)
   :descriptions descr-vect-maps})
