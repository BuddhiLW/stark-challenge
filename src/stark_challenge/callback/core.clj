(ns stark-challenge.callback.core
  (:require [stark-challenge.gen-invoice.invoice :as gen-inv]
            [stark-challenge.gen-invoice.helpers :as help]
            [starkbank.invoice :as invoice]))

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

(comment
  (def exemp-inv-cb (invoice/create (gen-inv/gen-three-hours-invoices)))
  (clojure.pprint/pprint exemp-inv-cb)
  (clojure.pprint/pprint (first exemp-inv-cb)))
#_(def
    exemp-inv-cb
    ({:amount 753424,
      :interest-amount 0,
      :fee 0,
      :tags ["scheduled"],
      :updated "2022-09-06T18:52:52.217325+00:00",
      :name "Dalyn",
      :expiration 87,
      :tax-id "00.897.006/0125-43",
      :pdf
      "https://sandbox.api.starkbank.com/v2/invoice/4b91c9aa61624086b5c3d74e0d4524f7.pdf",
      :nominal-amount 753424,
      :created "2022-09-06T18:52:51.864230+00:00",
      :discounts [],
      :due "2022-09-06T18:57:51.526662+00:00",
      :status "created",
      :interest 3.680000066757202,
      :link
      "https://challenge447890.sandbox.starkbank.com/invoicelink/4b91c9aa61624086b5c3d74e0d4524f7",
      :id "5020303998582784",
      :fine 6.929999828338623,
      :transaction-ids [],
      :fine-amount 0,
      :discount-amount 0,
      :brcode
      "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/4b91c9aa61624086b5c3d74e0d4524f75204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***6304273F",
      :descriptions [{:key "Product G25", :value "big"}]
      {:amount 56530,
       :interest-amount 0,
       :fee 0,
       :tags ["scheduled"],
       :updated "2022-09-06T18:52:52.217640+00:00",
       :name "Erli Takayuki Mehrdad",
       :expiration 456,
       :tax-id "44.080.929/4000-87",
       :pdf
       "https://sandbox.api.starkbank.com/v2/invoice/b2badf5bd0e040bd86dda48a5670b001.pdf",
       :nominal-amount 56530,
       :created "2022-09-06T18:52:51.864469+00:00",
       :discounts [],
       :due "2022-09-06T20:07:51.526662+00:00",
       :status "created",
       :interest 0.27000001072883606,
       :link
       "https://challenge447890.sandbox.starkbank.com/invoicelink/b2badf5bd0e040bd86dda48a5670b001",
       :id "6146203905425408",
       :fine 6.889999866485596,
       :transaction-ids [],
       :fine-amount 0,
       :discount-amount 0,
       :brcode
       "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/b2badf5bd0e040bd86dda48a5670b0015204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***630404B5",
       :descriptions [{:key "Service H57", :value "big"}]}
      {:amount 874463,
       :interest-amount 0,
       :fee 0,
       :tags ["scheduled"],
       :updated "2022-09-06T18:52:52.217741+00:00",
       :name "Odolfo",
       :expiration 983,
       :tax-id "48.090.506/3469-75",
       :pdf
       "https://sandbox.api.starkbank.com/v2/invoice/3a0956981f984c4996c3cc3a6bd44ff2.pdf",
       :nominal-amount 874463,
       :created "2022-09-06T18:52:51.864593+00:00",
       :discounts [],
       :due "2022-09-06T19:59:51.526662+00:00",
       :status "created",
       :interest 1.5499999523162842,
       :link
       "https://challenge447890.sandbox.starkbank.com/invoicelink/3a0956981f984c4996c3cc3a6bd44ff2",
       :id "5583253952004096",
       :fine 8.329999923706055,
       :transaction-ids [],
       :fine-amount 0,
       :discount-amount 0,
       :brcode
       "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/3a0956981f984c4996c3cc3a6bd44ff25204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***6304275A",
       :descriptions [{:key "Product B96", :value "small"}]}
      {:amount 632934,
       :interest-amount 0,
       :fee 0,
       :tags ["scheduled"],
       :updated "2022-09-06T18:52:52.217842+00:00",
       :name "Mr. Arlon Yler Rren",
       :expiration 981,
       :tax-id "02.712.623/0606-03",
       :pdf
       "https://sandbox.api.starkbank.com/v2/invoice/7cbd589455384d23bfe89e73e03b5d6e.pdf",
       :nominal-amount 632934,
       :created "2022-09-06T18:52:51.864708+00:00",
       :discounts [],
       :due "2022-09-06T21:41:51.526662+00:00",
       :status "created",
       :interest 2.7799999713897705,
       :link
       "https://challenge447890.sandbox.starkbank.com/invoicelink/7cbd589455384d23bfe89e73e03b5d6e",
       :id "6709153858846720",
       :fine 0.8600000143051147,
       :transaction-ids [],
       :fine-amount 0,
       :discount-amount 0,
       :brcode
       "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/7cbd589455384d23bfe89e73e03b5d6e5204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***63047BB4",
       :descriptions [{:key "Product I41", :value "big"}]}
      {:amount 820733,
       :interest-amount 0,
       :fee 0,
       :tags ["scheduled"],
       :updated "2022-09-06T18:52:52.217957+00:00",
       :name "Ylvesterling",
       :expiration 890,
       :tax-id "74.142.268/4119-00",
       :pdf
       "https://sandbox.api.starkbank.com/v2/invoice/c6db83e7530b457a9788c48c0b4ad129.pd;; => f",
       :nominal-amount 820733,
       :created "2022-09-06T18:52:51.864829+00:00",
       :discounts [],
       :due "2022-09-06T21:07:51.526662+00:00",
       :status "created",
       :interest 3.240000009536743,
       :link
       "https://challenge447890.sandbox.starkbank.com/invoicelink/c6db83e7530b457a9788c48c0b4ad129",
       :id "4562907161427968",
       :fine 8.359999656677246,
       :transaction-ids [],
       :fine-amount 0,
       :discount-amount 0,
       :brcode
       "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/c6db83e7530b457a9788c48c0b4ad1295204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***6304534F",
       :descriptions [{:key "Service P89", :value "big"}]}
      {:amount 306253,
       :interest-amount 0,
       :fee 0,
       :tags ["scheduled"],
       :updated "2022-09-06T18:52:52.218046+00:00",
       :name "Phoebe Neek",
       :expiration 59,
       :tax-id "26.058.166/4851-48",
       :pdf
       "https://sandbox.api.starkbank.com/v2/invoice/df7ab283031e4ea992c6312e15949bc4.pdf",
       :nominal-amount 306253,
       :created "2022-09-06T18:52:51.864949+00:00",
       :discounts [],
       :due "2022-09-06T21:05:51.526662+00:00",
       :status "created",
       :interest 3.5299999713897705,
       :link
       "https://challenge447890.sandbox.starkbank.com/invoicelink/df7ab283031e4ea992c6312e15949bc4",
       :id "5688807068270592",
       :fine 6.090000152587891,
       :transaction-ids [],
       :fine-amount 0,
       :discount-amount 0,
       :brcode
       "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/df7ab283031e4ea992c6312e15949bc45204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***6304F8C4",
       :descriptions [{:key "Service U79", :value "big"}]}
      {:amount 425765,
       :interest-amount 0,
       :fee 0,
       :tags ["scheduled"],
       :updated "2022-09-06T18:52:52.218132+00:00",
       :name "Ilheminada Arguerite An-piercarlos Chey Rada",
       :expiration 12,
       :tax-id "91.644.527/7891-58",
       :pdf
       "https://sandbox.api.starkbank.com/v2/invoice/9ae8cce2d3cd430b9f0ba83677192e5c.pdf",
       :nominal-amount 425765,
       :created "2022-09-06T18:52:51.865058+00:00",
       :discounts [],
       :due "2022-09-06T19:04:51.526662+00:00",
       :status "created",
       :interest 2.069999933242798,
       :link
       "https://challenge447890.sandbox.starkbank.com/invoicelink/9ae8cce2d3cd430b9f0ba83677192e5c",
       :id "5125857114849280",
       :fine 4.940000057220459,
       :transaction-ids [],
       :fine-amount 0,
       :discount-amount 0,
       :brcode
       "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/9ae8cce2d3cd430b9f0ba83677192e5c5204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***63046656",
       :descriptions [{:key "Product S94", :value "big"}]}
      {:amount 566228,
       :interest-amount 0,
       :fee 0,
       :tags ["scheduled"],
       :updated "2022-09-06T18:52:52.218216+00:00",
       :name "Eban Phil Izumi Rvey Nville",
       :expiration 352,
       :tax-id "68.014.551/8446-30",
       :pdf
       "https://sandbox.api.starkbank.com/v2/invoice/92028cc2bb1c4526b252a4d21c0caca6.pdf",
       :nominal-amount 566228,
       :created "2022-09-06T18:52:51.865164+00:00",
       :discounts [],
       :due "2022-09-06T19:56:51.526662+00:00",
       :status "created",
       :interest 0.8199999928474426,
       :link
       "https://challenge447890.sandbox.starkbank.com/invoicelink/92028cc2bb1c4526b252a4d21c0caca6",
       :id "6251757021691904",
       :fine 6.349999904632568,
       :transaction-ids [],
       :fine-amount 0,
       :discount-amount 0,
       :brcode
       "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/92028cc2bb1c4526b252a4d21c0caca65204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***6304BCA6",
       :descriptions [{:key "Payment K12", :value "big"}]}
      {:amount 778716,
       :interest-amount 0,
       :fee 0,
       :tags ["scheduled"],
       :updated "2022-09-06T18:52:52.218301+00:00",
       :name "Young",
       :expiration 352,
       :tax-id "91.585.088/2844-65",
       :pdf
       "https://sandbox.api.starkbank.com/v2/invoice/26a0cc1fd19b4c46a609b952ff4ef576.pdf",
       :nominal-amount 778716,
       :created "2022-09-06T18:52:51.865276+00:00",
       :discounts [],
       :due "2022-09-06T20:23:51.526662+00:00",
       :status "created",
       :interest 1.8600000143051147,
       :link
       "https://challenge447890.sandbox.starkbank.com/invoic;; => elink/26a0cc1fd19b4c46a609b952ff4ef576",
       :id "4844382138138624",
       :fine 6.510000228881836,
       :transaction-ids [],
       :fine-amount 0,
       :discount-amount 0,
       :brcode
       "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/26a0cc1fd19b4c46a609b952ff4ef5765204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***63049926",
       :descriptions [{:key "Payment Q38", :value "big"}]}
      {:amount 350780,
       :interest-amount 0,
       :fee 0,
       :tags ["scheduled"],
       :updated "2022-09-06T18:52:52.218399+00:00",
       :name "Ms. Queenie Hsuan",
       :expiration 768,
       :tax-id "96.402.014/9651-49",
       :pdf
       "https://sandbox.api.starkbank.com/v2/invoice/dc64626bcc11433fa65b5b681c32df8d.pdf",
       :nominal-amount 350780,
       :created "2022-09-06T18:52:51.865391+00:00",
       :discounts [],
       :due "2022-09-06T21:31:51.526662+00:00",
       :status "created",
       :interest 3.009999990463257,
       :link
       "https://challenge447890.sandbox.starkbank.com/invoicelink/dc64626bcc11433fa65b5b681c32df8d",
       :id "5970282044981248",
       :fine 9.149999618530273,
       :transaction-ids [],
       :fine-amount 0,
       :discount-amount 0,
       :brcode
       "00020101021226890014br.gov.bcb.pix2567brcode-h.sandbox.starkinfra.com/v2/dc64626bcc11433fa65b5b681c32df8d5204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***6304520D",
       :descriptions [{:key "Service K30", :value "big"}]}}))
