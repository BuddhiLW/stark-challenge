(ns stark-challenge.core
  (:require [ring.adapter.jetty :as j])
            ;; [ring.util.response :as resp])
  (:gen-class))

(defn handler [request]
  {:status 200
   :headers {"Content-type" "text/pain"}
   :body "Running on port 3130"})

(defn -main
  [& args]
  (j/run-jetty handler {:port 3130}))
