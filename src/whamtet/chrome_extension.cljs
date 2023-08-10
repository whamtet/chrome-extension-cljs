(ns whamtet.chrome-extension
  (:require
    [whamtet.client :as client])
  (:require-macros
    [whamtet.build :as build]))

(enable-console-print!)

(defn post-readability [uuid]
  (client/POST "/readability" {:uuid uuid
                               :text js/document.body.innerText}))

(build/defexport listen-for-events []
  (js/chrome.runtime.onMessage.addListener post-readability))

(build/do-spit-manifest)
