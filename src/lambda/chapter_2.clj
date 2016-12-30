(ns lambda.chapter-2)

;; Chapter 2

;;; β reduction

;; identity function
;;
;; Note that `identity` is renamed to `id` to
;; avoid naming conflict with `#'clojure.core/apply`
(def id (fn [x] x))

;; self applying function
(def self-apply (fn [s] (s s)))

;; function applying function
;;
;; Note that `apply` is renamed to `f-apply` to
;; avoid naming conflict with `#'clojure.core/apply`
(def f-apply
  (fn [func]
    (fn [arg]
      (func arg))))

(def id-2
  (fn [x] ((f-apply id) x)))

(def self-apply-2
  (fn [x] ((f-apply x) x)))

;; a function to select the first of two arguments
(def select-first
  (fn [first]
    (fn [second] first)))

;; a function to select the second of two arguments
(def select-second
  (fn [first]
    (fn [second] second)))

;; a function to make a pair of its two arguments
(def make-pair
  (fn [first]
    (fn [second]
      (fn [func] ((func first) second)))))

;;; for free/bound symbols, α conversion
;;; and η reduction, check chapter_2_test.clj
