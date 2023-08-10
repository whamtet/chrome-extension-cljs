(ns whamtet.chrome-extension
  (:require-macros
    [whamtet.build :as build]))

(enable-console-print!)

(build/defexport print []
  ;; add listener
  (println "prints"))

(build/do-spit-manifest)
