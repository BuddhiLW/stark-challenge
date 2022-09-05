(ns stark-challenge.gen-invoice.helpers
  (:require [clojure.math.numeric-tower :as math]
            [clojure.string :as str]
            [clojure.set :as set]
            [java-time :as jt]
            [namejen.names :as names]
            [cadastro-de-pessoa.cnpj :as cnpj]))

(comment
  ;; Our model
  (invoice/create
   [{:tags ["immediate"]
     :amount 300
     :due "2022-09-03T19:55:00.000000+00:00"
     :tax-id "012.345.678-90"
     :name "BuddhiLW's Account"
     :expiration 123456789
     :fine 2.5
     :interest 1.3
     :descriptions [{:key "Product Z"
                     :value "small"}]}]))

(defn due-timestamp
  "Format timestamp for given time, complient to =:due= requested-format, on invoice/create."
  [time]
  (-> (drop-last 4 (str time))
      (clojure.string/join)
      (str "+00:00")))

(comment (due-timestamp (jt/instant))
         ;; => "2022-09-05T14:34:32.884993+00:00"
         (jt/plus (jt/instant) (jt/hours 1)))
         ;; => "2022-09-03T23:48:38.906757+00:00")

(defn future-timestamp
  "Generate timestamp for future due-date"
  [start jt-future-minutes]
  (jt/plus start jt-future-minutes))

(defn r-min-max
  "Generate a random value, between min and max - includes min and max"
  [min max]
  (let [M (* max 10)
        r (rand max)]
    (if (or (< r min) (> r M))
      (r-min-max min max)
      (math/round r))))

(comment
  (rand-int 100)
  (r-min-max 8 12))

