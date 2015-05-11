(ns ^:figwheel-always hello-world.core
    (:require
              [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))
(def state (atom {:clicked-times 0
                  :color "white"}))


(def colors ["white" "red" "blue" "black" "yellow"])

(defn swap-values []
  (do
    (swap! state assoc :color (nth colors (rand-int 4)))
    (set! (.-backgroundColor (.-style (.-body js/document))) (str (@state :color)))
    #(swap! state update-in [:clicked-times] inc))          ;;what is this # for?
  ;; Why can't I put the 'set!' on the last position? (button disappears!)
  )

(defn change-color []
  (if (< (@state :clicked-times) 10)
    (swap-values)))

(defn change-body-color []
  [:button {:on-click (change-color)}
   "Change body Color " (:color @state)]
  )



(defn hello-world []
  [:h1 (:text @app-state)])

(reagent/render-component [change-body-color]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)