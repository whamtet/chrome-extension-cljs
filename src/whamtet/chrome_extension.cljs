(ns whamtet.chrome-extension
  (:require-macros
    [whamtet.build :as build]))

(enable-console-print!)

(build/defexport print []
  ;; add listener
  (js/chrome.action.setPopup
    #js {:popup "popup.html"}))

(build/do-spit-manifest)
