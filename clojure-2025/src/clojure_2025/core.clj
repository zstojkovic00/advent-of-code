(ns clojure-2025.core
  (:require [clojure.string :as str]))


;; https://adventofcode.com/2025/day/1

;; treba mi funkcija koja prihvata vektor i za svaki clan tog vektora racuna da li nakon pomeranja u stranu je jednako 0
;; 1. Otvori fajl
;; 2. Parsiraj fajl
;; 3. za svaki element liste odredi stranu i to dodaj ili oduzmi do 50, left je minus a right je + sa time sto
;; 50 - 68 = -18 abs()
;; 50 + 55 = 100 + 5 - 100
;; 4. Uporedi da li je rezultat 0, ako jeste inc



(System/getProperty "user.dir")
(slurp "src/clojure_2025/tasks/test1.txt")

(def lines
  (str/split-lines (slurp "src/clojure_2025/tasks/test1.txt")))

(identity lines)