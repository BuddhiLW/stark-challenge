(ns stark-challenge.gen-invoice.helpers
  (:require [clojure.math.numeric-tower :as math]))

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
