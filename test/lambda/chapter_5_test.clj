(ns lambda.chapter-5-test
  (:require [clojure.test :refer :all]
            [lambda.chapter-5 :refer :all]))

(deftest test-error-type
  (testing "error type"
    (is (= error-type (+value t-error)))
    (is (= error-type (+type t-error)))
    (is (is-error? t-error))))

(deftest test-bool-type
  (testing "Boolean type"
    (is (is-bool? t-true))
    (is (is-bool? t-false))))

(deftest test-t-not
  (testing "typed NOT operator function"
    (is (= (+value t-true) (+value (t-not t-false))))
    (is (= (+value t-false) (+value (t-not t-true))))))

(deftest test-t-and
  (testing "typed AND operator function"
    (is (= (+value t-false) (+value ((t-and t-false) t-false))))
    (is (= (+value t-false) (+value ((t-and t-true) t-false))))
    (is (= (+value t-false) (+value ((t-and t-false) t-true))))
    (is (= (+value t-true) (+value ((t-and t-true) t-true))))))
