(ns whamtet.build
  (:require
   [clojure.data.json :as json]
   [clojure.java.io :as io])
  (:import
   java.io.File))

(defn write-str [s]
  (json/write-str s :escape-slash false))

(defn format-map [s m]
  (reduce
   (fn [s [k v]]
     (.replace s (str k) v))
   s
   m))

(def dev?
  (some #(= "-w" %) *command-line-args*))

(defn- spit-manifest []
  (spit "extension/manifest.json"
        (format
         (slurp "resources/manifest.template.json"))))

(defn- copy-resources []
  (doseq [f ["logo.png"]]
    (io/copy
     (File. (str "resources/" f))
     (File. (str "extension/" f)))))

(defmacro do-spit-manifest
  "Part of the build Process"
  []
  (spit-manifest)
  (copy-resources)
  nil)
