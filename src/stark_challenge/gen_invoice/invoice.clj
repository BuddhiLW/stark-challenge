(ns stark-challenge.gen-invoice.invoice
  (:require [stark-challenge.gen-invoice.helpers :as help]
            [starkbank.transaction :as trans]
            [starkbank.invoice :as invoice]
            [namejen.names :as names]
            [cadastro-de-pessoa.cnpj :as cnpj]))

(comment
  (vector (help/gen-trans-map 100 (help/random-id) "Some description" 12312 ["provider" "lucky"]))
  (trans/create (vector (help/gen-trans-map 100 5768064935133184 "Some description" 12312 ["provider" "lucky"])))
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
                                   :value "big"}]}]))

  (invoice/create
   [{:tags ["immediate"]
     :amount 300
     :due "2022-09-03T19:55:00.000000+00:00"
     ;; :due "2022-09-04T23:14:31.290750+00:00"
     :tax-id "012.345.678-90"
     :name "BuddhiLW's Account"
     :expiration 123456789
     :fine 2.5
     :interest 1.3
     :descriptions [{:key "Product Z"
                     :value "small"}]}])
  (invoice/create [(help/gen-invoice-map
                    (help/r-amount 100000)
                    (first (map help/due-timestamp (help/gen-three-hours)))
                    (cnpj/gen)
                    (names/name-maker)
                    (help/r-exp)
                    (help/r-fine)
                    (help/r-interest)
                    help/descr-ex)]))
                   ;; => {:amount 81251,
                   ;;     :tags ["scheduled"],
                   ;;     :name "Tore Aura",
                   ;;     :expiration 899,
                   ;;     :tax-id "550.569.890-75",
                   ;;     :due "2022-09-05T00:14:49.325439+00:00",
                   ;;     :interest 3.76,
                   ;;     :fine 0.7,
                   ;;     :descriptions [{:key "Product Z", :value "small"}]}]))

(comment
  #_(defn gen-three-hours
      "Generate due-dates for 3 hours of invoices-creation, starting at five minutes from when the function is called."
      []
      (loop [init-ts  (future-timestamp (jt/instant) (jt/minutes 5))
             quantity (r-min-max 8 12)
             trange   180
             vals     []]
        (if (> (count vals) 8) ;; => the minimum is 64 invoices in 24 hours (8 per 3 hours)
          vals
          (recur (future-timestamp init-ts (jt/minutes trange))
                 (r-min-max 8 12)
                 (identity trange)
                 (concatv vals (gen-timestamps init-ts quantity trange)))))))

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
                    help/descr-ex)]))
