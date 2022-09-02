(ns stark-challenge.core
  (:require [ring.adapter.jetty :as j]
            [starkbank.settings :as sset]
            [stark-challenge.user :as proj-setup])
  (:gen-class))

(defn handler [request]
  {:status 200
   :headers {"Content-type" "text/pain"}
   :body "Running on port 3130"})

(defn -main
  [& args]
  (sset/user proj-setup/buddhilw)
  (j/run-jetty handler {:port 3130}))
