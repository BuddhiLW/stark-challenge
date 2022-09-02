(ns stark-challenge.gen-transactions.helpers
  (:require [clojure.math.numeric-tower :as math]))

;; Generate random id, with 16 digits
(defn random-id []
  (math/round (rand (math/expt 10 16))))
