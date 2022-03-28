(ns utils.core
  (:import java.time.LocalDateTime
           java.time.temporal.ChronoField
           java.time.temporal.ChronoUnit))

(defn get-date
  "Returns new date"
  [y m d]
  (.of LocalDateTime y m d 0 0))

(defn day-of-week
  "Returns the day of week"
  [date]
  (let [dow (.DAY_OF_WEEK ChronoField)]
    (.get date dow)))

(defn days-between
  "Returns the days between s (starting from) and e (ending to)"
  [s e]
  (let [days (.DAYS ChronoUnit)]
    (.between days s e)))

(defn working-days-between
  "Returns the working days between s and e"
  [s e]
  (let [days (days-between s e)
        l-days (->> (range 1 days)
                    (map #(inc (mod (+ 7 %) 7)))
                    (filter #(< % 6)))]
    (count l-days)))
