(ns lambda.chapter-5
  (:require [lambda.chapter-2 :refer
             [select-first select-second]]
            [lambda.chapter-3 :refer
             [+true +false +not +and zero succ +if]]
            [lambda.chapter-4 :refer
             [equal-2]]))

;;; Chapter 5

;; note that typed functions and values are
;; prepended with `t-` instead of being shown
;; in upper case

;; function to create an object of a
;; specified type and value
(def make-obj
  (fn [type]
    (fn [value]
      (fn [s] ((s type) value)))))

(def +type (fn [obj] (obj select-first)))

(def +value (fn [obj] (obj select-second)))

;; function to check if an object
;; matches a given type
(def is-type?
  (fn [type]
    (fn [obj] ((equal-2 (+type obj)) type))))

;;; Errors

(def error-type zero)
(def is-error? (is-type? error-type))
(def t-make-error (make-obj error-type))

(def t-error (t-make-error error-type))

;;; Booleans

(def bool-type (succ zero))
(def is-bool? (is-type? bool-type))
(def t-make-bool (make-obj bool-type))

(def t-true (t-make-bool +true))
(def t-false (t-make-bool +false))
(def t-bool-error (t-make-error bool-type))

(def t-not
  (fn [X] (+if (is-bool? X)
              (t-make-bool (+not (+value X)))
              t-bool-error)))

(def t-and
  (fn [X]
    (fn [Y] (+if ((+and (is-bool? X)) (is-bool? Y))
                (t-make-bool ((+and (+value X)) (+value Y)))
                t-bool-error))))
