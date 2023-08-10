(ns whamtet.main
  (:require
   [cljs.build.api :as b]
   [clojure.java.shell :refer [sh]]))

(defn -main [& [arg]]
  ((if (= "-w" arg) b/watch b/build)
   "src"
   {:closure-output-charset "us-ascii"
    :optimizations :simple
    :output-to "extension/main.js"
    :output-dir "extension"
    :browser-repl false
    :main 'whamtet.chrome-extension
    :output-wrapper "if (!window.xx) {xx = true;%s}"
    :watch-fn #(prn (sh "tput" "bel"))}))
