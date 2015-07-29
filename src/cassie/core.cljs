(ns cassie.core
  (:require [garden.core :refer [css]]))

(def custom-style-block-name "cassie-css")

(defn add-stylesheet!
  "Adds the stylesheet from the given URI"
  [uri]
  (if (not-any? #(= % uri)
                (map-indexed (fn [idx]
                               (let [sheet (aget (.. js/document -styleSheets) idx)]
                                 (.. sheet -href)))
                             (range (.. js/document -styleSheets -length))))
    (let [link (.createElement js/document "link")]
      (.setAttribute link "href" uri)
      (.setAttribute link "type" "text/css")
      (.setAttribute link "rel" "stylesheet")
      (.appendChild (.. js/document -head) link))))

(defn remove-all-styles!
  "Removes all styles from the injected stylesheet"
  []
  (if-let [style-el (.getElementById js/document custom-style-block-name)]
    (set! (.. style-el -innerText) nil)))

(defn set-style!
  "Adds a CSS style to the current page"
  [style]
  (if-not (.getElementById js/document custom-style-block-name)
    (let [style-el (.createElement js/document "style")]
      (.setAttribute style-el "id" custom-style-block-name)
      (.setAttribute style-el "type" "text/css")
      (.appendChild (.. js/document -head) style-el)))
  (let [style-el (.getElementById js/document custom-style-block-name)
        new-inner-text (css {:pretty-print? false} style)]
    (set! (.. style-el -innerText) new-inner-text)))
