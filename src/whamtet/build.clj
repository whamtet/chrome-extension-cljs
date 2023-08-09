(ns whamtet.build
  (:require
   [clojure.java.io :as io])
  (:import
   java.io.File))

(defn format-map [s m]
  (reduce
   (fn [s [k v]]
     (.replace s (str k) v))
   s
   m))

(def dev?
  (some #(= "-w" %) *command-line-args*))

(def host
  (if dev? "http://localhost:3000" "https://backend.molloy.link"))

(def exports #{})

(defmacro defexport [name args & body]
  (alter-var-root #'exports conj (str (ns-name *ns*) "." name))
  `(defn ~name ~args ~@body))

(defn- spit-manifest []
  (spit "extension/manifest.json"
        (format
         (slurp "resources/manifest.template.json"))))

(defn- spit-assistant-script [name]
  (spit
   (format "extension/%s.js" name)
   (-> name str (.replace "-" "_") (str "()"))))
(defn- spit-assistant-scripts []
  (doseq [name exports]
    (spit-assistant-script name)))

(defn- copy-resources []
  (doseq [f ["logo.png" "background.js"]]
    (io/copy
     (File. (str "resources/" f))
     (File. (str "extension/" f)))))

(defn- spit-frame []
  (spit
   "extension/popup.html"
   (format-map
    (slurp "resources/frame.html")
    {:host host})))

(defmacro do-spit-manifest
  "Part of the build Process"
  []
  (spit-manifest)
  (spit-assistant-scripts)
  (copy-resources)
  (spit-frame)
  nil)
