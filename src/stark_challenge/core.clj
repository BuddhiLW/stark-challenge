(ns stark-challenge.core
  (:require [ring.adapter.jetty :as j]
            [starkbank.core :as stark])
            ;; [ring.util.response :as resp])
  (:gen-class))

(def key-pair (stark/create "/sample/destination/path"))
(println (:private-pem key-pair))
(println (:public-pem key-pair))

(defn handler [request]
  {:status 200
   :headers {"Content-type" "text/pain"}
   :body "Running on port 3130"})

(defn -main
  [& args]
  (j/run-jetty handler {:port 3130}))
