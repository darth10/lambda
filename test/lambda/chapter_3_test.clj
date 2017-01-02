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
    (is (= +true (->> zero
                      (from-zero 0))))
    (is (= +false (->> zero
                       (from-zero 1))))
    (is (= +false (->> (succ zero)
                       (from-zero 0))))
    (is (= +true (->> (succ zero)
                      (from-zero 1))))
    (is (= +false (->> (succ (succ zero))
                       (from-zero 0))))
    (is (= +false (->> (succ (succ zero))
                       (from-zero 1))))
    (is (= +true (->> (succ (succ zero))
                      (from-zero 2))))))

(deftest test-succ
  (testing "successor function"
    (is (= +true (->> (succ zero)
                      (from-zero 1))))
    (is (= +true (->> (succ (succ zero))
                      (from-zero 2))))
    (is (= +true (->> (succ (succ (succ zero)))
                      (from-zero 3))))))

(deftest test-pred
  (testing "predecessor function"
    (is (= +true (->> (pred three)
                      (from-zero 2))))
    (is (= +true (->> (pred (pred three))
                      (from-zero 1))))
    (is (= +true (->> (pred (pred (pred three)))
                      (from-zero 0))))))

(deftest test-+if
  (testing "+if macro"
    (is (= 'x (+if +true 'x 'y)))
    (is (= 'y (+if +false 'x 'y)))))
