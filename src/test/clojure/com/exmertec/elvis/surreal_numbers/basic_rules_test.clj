(ns com.exmertec.elvis.surreal_numbers.basic-rules-test
  (:use clojure.test)
  (:require [com.exmertec.elvis.surreal_numbers.basic-rules :as br]))

(deftest identify-empty-set
  (testing "empty-set? method can identify an empty set"
    (is (br/empty-set? []))
    (is (not (br/empty-set? [1])))
    (is (not (br/empty-set? 3)))))

(deftest identify-number
  (testing "valid-number? method can identify a valid surreal number"
    (is (br/valid-number? {:left [], :right []}))
    (is (br/valid-number? {:left 0, :right []}))
    (is (br/valid-number? {:left [], :right 0}))
    (is (br/valid-number? {:left 0, :right 0})))
  (testing "valid-number? method can identify an invalid surreal number"
    (is (not (br/valid-number? {})))
    (is (not (br/valid-number? {:left []})))
    (is (not (br/valid-number? {:right []})))
    (is (not (br/valid-number? {:left[], :right [], :middle []})))
    (is (not (br/valid-number? []))))
  (testing "valid-number? method can accept a direct digit number as valid"
    (is (br/valid-number? 3))
    (is (br/valid-number? 0)))
  (testing "valid-number? method can accept a more complex surreal number"
    (is (br/valid-number? {:left {:left [], :right []}, :right []}))))

(deftest create-number
  (testing "create method can create a valid surreal number"
    (is (br/valid-number? (br/create 0 1)))
    (is (br/valid-number? (br/create [] [])))
    (is (br/valid-number? (br/create [] 0)))
    (is (br/valid-number? (br/create 0 [])))
  (testing "create method throws exception for invalid components"
    (is (thrown? Exception (br/create [0] 1)))
    (is (thrown? Exception (br/create [0] [0])))
    (is (thrown? Exception (br/create 1 [0]))))))
