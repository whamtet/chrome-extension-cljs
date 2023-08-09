(ns whamtet.chrome-extension
  (:require-macros
    [whamtet.build :as build]))

(build/do-spit-manifest)

(enable-console-print!)
(println "hello")
