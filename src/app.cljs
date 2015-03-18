(ns app
  (:require cljsjs.c3))

(def chart-data
  {:bindto "#chart"
   :data   {:columns [["data1" 30 200 100 400 150 250]
                      ["data2" 50 20 10 40 15 25]]}})

(enable-console-print!)

(defn init []
  (prn chart-data)
  (js/c3.generate (clj->js chart-data)))
