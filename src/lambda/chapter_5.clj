(ns lambda.chapter-5
  (:require [lambda.chapter-2 :refer
             [select-first select-second]]
            [lambda.chapter-3 :refer
             [zero succ]]
            [lambda.chapter-4 :refer
             [equal-2]]))

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
(def is-type
  (fn [type]
    (fn [obj] ((equal-2 (+type obj)) type))))
