(ns app
  (:require cljsjs.c3))
(enable-console-print!)

(def chart-data
  {:bindto "#chart"
   :padding {
             :right 40
             :top 40
             :bottom 40

             }
   :size {
          :height 600
          :width 1000
          }
   :data   {:x "x"
            :columns [["x" "2015-01-01" "2015-02-01" "2015-03-01" "2015-04-01" "2015-05-01" "2015-06-01" "2015-07-01" "2015-08-01" "2015-09-01" "2015-10-01" "2015-11-01" "2015-12-01" "2016-01-01" "2016-02-01" "2016-03-01" "2016-04-01"]
                       ["data101" "20 30 40 50 60 70 80 90 100 120 120 130 140 150 160 170"]]
            :type "bar"

            :types {
                    :data5 "area-spline"
                    }


            :names {
                    :data1 "Active Users"
                    :data2 "Inactive Users"
                    :data3 "Lowest Users"
                    :data4 "Highest Users"
                    :data5 "Average Users"
                    }
   }

   :zoom {
          :enabled true
          }

   :axis {
          :x {
              :type "timeseries"
              :tick {
                     :format "%Y / %m"
                     }
              :label {
                      :text "Time in Months"
                      :position "outer-center"
                      }
              }
          :y {
              :label {
                      :text "Users Online"
                      :position "outer-middle"
                      }

              }
         }

   :grid {
          :x {
          :show true
             }
          :y {
          :show true
             }

         }
    }
)

(def first-data
  { :columns [["data1" 30 200 100 130 150 250 30 200 100 130 150 250 30 200 100 130 150 250 30 200 100 130 150 250]]})


(def second-data
  { :columns [["data2" 20 10 15 18 20 49 50 20 10 15 18 20 49 50 20 10 15 18 20 49 50 20 10 15 18 20 49 50]]})

(def third-data
  { :columns [["data3" 20 60 50 40 30 60 70 80 100 20 30 10 15 20 60 40 20 70 50]]})

(def fourth-data
  { :columns [["data4" 20 60 50 40 30 60 70 80 100 20 30 10 15 20 60 40 20 70 50]]})

(def fifth-data
  { :columns [["data5" 30 30 40 50 60 80 100 20 30 40 100 90 80 40 20 40 50]]})

(defonce chart (atom nil))

(defn init []
  (prn chart-data)
  (reset! chart (js/c3.generate (clj->js chart-data)))
  (js/setTimeout (fn [] (.load @chart (clj->js first-data)))1000)
  (js/setTimeout (fn [] (.load @chart (clj->js second-data)))2000)
  (js/setTimeout (fn [] (.load @chart (clj->js third-data)))3000)
  (js/setTimeout (fn [] (.load @chart (clj->js fourth-data)))4000)
  (js/setTimeout (fn [] (.load @chart (clj->js fifth-data)))5000))

