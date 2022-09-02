(ns stark-challenge.project
  (:require [starkbank.user :as su]))

(defn strip [coll chars]
  (apply str (remove #((set chars) %) coll)))

(def private (slurp "resources/keys/privateKey.pem"))
(def id (-> (slurp "resources/id/id.txt")
            (strip "\n")))

;; User: buddhilw
(def buddhilw (su/project "sandbox" id private))
