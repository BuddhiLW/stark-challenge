(ns invoice.core
  (:require [invoice.helpers :as help]
            [starkbank.invoice :as invoice]
            [namejen.names :as names]
            [cadastro-de-pessoa.cnpj :as cnpj]
            [chime.core :as chime]
            [java-time :as jt])
  (:gen-class))

(comment
  ;; example
  (def invoices (invoice/create
                 [{:tags ["immediate"]
                   :amount 400000
                   :due "2022-09-02T18:50:00.000000+00:00"
                   :tax-id "012.345.678-90"
                   :name "Iron Bank S.A."
                   :expiration 123456789
                   :fine 2.5
                   :interest 1.3
                   :descriptions [{:key "Product X"
                                   :value "big"}]}])))

(defn gen-day-invoices
  "First the timestamps are generated and saved into =ts= (timestamps).
   Then, we test if this list is empty. If it's not, we generate an invoice
   with the first timestamp. If it's empty, we return the list of generated
   invoices (maps).

  Generate invoices in a range of a day."
  []
  (let [ts (map help/due-timestamp (help/gen-twentyfour))]
    (loop [t (first ts)
           ts (rest ts)
           amount (help/r-amount 1000000)
           cnpj (cnpj/gen)
           name (names/name-maker)
           exp  (help/r-exp)
           fine (help/r-fine)
           inter (help/r-interest)
           descr [(help/r-descr-map amount)]
           vals []]
      (if (empty? t)
        vals
        (recur (first ts)
               (rest ts)
               (help/r-amount 1000000)
               (cnpj/gen)
               (names/name-maker)
               (help/r-exp)
               (help/r-fine)
               (help/r-interest)
               [(help/r-descr-map amount)]
               (conj vals (help/gen-invoice-map amount t cnpj name exp fine inter descr)))))))

(defn gen-three-hours-invoices
  "First the timestamps are generated and saved into =ts= (timestamps).
   Then, we test if this list is empty. If it's not, we generate an invoice
   with the first timestamp. If it's empty, we return the list of generated
   invoices (maps).

  Generate invoices in a range of three-hours."
  []
  (let [ts (map help/due-timestamp (help/gen-three-hours))]
    (loop [t (first ts)
           ts (rest ts)
           amount (help/r-amount 1000000)
           cnpj (cnpj/gen)
           name (names/name-maker)
           exp  (help/r-exp)
           fine (help/r-fine)
           inter (help/r-interest)
           descr [(help/r-descr-map amount)]
           vals []]
      (if (empty? t)
        vals
        (recur (first ts)
               (rest ts)
               (help/r-amount 1000000)
               (cnpj/gen)
               (names/name-maker)
               (help/r-exp)
               (help/r-fine)
               (help/r-interest)
               [(help/r-descr-map amount)]
               (conj vals (help/gen-invoice-map amount t cnpj name exp fine inter descr)))))))

(comment
  (invoice/create [(help/gen-invoice-map
                    (help/r-amount 100000)
                    (first (map help/due-timestamp (help/gen-three-hours)))
                    (cnpj/gen)
                    (names/name-maker)
                    (help/r-exp)
                    (help/r-fine)
                    (help/r-interest)
                    help/descr-ex)])
  ;; -------------------------------------
  (clojure.pprint/pprint (gen-three-hours-invoices))
  (count (gen-three-hours-invoices)))

(defn twentyfour-hours-three-apart
  [inst-start]
  (for [i (range 0 7)
        :let [t (help/future-timestamp inst-start (jt/hours (* 3 i)))
              vals []]]
    (conj vals t)))

(comment (clojure.pprint/pprint (vec (help/concatv (twentyfour-hours-three-apart (jt/instant))))))

(defn scheduled-invoices []
  (let [now (jt/instant)]
    (chime/chime-at [(help/future-timestamp now (jt/seconds 5))
                     (help/future-timestamp now (jt/hours 3))
                     (help/future-timestamp now (jt/hours 6))
                     (help/future-timestamp now (jt/hours 9))
                     (help/future-timestamp now (jt/hours 12))
                     (help/future-timestamp now (jt/hours 15))
                     (help/future-timestamp now (jt/hours 18))
                     (help/future-timestamp now (jt/hours 21))]
                    (fn [_]
                      (invoice/create (gen-three-hours-invoices))))))
