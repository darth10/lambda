(ns lambda.chapter-4-test
  (:require [clojure.test :refer :all :exclude [num]]
            [lambda.chapter-3 :refer
             [+true +false zero succ from-zero]]
            [lambda.chapter-4 :refer :all]))

(def num-seq (iterate succ zero))
(defn num [n] (nth num-seq n))

(deftest test-add-1
  (testing "add-1 function"
    (is (= +true (->> ((add-1 (num 0)) (num 0)) (from-zero 0))))
    (is (= +true (->> ((add-1 (num 1)) (num 0)) (from-zero 1))))
    (is (= +true (->> ((add-1 (num 0)) (num 1)) (from-zero 1))))
    (is (= +true (->> ((add-1 (num 1)) (num 1)) (from-zero 2))))
    (is (= +true (->> ((add-1 (num 1)) (num 2)) (from-zero 3))))
    (is (= +true (->> ((add-1 (num 2)) (num 1)) (from-zero 3))))
    (is (= +true (->> ((add-1 (num 2)) (num 2)) (from-zero 4))))))

(deftest test-add-2
  (testing "add-2 function"
    (is (= +true (->> ((add-2 (num 0)) (num 0)) (from-zero 0))))
    (is (= +true (->> ((add-2 (num 1)) (num 0)) (from-zero 1))))
    (is (= +true (->> ((add-2 (num 0)) (num 1)) (from-zero 1))))
    (is (= +true (->> ((add-2 (num 1)) (num 1)) (from-zero 2))))
    (is (= +true (->> ((add-2 (num 1)) (num 2)) (from-zero 3))))
    (is (= +true (->> ((add-2 (num 2)) (num 1)) (from-zero 3))))
    (is (= +true (->> ((add-2 (num 2)) (num 2)) (from-zero 4))))))

(deftest test-mult-1
  (testing "mult-1 function"
    (is (= +true (->> ((mult-1 (num 0)) (num 0)) (from-zero 0))))
    (is (= +true (->> ((mult-1 (num 0)) (num 1)) (from-zero 0))))
    (is (= +true (->> ((mult-1 (num 1)) (num 0)) (from-zero 0))))
    (is (= +true (->> ((mult-1 (num 1)) (num 1)) (from-zero 1))))
    (is (= +true (->> ((mult-1 (num 1)) (num 2)) (from-zero 2))))
    (is (= +true (->> ((mult-1 (num 2)) (num 1)) (from-zero 2))))
    (is (= +true (->> ((mult-1 (num 2)) (num 2)) (from-zero 4))))
    (is (= +true (->> ((mult-1 (num 2)) (num 3)) (from-zero 6))))
    (is (= +true (->> ((mult-1 (num 3)) (num 2)) (from-zero 6))))
    (is (= +true (->> ((mult-1 (num 3)) (num 3)) (from-zero 9))))
    (is (= +true (->> ((mult-1 (num 2)) (num 25)) (from-zero 50))))
    (is (= +true (->> ((mult-1 (num 3)) (num 25)) (from-zero 75))))
    (is (= +true (->> ((mult-1 (num 4)) (num 25)) (from-zero 100))))))

(deftest test-recursive
  (testing "recursive function"
    (is (thrown? StackOverflowError
                 (recursive recursive))
        "recursive is non-terminating when called on itself")))

(deftest test-mult-2
  (testing "mult-2 function"
    (is (= +true (->> ((mult-2 (num 0)) (num 0)) (from-zero 0))))
    (is (= +true (->> ((mult-2 (num 0)) (num 1)) (from-zero 0))))
    (is (= +true (->> ((mult-2 (num 1)) (num 0)) (from-zero 0))))
    (is (= +true (->> ((mult-2 (num 1)) (num 1)) (from-zero 1))))
    (is (= +true (->> ((mult-2 (num 1)) (num 2)) (from-zero 2))))
    (is (= +true (->> ((mult-2 (num 2)) (num 1)) (from-zero 2))))
    (is (= +true (->> ((mult-2 (num 2)) (num 2)) (from-zero 4))))
    (is (= +true (->> ((mult-2 (num 2)) (num 3)) (from-zero 6))))
    (is (= +true (->> ((mult-2 (num 3)) (num 2)) (from-zero 6))))
    (is (= +true (->> ((mult-2 (num 3)) (num 3)) (from-zero 9))))
    (is (= +true (->> ((mult-2 (num 2)) (num 25)) (from-zero 50))))
    (is (= +true (->> ((mult-2 (num 3)) (num 25)) (from-zero 75))))
    (is (= +true (->> ((mult-2 (num 4)) (num 25)) (from-zero 100))))))

(deftest test-recursive-2
  (testing "recursive-2 function"
    (is (thrown? StackOverflowError
                 (recursive-2 (fn [f] (recursive-2 (f)))))
        "recursive-2 is non-terminating when called on itself")))

