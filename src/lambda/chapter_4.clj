(ns lambda.chapter-4
  (:require [lambda.chapter-3 :refer
             [+true +false +not +and +or
              zero one is-zero? succ pred]]))

;;; Chapter 4

;; function to add two natural numbers
;; using primitive recursion
(def add-1
  (fn [x]
    (fn [y] (if (= +true (is-zero? y))
             x
             ((add-1 (succ x)) (pred y))))))

;; function to add two natural numbers
;; using general recursion
(def add-2'
  (fn [f]
    (fn [x]
      (fn [y] (if (= +true (is-zero? y))
               x
               (((f f) (succ x)) (pred y)))))))

(def add-2 (add-2' add-2'))

;; function to multiply two natural numbers
;; using primitive recursion
(def mult-1
  (fn [x]
    (fn [y] (if (= +true (is-zero? y))
             zero
             ((add-1 x) ((mult-1 x) (pred y)))))))

;; function to pass `f` to a recursive
;; call to itself
;; (clone of s-apply from chapter 2)
(def recursive (fn [f] (f f)))

;; function to multiply two natural numbers
;; using abstracted general recursion
(def mult-2'
  (fn [f]
    (fn [x]
      (fn [y] (if (= +true (is-zero? y))
               zero
               ((add-1 x) (((recursive f) x) (pred y))))))))

(def mult-2 (recursive mult-2'))

;; function to pass `f` to a recursive
;; call to itself using a 0-arg closure
(def recursive-2
  (fn [f] (f (fn [] (recursive-2 f)))))

;; function to multiply two natural numbers
;; using another form of abstracted general recursion
(def mult-3'
  (fn [f]
    (fn [x]
      (fn [y] (if (= +true (is-zero? y))
               zero
               ((add-1 x) (((f) x) (pred y))))))))

(def mult-3 (recursive-2 mult-3'))

;; function to find power of a number
(def power
  (fn [x]
    (fn [y] (if (= +true (is-zero? y))
             one
             ((mult-1 x) ((power x) (pred y)))))))

;; function to find difference between
;; two numbers
(def sub
  (fn [x]
    (fn [y] (if (= +true (is-zero? y))
             x
             ((sub (pred x)) (pred y))))))

(def abs-diff
  (fn [x]
    (fn [y] ((add-1 ((sub x) y)) ((sub y) x)))))

;; function to check if two numbers are equal
(def equal-1
  (fn [x]
    (fn [y] (is-zero? ((abs-diff x) y)))))

;; function to check if two numbers are equal
;; using recursion
(def equal-2
  (fn [x]
    (fn [y] (if (= +true ((+and (is-zero? x))
                         (is-zero? y)))
             +true
             (if (= +true ((+or (is-zero? x))
                           (is-zero? y)))
               +false
               ((equal-2 (pred x)) (pred y)))))))

;; function to check if a number is greater
;; than another number
(def greater
  (fn [x]
    (fn [y]
      (+not (is-zero? ((sub x) y))))))

;; function to check if a number is greater
;; than or equal to another number
(def greater-or-equal
  (fn [x]
    (fn [y]
      (is-zero? ((sub y) x)))))

(def div'
  (fn [x]
    (fn [y] (if (= +true ((greater y) x))
             zero
             (succ ((div' ((sub x) y)) y))))))

;; function to divide two numbers
;; note that any number divided by
;; zero is defined as zero
(def div
  (fn [x]
    (fn [y] (if (= +true (is-zero? y))
             zero
             ((div' x) y)))))