(defn unique-random-numbers [n]
  (let [a-set (set (take n (repeatedly #(rand-int n))))]
    (concat a-set (set/difference (set (take n (range)))
                                  a-set))))

(comment (take 10 (unique-random-numbers 180))
         (apply max (take 10 (unique-random-numbers 180))))

(comment (map (partial future-timestamp (jt/instant))
              (map jt/minutes (take 10 (unique-random-numbers 180)))))

(defn gen-timestamps
  "Take initial timestamp (init-ts), quantity and time-range.
  Returns timestamps randomly generated through the timerange"
  [init-ts quant trange]
  (map (partial future-timestamp init-ts)
       (map jt/minutes (take quant (unique-random-numbers trange)))))

(comment (gen-timestamps (jt/instant) 10 180)
         (map due-timestamp (gen-timestamps (jt/instant) 10 180)))

(defn concatv
  "Helper function: concatenate `xs` and return the result as a vector."
  [& xs]
  (into [] cat xs))

(comment (concatv '(1 2) [3] [4 5]))

(comment (count (concatv '(1 2) [3] [4 5])))

(defn gen-twentyfour
  "Generate due-dates for 24 hours of invoices-creation, starting at five minutes from when the function is called."
  []
  (loop [init-ts  (future-timestamp (jt/instant) (jt/minutes 5))
         quantity (r-min-max 8 12)
         trange   180
         vals     []]
    (if (> (count vals) 64) ;; => the minimum is 64 invoices in 24 hours (8 per 3 hours)
      vals
      (recur (future-timestamp init-ts (jt/minutes trange))
             (r-min-max 8 12)
             (identity trange)
             (concatv vals (gen-timestamps init-ts quantity trange))))))

(comment (count (map due-timestamp (gen-twentyfour))))

(comment
  "This does not generate a valid id, most of the time.
   We will use the =cadatro-de-pessoa= library, instead."
  (defn r-three-digits
    "Generate three digits, randomly"
    []
    (let [max (math/expt 10 3)
          r (rand max)]
      (if (< r (/ max 10))
        (r-three-digits)
        (math/round r)))))

(defn r-two-digits
  "Generate two digits, randomly"
  []
  (let [max (math/expt 10 2)
        r (rand max)]
    (if (< r (/ max 10))
      (r-two-digits)
      (math/round r))))

(comment
  (defn r-id
    "Generate an id, randomly"
    []
    (str (r-three-digits) "." (r-three-digits) "." (r-three-digits) "-" (r-two-digits)))

  (r-id))
    ;; => "839.960.517-45"

(defn r-amount
  "Generate a random amount between 2R$ and a max value (a value of 100 translates to 1R$)"
  [max]
  (long (r-min-max 200 max)))

(comment (names/name-maker))
(defn r-exp []
  (rand-int 1000))

(defn r-fine []
  (float (/ (rand-int 1000) 100)))

(defn r-interest []
  (float (/ (rand-int 500) 100)))

(def alphabet ["A" "B" "C" "D" "E" "F" "G" "H" "I" "J" "K" "L" "M" "O" "P" "Q" "R" "S" "T" "U" "V" "W" "X" "Y" "Z"])
(def descriptions ["Product" "Service" "Payment"])
(comment (rand-nth alphabet))

(defn r-descr-key
  []
  (str  (rand-nth descriptions) " " (rand-nth alphabet) (r-two-digits)))

(defn r-descr-val
  [value]
  (if (> value 100000)
    "big"
    "small"))

(defn r-descr-map
  [value]
  {:key (r-descr-key)
   :value (r-descr-val value)})

(comment (r-descr-map 10000))
         ;; => Syntax error compiling at (src/stark_challenge/gen_invoice/helpers.clj:162:10).
         ;;    Unable to resolve symbol: r-descr-map in this context)
;; (defn r-)

;; {:key "Product Z"
;;  :value "small"}
(def descr-ex
  [{:key "Product Z"
    :value "small"}])

(defn gen-three-hours
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
             (concatv vals (gen-timestamps init-ts quantity trange))))))

(comment (map due-timestamp (gen-three-hours))
         (println descr-ex))

;; Generate invoice-map
(comment "The =gen-invoice-map= function must comply to the following.
          This function can be found on the implementation of
          =clojure-to-java=, used in =starkbank.invoice/create=."
         #_(defn- clojure-to-java
             ([clojure-map]
              (let [{amount "amount"
                     name "name"
                     tax-id "tax-id"
                     due "due"
                     expiration "expiration"
                     fine "fine"
                     interest "interest"
                     discounts "discounts"
                     tags "tags"
                     descriptions "descriptions"}
                    (stringify-keys clojure-map)]

                (defn- apply-java-hashmap [x] (java.util.HashMap. x))

                (Invoice. (java.util.HashMap.
                           {"amount" (if (nil? amount) nil (Long. amount))
                            "name" name
                            "taxId" tax-id
                            "due" due
                            "expiration" (if (nil? expiration) nil (Long. expiration))
                            "fine" (if (nil? fine) nil (double fine))
                            "interest" (if (nil? interest) nil (double interest))
                            "discounts" (if (nil? discounts) nil (java.util.ArrayList. (map apply-java-hashmap discounts)))
                            "tags" (if (nil? tags) nil (into-array String tags))
                            "descriptions" (if (nil? descriptions) nil (java.util.ArrayList. (map clojure-descriptions-to-java descriptions)))}))))))

(defn gen-invoice-map
  [amount due-datetime id name exp-time fine interest descr-vect-maps]
  {:tags ["scheduled"]
   :amount (long amount)
   :due (str due-datetime)
   :tax-id (str id)
   :name (str name)
   :expiration (long exp-time)
   :fine (double fine)
   :interest (double interest)
   :descriptions descr-vect-maps})

(comment (gen-invoice-map (r-amount 100000)
                          (first (map due-timestamp (gen-three-hours)))
                          (cnpj/gen)
                          (names/name-maker)
                          (r-exp)
                          (r-fine)
                          (r-interest)
                          descr-ex))
