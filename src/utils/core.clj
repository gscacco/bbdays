(ns utils.core
  (:use [hiccup.core])
  (:import 'java.time.LocalDateTime
           'java.time.temporal.ChronoField
           'java.time.temporal.ChronoUnit)
  (:require [clojure.java.shell :refer [sh]]
            [clojure.tools.cli :refer [parse-opts]]))


(defn get-date [y m d]
  "Returns new date"
  (.of LocalDateTime y m d 0 0))

(defn day-of-week [date]
  "Returns the day of week"
  (let [dow (.DAY_OF_WEEK ChronoField)]
    (.get date dow)))

(defn days-between [s e]
  "Returns the days between s (starting from) and e (ending to)"
  (let [days (.DAYS ChronoUnit)]
    (.between days s e)))

(defn working-days-between [s e]
  "Returns the working days between s and e"
  (let [days (days-between s e)
        dow (day-of-week s)
        l-days (->> (range 1 days)
                    (map #(inc (mod (+ 7 %) 7)))
                    (filter #(< % 6)))]
    (count l-days)))
