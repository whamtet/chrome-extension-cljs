(ns whamtet.chrome-extension
  (:require-macros
    [whamtet.build :as build]))

(enable-console-print!)

(build/defexport notify-text []
  (js/chrome.runtime.sendMessage js/document.body.innerText))

(build/do-spit-manifest)
