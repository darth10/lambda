(ns lambda.chapter-3-test
  (:require [clojure.test :refer :all]
            [lambda.chapter-3 :refer :all]))

(deftest test-+cond
  (testing "conditional expression function"
    ;; true ? x : y
    (is (= 'x (((+cond 'x) 'y) +true)))
    ;; false ? x : y
    (is (= 'y (((+cond 'x) 'y) +false)))))

(deftest test-+not
  (testing "NOT operator function"
    (is (= +true (+not +false)))
    (is (= +false (+not +true))))
  (testing "NOT operator function using +cond"
    (is (= (+not +true) (+not' +true)))
    (is (= (+not +false) (+not' +false)))))

(deftest test-+and
  (testing "AND operator function"
    (is (= +false ((+and +false) +false)))
    (is (= +false ((+and +true) +false)))
    (is (= +false ((+and +false) +true)))
    (is (= +true ((+and +true) +true))))
  (testing "AND operator function using +cond"
    (is (= ((+and +false) +false) ((+and' +false) +false)))
    (is (= ((+and +true) +false) ((+and' +true) +false)))
    (is (= ((+and +false) +true) ((+and' +false) +true)))
    (is (= ((+and +true) +true) ((+and' +true) +true)))))

(deftest test-+or
  (testing "OR operator function"
    (is (= +false ((+or +false) +false)))
    (is (= +true ((+or +true) +false)))
    (is (= +true ((+or +false) +true)))
    (is (= +true ((+or +true) +true))))
  (testing "OR operator function using +cond"
    (is (= ((+or +false) +false) ((+or' +false) +false)))
    (is (= ((+or +true) +false) ((+or' +true) +false)))
    (is (= ((+or +false) +true) ((+or' +false) +true)))
    (is (= ((+or +true) +true) ((+or' +true) +true)))))

(deftest test-is-zero?
  (testing "function to check if a number is zero"
    (is (= +true (is-zero? zero)))
    (is (= +false (is-zero? one)))))

(deftest test-from-zero
  (testing "from-zero helper function"
    (is (from-zero 0 zero))
    (is (not (from-zero 1 zero)))
    (is (not (from-zero 0 (succ zero))))
    (is (from-zero 1 (succ zero)))
    (is (not (from-zero 0 (succ (succ zero)))))
    (is (not (from-zero 1 (succ (succ zero)) )))
    (is (from-zero 2 (succ (succ zero))))))

(deftest test-succ
  (testing "successor function"
    (is (from-zero 1 (succ zero)))
    (is (from-zero 2 (succ (succ zero))))
    (is (from-zero 3 (succ (succ (succ zero)))))))

(deftest test-pred
  (testing "predecessor function"
    (is (from-zero 2 (pred three)))
    (is (from-zero 1 (pred (pred three))))
    (is (from-zero 0 (pred (pred (pred three)))))))

(deftest test-+if
  (testing "+if macro"
    (is (= 'x (+if +true 'x 'y)))
    (is (= 'y (+if +false 'x 'y)))))
