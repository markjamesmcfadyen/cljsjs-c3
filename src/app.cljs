(ns app
  (:require [cljsjs.c3]
            [goog.events :as events]
            [goog.dom :as dom]
            [goog.ui.ComboBox :as combo-box]))

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

   :data   {:x "date"
            :columns [["date" "2015-01-01" "2015-02-01" "2015-03-01" "2015-04-01" "2015-05-01" "2015-06-01" "2015-07-01" "2015-08-01" "2015-09-01" "2015-10-01" "2015-11-01" "2015-12-01" "2016-01-01" "2016-02-01" "2016-03-01" "2016-04-01"]
                      ["data1" 20 30 40 50 60 70 80 90 100 120 120 130 140 150 160 170]]
           :type "bar"
   }

   :axis {
          :x {
              :type "timeseries"
              :tick {
                     :count 12
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

    :zoom {
         :enabled true
         }

   :grid {
          :x {
          :show true
             }
          :y {
          :show true
             }}})

(def old-dates
  { :columns [["date" "2015-01-01" "2015-02-01" "2015-03-01" "2015-04-01" "2015-05-01" "2015-06-01" "2015-07-01" "2015-08-01" "2015-09-01" "2015-10-01" "2015-11-01" "2015-12-01" "2016-01-01" "2016-02-01" "2016-03-01" "2016-04-01"]
              ["data1" 20 30 40 50 60 70 80 90 100 120 120 130 140 150 160 170]
              ["data2" 30 200 100 130 150 250 30 200 100 130 150 250 30 200 100]]
    :unload ["data4" "data3" "new-data1" "new-data2" "date"]})

(def new-dates
  { :columns [["date" "2016-05-01" "2016-06-01" "2016-07-01" "2016-08-01" "2016-09-01" "2016-10-01" "2016-11-01" "2016-12-01" "2017-01-01" "2017-02-01" "2017-03-01" "2017-04-01" "2017-05-01" "2017-06-01" "2017-07-01" "2017-08-01"]
              ["new-data1" 20 30 40 50 60 70 80 90 100 120 120 130 140 150 160 170]
              ["new-data2" 20 30 40 50 10 20 60 80 90 100 20 40 60 100 120 130]]
    :unload ["data1" "data2" "data3" "data4" "date"]})

(def six-months
  {:columns [["date" "2015-01-01" "2015-02-01" "2015-03-01" "2015-04-01" "2015-05-01" "2015-06-01"]
              ["data3" 20 30 40 50 60 70 80 90 100 120 120 130 140 150 160 170]
              ["data4" 30 200 100 130 150 250 30 200 100 130 150 250 30 200 100]]
   :unload ["data1" "data2" "new-data1" "new-data2" "date"]
   :axis {
          :x {
              :type "timeseries"
              :tick {
                     :count 6
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
         }})

(defonce chart (atom nil))

(defn init []
  (prn chart-data)
  (reset! chart (js/c3.generate (clj->js chart-data)))
  (js/setTimeout (fn [] (.load @chart (clj->js old-dates)))1000)

  (let [button  (dom/getElement "button")]
    (events/listen button "click"
                   (fn [] (.load @chart (clj->js new-dates)))))

  (let [button  (dom/getElement "button2")]
    (events/listen button "click"
                   (fn [] (.load @chart (clj->js old-dates)))))

  (let [button  (dom/getElement "button6")]
    (events/listen button "click"
                   (fn [] (.load @chart (clj->js six-months)))))

  (let [button (dom/getElement "button3")]
    (events/listen button "click"
                  (fn [] (.transform @chart (clj->js "line")))))

  (let [button (dom/getElement "button4")]
    (events/listen button "click"
                  (fn [] (.transform @chart (clj->js "bar")))))


  (let [el (dom/getElement "combo1")
        cb (goog.ui.ComboBox.)
        caption (goog.ui.ComboBoxItem. "Transform Graph")]
    (doto cb
      (.setUseDropdownArrow true)
      (.setDefaultText "Transform Graph")
      (.addItem (goog.ui.ComboBoxItem. "Line"))
      (.addItem (goog.ui.ComboBoxItem. "Bar"))
      (.render el))
    (doto caption
      (.setSticky true)
      (.setEnabled true))))























