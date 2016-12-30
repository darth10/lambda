(ns lambda.chapter-3
  (:require [lambda.chapter-2 :refer
             [id select-first select-second make-pair]]))

;;; Chapter 3


;;; Boolean operators

;; Note that functions for Boolean operators are
;; prepended with '+' to avoid naming conflicts with
;; literals and functions from the clojure.core namespace

;; conditional expression function
(def +cond make-pair)

;; functions for true and false Boolean literals
(def +true select-first)
(def +false select-second)

;; NOT operator function
;;
;; derived from 'x ? true : false'

(def +not'
  (fn [x] (((+cond +false) +true) x)))

(def +not
  (fn [x] ((x +false) +true)))

;; AND operator function
;;
;; derived from 'x ? y : false'

(def +and'
  (fn [x]
    (fn [y] (((+cond y) +false) x))))

(def +and
  (fn [x]
    (fn [y] ((x y) +false))))

;; OR operator function
;;
;; derived from 'x ? true : y'

(def +or'
  (fn [x]
    (fn [y] (((+cond +true) y) x))))

(def +or
  (fn [x]
    (fn [y] ((x +true) y))))


;;; Natural numbers

(def zero id)

;; function to check if a number is zero
(def is-zero?
  (fn [n] (n select-first)))

;; function for successor of a number
(def succ
  (fn [n]
    (fn [s] ((s +false) n))))

;; function for predecessor of a number
;;
;; derived from '(def pred1 (fn [n] (n select-second)))' and
;; '(def pred (fn [n] (((+cond zero) (pred1 n)) (is-zero n))))'
(def pred
  (fn [n] (((is-zero? n) zero) (n select-second))))

(def one (succ zero))
(def two (succ (succ zero)))
(def three (succ (succ (succ zero))))
;; ...

;; helper function to check difference
;; between a number and zero
(defn from-zero [diff n]
  (if (= diff 0)
    (is-zero? n)
    (let [n' (pred n)]
      (if (= n n') ;; already at `zero`
        +false
        (recur (dec diff) n')))))
