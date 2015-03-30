(set-env!
 :source-paths   #{"src"}
 :resource-paths #{"resources"}
 :dependencies '[[adzerk/boot-cljs          "0.0-2814-3"     :scope "test"]
                 [adzerk/boot-reload        "0.2.4"          :scope "test"]
                 [pandeiro/boot-http        "0.6.3-SNAPSHOT" :scope "test"]
                 [cljsjs/boot-cljsjs        "0.4.6"]
                 [org.clojure/clojure       "1.7.0-alpha5"]
                 [org.clojure/clojurescript "0.0-3058"]
                 [cljsjs/d3                 "3.5.5-1"]
                 [cljsjs/c3                 "0.4.9-1"]])

(require
 '[adzerk.boot-cljs   :refer [cljs]]
 '[adzerk.boot-reload :refer [reload]]
 '[cljsjs.boot-cljsjs :refer [from-cljsjs]]
 '[pandeiro.boot-http :refer [serve]])

(task-options!
  serve {:dir     "./target/"
         :httpkit true})

(deftask dev []
  (comp (serve)
        (watch)
        (reload :on-jsload 'app/init)
        (sift :add-jar {'cljsjs/c3 #"^cljsjs/c3/common/c3.min.css$"})
        (cljs
          :optimizations    :none
          :source-map       true
          :unified-mode     true
          :compiler-options {:warnings {:single-segment-namespace false}})))
