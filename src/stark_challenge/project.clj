(ns stark-challenge.project
  (:require [starkbank.user :as su]))

(defn strip [coll chars]
  (apply str (remove #((set chars) %) coll)))

(def private (slurp "resources/keys/privateKey.pem"))
(def id (-> (slurp "resources/id/id.txt")
            (strip "\n")))

(def project (su/project "sandbox" id private))
