(ns web.core
  (:require [reitit.ring :as ring]
            [reitit.middleware :as middleware]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]
            [aleph.http :as http]
            [mount.core :as mount]
            [clojure.java.io :as io]
            ;; [database.core :as db]
            [transfer.core :as tr])
  (:gen-class))

(defn index-handler [_]
  {:body (slurp (io/resource "public/index.html"))})

(defn create-handler
  [req]
  (let [bp (:body-params req)
        ev (:event bp)
        log (:log ev)
        inv (:invoice log)
        amount (Float/parseFloat (:amount inv))
        int-amt (Float/parseFloat (:interestAmount inv))
        fine-amt (Float/parseFloat (:fineAmount inv))
        disc-amt (Float/parseFloat (:discontAmount inv))
        liq-amt (- (+ amount fine-amt int-amt) disc-amt)
        id (:id inv)]
    ;; (clojure.pprint/pprint inv)
    (if (= (str (:status inv)) "paid")
      (do
        (tr/trans-s-bank liq-amt id)
        {:body bp})

      (do (clojure.pprint/pprint inv)
          {:body bp}))))

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

(comment
  ;;; --------- Example of possible invoice reqs
  {:amount 630098
   :fee 0,
   :tags ["scheduled"],
   :transactionIds [],
   :updated "2022-09-09T12:38:47.329730+00:00",
   :name "Runo Nacea, II",
   :expiration 654,
   :pdf
   "https://sandbox.api.starkbank.com/v2/invoice/007a65e14058476887c57eb00e7177d7.pdf",
   :created "2022-09-09T12:38:33.299458+00:00",
   :nominalAmount 630098,
   :discounts [],
   :due "2022-09-09T13:41:32.898644+00:00",
   :interestAmount 0,
   :status "created",
   :interest 1.600000023841858,
   :link
   "https://challenge447890.sandbox.starkbank.com/invoicelink/007a65e14058476887c57eb00e7177d7",
   :id "4539818893639680",
   :taxId "24.870.952/1747-00",
   :discountAmount 0,
   :fine 4.449999809265137,
   :fineAmount 0,
   :brcode
   "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/007a65e14058476887c57eb00e7177d75204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***6304ED88",
   :descriptions [{:key "Product Y57", :value "big"}]}

  {:amount 630098
   :fee 0,
   :tags ["scheduled" "e29079725202209091240o5u6xqcntji"],
   :transactionIds ["5465050475134976"],
   :updated "2022-09-09T12:40:33.045155+00:00",
   :name "Runo Nacea, II",
   :expiration 654,
   :pdf
   "https://sandbox.api.starkbank.com/v2/invoice/007a65e14058476887c57eb00e7177d7.pdf",
   :created "2022-09-09T12:38:33.299458+00:00",
   :nominalAmount 630098,
   :discounts [],
   :due "2022-09-09T13:41:32.898644+00:00",
   :interestAmount 0,
   :status "paid",
   :interest 1.600000023841858,
   :link
   "https://challenge447890.sandbox.starkbank.com/invoicelink/007a65e14058476887c57eb00e7177d7",
   :id "4539818893639680",
   :taxId "24.870.952/1747-00",
   :discontAmount 0,
   :fine 4.449999809265137,
   :fineAmount 0,
   :brcode
   "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/007a65e14058476887c57eb00e7177d75204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***6304ED88",
   :descriptions [{:key "Product Y57", :value "big"}]})
