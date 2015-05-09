(ns ^:figwheel-always hello-world.core
    (:require
              [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))
(def state (atom {:clicked-times 0
                  :color "#ff0000"}))

;; (def body js/document (getElementsByTagName "body"))

(defn change-body-color []
  [:button {:on-click #(swap! (:clicked-times state) inc)}
   "Change body Color " (:clicked-times @state)])

(defn hello-world []
  [:h1 (:text @app-state)])

(reagent/render-component [change-body-color]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)