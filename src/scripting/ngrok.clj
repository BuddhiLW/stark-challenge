(ns scripting.ngrok
  (:require [clojure.java.shell :as shell])
  (:gen-class))

(defn strip [coll chars]
  (apply str (remove #((set chars) %) coll)))

(shell/sh "chmod" "a+rwx" "scripts/ngrok-gen.sh")

(def ngrok-url (-> (shell/sh "./scripts/ngrok-gen.sh")
                   (:out)
                   (strip "\n")))

(comment (clojure.pprint/pprint ngrok-url))
