(ns app
  (:require cljsjs.c3))

(enable-console-print!)

(def chart-data
  {:bindto "#chart"
   :data   {:columns [["data1" 30 200 100 400 150 250]
                      ["data2" 50 20 10 40 15 25]]}})

(def extra-chart-data
  {:columns [["data3" 400 500 450 700 600 500]]})

(defonce chart (atom nil))

(defn init []
  (prn chart-data)
  (reset! chart (js/c3.generate (clj->js chart-data)))
  (js/setTimeout (fn [] (.load @chart (clj->js extra-chart-data)))))
