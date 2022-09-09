(ns database.core
  (:require [xtdb.api :as xt]
            [mount.core :as mount])
  (:gen-class))

(mount/defstate node :start (xt/start-node {}))

#_(defn start-xtdb! []
    (letfn [(kv-store [dir]
              {:kv-store {:xtdb/module 'xtdb.rocksdb/->kv-store
                          :db-dir (io/file dir)
                          :sync? true}})]
      (xt/start-node
       {:xtdb/tx-log (kv-store "data/dev/tx-log")
        :xtdb/document-store (kv-store "data/dev/doc-store")
        :xtdb/index-store (kv-store "data/dev/index-store")})))

#_(def xtdb-node (start-xtdb!))

;; (defn stop-xtdb! []
;;   (.close xtdb-node))

(defn ensure-id
  [{:keys [:xt/id] :as entity}]
  (let [uuid (java.util.UUID/randomUUID)]
    (if-not id
      (assoc entity
             :xt/id uuid
             :id/id uuid)
      entity)))

(comment (ensure-id {:hi "there"})
         (ensure-id {:hi "there" :xt/id "a-id"}))

(defn write [entity]
  (let [with-id (ensure-id entity)]
    (xt/submit-tx node [[::xt/put with-id]])))

(comment (xt/submit-tx node [[::xt/put
                              {:xt/id :dbpedia.resource/Pablo-Picasso :first-name :Pablo}]]))

(defn query []
  (xt/q
   (xt/db node)
   '{:find [?e]
     :where [[?e :id/id _]]}))

;; (query)
