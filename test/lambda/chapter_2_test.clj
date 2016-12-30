(ns lambda.chapter-2-test
  (:require [clojure.test :refer :all]
            [lambda.chapter-2 :refer :all]))

;; Chapter 2

(deftest test-id
  (testing "identity function"
    (is (= 'x (id 'x) 'x))
    (is (= 0 (id 0) 0))
    (is (= id (id id)))))

(deftest test-s-apply
  (testing "self applying function"
    (is (= id (self-apply id)))
    (is (thrown? StackOverflowError
                 (self-apply self-apply))
        "s-apply is non-terminating when called on itself")))

(deftest test-f-apply
  (testing "apply function"
    (is (= 0 ((f-apply id) 0)))
    (is (= self-apply ((f-apply id) self-apply)))))

(deftest test-id-2
  (testing "identity function 2"
    (is (= 'x (id-2 'x)))
    (is (= 0 (id-2 0)))
    (is (= id (id-2 id)))
    (is (= self-apply (id-2 self-apply)))))

(deftest test-s-apply-2
  (testing "self applying function 2"
    (is (= id (self-apply-2 id)))
    ;; s-apply-2 (like s-apply) never terminates when called on itself
    (is (thrown? StackOverflowError
                 (self-apply-2 self-apply-2)))))

(deftest test-select-first
  (testing "select-first function"
    (is (= id ((select-first id) f-apply)))
    (is (= 0 ((select-first 0) 1)))))

(deftest test-select-second
  (testing "select-second function"
    (is (= f-apply ((select-second id) f-apply)))
    (is (= 1 ((select-second 0) 1)))))

(deftest test-make-pair
  (testing "make-pair function"
    (let [pair ((make-pair id) f-apply)]
      (is (= id (pair select-first)))
      (is (= f-apply (pair select-second))))
    (let [pair ((make-pair 'x) 'y)]
      (is (= 'x (pair select-first)))
      (is (= 'y (pair select-second))))))

(deftest test-free-bound-vars
  (testing "free and bound variables are handled correctly"
    (is (= id
           ((fn [x] (x id)) self-apply)))))

(deftest test-alpha-conversion
  (testing "alpha conversion is done correctly"
    (let [arg id]
      (is (= 0
             (((fn [func] (fn [arg] (func arg))) id) 0)
             (((fn [func] (fn [arg1] (func arg1))) id) 0))))))

(deftest test-eta-reduction
  (testing "eta reduction is done correctly"
    (is (= 0
           ((fn [x] x) 0)
           ((fn [name] ((fn [x] x) name)) 0)))))
