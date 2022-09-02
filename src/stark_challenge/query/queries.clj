(ns stark-challenge.query.queries
  (:require [starkbank.balance :as balance]
            [starkbank.transaction :as trans]))

(balance/get)
;; => {:id "5213546283532288",
;;     :amount 0,
;;     :currency "BRL",
;;     :updated "2022-09-02T14:54:37.202888+00:00"}
(trans/query {:limit 10})
