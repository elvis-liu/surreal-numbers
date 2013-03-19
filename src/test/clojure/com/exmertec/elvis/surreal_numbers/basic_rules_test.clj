(ns com.exmertec.elvis.surreal_numbers.basic-rules-test
  (:use midje.sweet)
  (:require [com.exmertec.elvis.surreal_numbers.basic-rules :as br]))

(fact "how to identify empty set"
  (br/empty-set? []) => true
  (br/empty-set? [1]) => false
  (br/empty-set? 3) => false)

(facts "how to identify valid numbers"
  (fact "valid-number? method can identify a valid surreal number"
    (br/valid-number? {:left [], :right []}) => true
    (br/valid-number? {:left 0, :right []}) => true
    (br/valid-number? {:left [], :right 0}) => true
    (br/valid-number? {:left 0, :right 0}) => true)
  (fact "valid-number? method can identify an invalid surreal number"
    (br/valid-number? {}) => false
    (br/valid-number? {:left []}) => false
    (br/valid-number? {:right []}) => false
    (br/valid-number? {:left[], :right [], :middle []}) => false
    (br/valid-number? []) => false)
  (fact "valid-number? method can accept a direct digit number as valid"
    (br/valid-number? 3) => true
    (br/valid-number? 0) => true)
  (fact "valid-number? method can accept a more complex surreal number"
    (br/valid-number? {:left {:left [], :right []}, :right []}) => true))

(facts "how to create number"
  (fact "create method can create a valid surreal number"
    (br/valid-number? (br/create 0 1)) => true
    (br/valid-number? (br/create [] [])) => true
    (br/valid-number? (br/create [] 0)) => true
    (br/valid-number? (br/create 0 [])) => true)
  (fact "create method throws exception for invalid components"
    (br/create [0] 1) => (throws Exception)
    (br/create [0] [0]) => (throws Exception)
    (br/create 1 [0]) => (throws Exception)))
