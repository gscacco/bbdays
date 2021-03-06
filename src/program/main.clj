(ns program.main
  (:require [utils.core :as u]
            [clojure.tools.cli :refer [parse-opts]]))

(def cli-options
  [["-s" "--start-date SDATE" "Start date"
    :parse-fn u/parse-date]
   ["-e" "--end-date EDATE" "End date"
    :parse-fn u/parse-date]
   ["-r" "--rate RATE" "Hourly rate"
    :parse-fn parse-double]
   ["-h" "--help"]])

(defn -main [& args]
  (let [parsed (parse-opts args cli-options)
        summary (:summary parsed)
        options (:options parsed)
        s-date (:start-date options)
        e-date (:end-date options)
        rate (:rate options)
        help-condition (or (:help options) (not (and s-date e-date)))]
    (if help-condition
      (println "Usage:\n" summary)
      (let [days (u/days-between s-date e-date)
            wd (u/working-days-between s-date e-date)]
        (println "Days:\t \t" days)
        (println "Working days:\t" wd)
        (when rate
          (println "Total cost:\t" (* rate 8 wd)))))))

(comment
  (parse-double "40")
  (-main "-s" "2022/04/01" "-e" "2022/10/31" "-r" "40.0")
  (-main "-s" "2022/04/01")
  (-main "-h")
  )
