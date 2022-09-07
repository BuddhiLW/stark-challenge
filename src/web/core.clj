(ns web.core
  (:require [reitit.ring :as ring]
            [reitit.middleware :as middleware]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]
            [aleph.http :as http]
            [mount.core :as mount]
            [clojure.java.io :as io]))

(defn index-handler [_]
  {:body (slurp (io/resource "public/index.html"))})

(defn create-handler
  [req]
  (let [bg (:body-params req)]
    (clojure.pprint/pprint bg)
    {:body "body"}))

(def app
  (ring/ring-handler
   (ring/router
    [["/invoices"
      ["/create" {:post create-handler
                  :middleware [:content]}]]
     ["/assets/*" (ring/create-resource-handler {:root "public/assets/"})]
     ["/index" {:get index-handler}]
     ["/" {:post create-handler :middleware [:content]}]]
    {::middleware/registry {:content muuntaja/format-middleware}
     :data {:muuntaja m/instance}})))

(mount/defstate server
  :start (http/start-server #'app {:port 8844})
  :stop (.close server))

(defn -main [& _]
  (mount/start))
