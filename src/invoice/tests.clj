(ns invoice.tests
  (:require [starkbank.event :as event]))

(event/query
 {:after "2020-03-20"
  :is-delivered false
  :limit 10})