(deftest test-mult-3
  (testing "mult-3 function"
    (is (= +true (->> ((mult-3 (num 0)) (num 0)) (from-zero 0))))
    (is (= +true (->> ((mult-3 (num 0)) (num 1)) (from-zero 0))))
    (is (= +true (->> ((mult-3 (num 1)) (num 0)) (from-zero 0))))
    (is (= +true (->> ((mult-3 (num 1)) (num 1)) (from-zero 1))))
    (is (= +true (->> ((mult-3 (num 1)) (num 2)) (from-zero 2))))
    (is (= +true (->> ((mult-3 (num 2)) (num 1)) (from-zero 2))))
    (is (= +true (->> ((mult-3 (num 2)) (num 2)) (from-zero 4))))
    (is (= +true (->> ((mult-3 (num 2)) (num 3)) (from-zero 6))))
    (is (= +true (->> ((mult-3 (num 3)) (num 2)) (from-zero 6))))
    (is (= +true (->> ((mult-3 (num 3)) (num 3)) (from-zero 9))))
    (is (= +true (->> ((mult-3 (num 2)) (num 25)) (from-zero 50))))
    (is (= +true (->> ((mult-3 (num 3)) (num 25)) (from-zero 75))))
    (is (= +true (->> ((mult-3 (num 4)) (num 25)) (from-zero 100))))))

(deftest test-power
  (testing "power function"
    (is (= +true (->> ((power (num 1)) (num 0)) (from-zero 1))))
    (is (= +true (->> ((power (num 1)) (num 1)) (from-zero 1))))
    (is (= +true (->> ((power (num 2)) (num 1)) (from-zero 2))))
    (is (= +true (->> ((power (num 2)) (num 2)) (from-zero 4))))
    (is (= +true (->> ((power (num 2)) (num 3)) (from-zero 8))))))

(deftest test-sub
  (testing "sub function"
    (is (= +true (->> ((sub (num 0)) (num 0)) (from-zero 0))))
    (is (= +true (->> ((sub (num 1)) (num 0)) (from-zero 1))))
    (is (= +true (->> ((sub (num 1)) (num 1)) (from-zero 0))))
    (is (= +true (->> ((sub (num 2)) (num 1)) (from-zero 1))))
    (is (= +true (->> ((sub (num 3)) (num 2)) (from-zero 1))))
    (is (= +true (->> ((sub (num 5)) (num 3)) (from-zero 2))))))

(deftest test-equal-1
  (testing "equal-1 function"
    (is (= +true ((equal-1 (num 0)) (num 0))))
    (is (= +false ((equal-1 (num 1)) (num 0))))
    (is (= +false ((equal-1 (num 0)) (num 1))))
    (is (= +true ((equal-1 (num 1)) (num 1))))
    (is (= +false ((equal-1 (num 2)) (num 1))))
    (is (= +false ((equal-1 (num 1)) (num 2))))))

(deftest test-equal-2
  (testing "equal-2 function"
    (is (= +true ((equal-2 (num 0)) (num 0))))
    (is (= +false ((equal-2 (num 1)) (num 0))))
    (is (= +false ((equal-2 (num 0)) (num 1))))
    (is (= +true ((equal-2 (num 1)) (num 1))))
    (is (= +false ((equal-2 (num 2)) (num 1))))
    (is (= +false ((equal-2 (num 1)) (num 2))))))

(deftest test-greater
  (testing "greater function"
    (is (= +false ((greater (num 0)) (num 0))))
    (is (= +true ((greater (num 1)) (num 0))))
    (is (= +false ((greater (num 0)) (num 1))))
    (is (= +false ((greater (num 1)) (num 1))))
    (is (= +false ((greater (num 1)) (num 2))))
    (is (= +true ((greater (num 2)) (num 1))))
    (is (= +false ((greater (num 1)) (num 2))))
    (is (= +false ((greater (num 2)) (num 2))))))

(deftest test-greater-or-equal
  (testing "greater-or-equal function"
    (is (= +true ((greater-or-equal (num 0)) (num 0))))
    (is (= +true ((greater-or-equal (num 1)) (num 0))))
    (is (= +false ((greater-or-equal (num 0)) (num 1))))
    (is (= +true ((greater-or-equal (num 1)) (num 1))))
    (is (= +false ((greater-or-equal (num 1)) (num 2))))
    (is (= +true ((greater-or-equal (num 2)) (num 1))))
    (is (= +false ((greater-or-equal (num 1)) (num 2))))
    (is (= +true ((greater-or-equal (num 2)) (num 2))))))

(deftest test-div
  (testing "div function"
    (is (= +true (->> ((div (num 3)) (num 0)) (from-zero 0))))
    (is (= +true (->> ((div (num 3)) (num 1)) (from-zero 3))))
    (is (= +true (->> ((div (num 3)) (num 3)) (from-zero 1))))
    (is (= +true (->> ((div (num 6)) (num 3)) (from-zero 2))))
    (is (= +true (->> ((div (num 9)) (num 3)) (from-zero 3))))
    (is (= +true (->> ((div (num 16)) (num 4)) (from-zero 4))))))
