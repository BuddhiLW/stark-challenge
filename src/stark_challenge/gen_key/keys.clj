(ns stark-challenge.gen-key.keys
  (:require [starkbank.key :as skey]))

(def key-pair (skey/create "resources/keys/"))
(def private-key (:private-key key-pair))
(def public-key (:public-key key-pair))
