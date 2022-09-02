(ns stark-challenge.core
  (:require [ring.adapter.jetty :as j]
            [starkbank.key :as skey])
  (:gen-class))

(def key-pair (skey/create "resources/keys/"))
(def private-key (:private-key key-pair))
(def public-key (:public-key key-pair))

(defn handler [request]
  {:status 200
   :headers {"Content-type" "text/pain"}
   :body "Running on port 3130"})

(defn -main
  [& args]
  (j/run-jetty handler {:port 3130}))
