(ns com.exmertec.elvis.surreal_numbers.basic-rules
  (:require [clojure.contrib.except :as except]))

(declare valid-component?)

(defn empty-set? [s]
  (and
    (vector? s)
    (empty? s)))


(defn valid-number? [n]
  (or
    (number? n)
    (and
      (map? n)
      (= 2 (count n))
      (valid-component? (get n :left))
      (valid-component? (get n :right)))))

(defn create [l r]
  (except/throw-if (not (and (valid-component? l) (valid-component? r))))
  {:left l, :right r})

(defn- valid-component? [c]
  (or (empty-set? c) (valid-number? c)))
