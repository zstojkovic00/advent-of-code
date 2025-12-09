(ns clojure-2025.core
  (:require [clojure.string :as str]))


;; https://adventofcode.com/2025/day/1

;; treba mi funkcija koja prihvata vektor i za svaki clan tog vektora racuna da li nakon pomeranja u stranu je jednako 0
;; 1. Otvori fajl
;; 2. Parsiraj fajl (L68 -> Left 68, R22 -> Right 22)
;; 3. Za svaki element uzmi direction i oduzmi/dodaj na trenutnu poziciju nakon toga posto je ciklican proces uradi mod 100
;; 4. Uporedi da li je rezultat 0, ako jeste inc

(System/getProperty "user.dir")
(slurp "resources/test1.txt")
;;(slurp "https://adventofcode.com/2025/day/1/input")         ;; ne radi :/ vrv moram da budem ulogovan

;; ucitavanje fajla
(def lines
  (str/split-lines (slurp "resources/input1.txt")))

(identity lines)

;; parsiranje fajla
(defn parse? [s] (
                   let [direction (subs s 0 1)
                        rest (Integer/parseInt (subs s 1))]
                   [direction rest]
                   ))

(subs "L68" 0 1)
(subs "L68" 1)
(parse? "L68")

(map parse? lines)
(mod -18 100)


;; Logika
(defn rotate [[direction num] cnt]
  (mod (if (= direction "L")
         (- cnt num)
         (+ cnt num))
       100)
  )

;; tests
(rotate ["L" 68] 50)
(rotate ["L" 20] 50)
(rotate ["L" 50] 50)

(rotate ["R" 68] 50)
(rotate ["R" 68] 20)
(rotate ["R" 50] 50)
(rotate ["R" 155] 50)


(count (filter zero? (reductions (fn [cnt pair]
                                   (rotate pair cnt))
                                 50
                                 (map parse? lines))))

(reduce (fn [[cnt count] pair]
          (let [res (rotate pair cnt)]
            [res (if (= res 0)
                   (inc count)
                   count)]))
        [50 0]
        (map parse? lines))


;; Day 2: Gift Shop

(System/getProperty "user.dir")
(slurp "resources/input2.txt")

(def lines
  (str/split (slurp "resources/input2.txt") #","))
(identity lines)

;; Problem je sto map prima funkciju i kolekciju a ja saljem dodatni argument direktno
;;(map str/split lines #"-")

(def products-ids-string
  (map (fn [line] (str/split line #"-")) lines))

(identity products-ids-string)

(def products-ids
  (map (fn [product-ids]
         (map read-string product-ids))
       products-ids-string))

(identity products-ids)

;; e sada mi treba da za svaki par napravim listu koji kaze od 11 do 22..
(def id-ranges
  (map (fn [product-ids]
         (range (first product-ids) (inc (second product-ids))))
       products-ids))

(first [11 22])
(second [11 22])

(let [pair [11 22]]
  (range (first pair) (inc (second pair))))

(identity id-ranges)

(defn invalid-id? [num]
  (let [num-str (str num)
        half-len (quot (count num-str) 2)
        first-half (subs num-str 0 half-len)
        second-half (subs num-str half-len)]
    (= first-half second-half)))

(invalid-id? 22)
(invalid-id? 11)
(invalid-id? 1188511885)
(invalid-id? 38593859)

(apply +
       (flatten
         (map (fn [id-range]
                (filter invalid-id? id-range))
              id-ranges)))

(->> id-ranges
     (map (fn [id-range]
            (filter invalid-id? id-range)))
     flatten
     (apply +))