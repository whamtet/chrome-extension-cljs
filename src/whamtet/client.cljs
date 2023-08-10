(ns whamtet.client
  (:require
   [ajax.core :as ajax])
  (:require-macros
   [whamtet.build :refer [get-host]]))

(def host (get-host))

(defn request [verb url params]
  (js/Promise.
   #(verb (str host "/api" url)
          {:params params
           :with-credentials true
           :handler %1
           :error-handler %2})))

(defn POST [url params]
  (request ajax/POST url params))
