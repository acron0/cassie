(ns cassie.core
  (:require [garden.core :refer [css]]))

(def cassie-stylesheet-name "cassie-css")

(defn- get-cassie-stylesheet
  []
  (let [style-el (.getElementById js/document cassie-stylesheet-name)]
    (first (filter #(not (nil? %)) (map-indexed (fn [idx]
                                  (let [sheet (aget (.. js/document -styleSheets) idx)]
                                    (if (= (.-ownerNode sheet) style-el)
                                      sheet)))
                                (range (.. js/document -styleSheets -length)))))))

(defn- create-cassie-stylesheet!
  []
  (let [style-el (.createElement js/document "style")]
    (.setAttribute style-el "type" "text/css")
    (.setAttribute style-el "id" cassie-stylesheet-name)
    (.appendChild (.. js/document -head) style-el)
    (.-sheet style-el)))

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

(defn set-style!
  "Adds a CSS style to the current page"
  [styles]
  (if (nil? (get-cassie-stylesheet))
    (create-cassie-stylesheet!))
  (let [old-ss (get-cassie-stylesheet)
        ss (if (nil? ss) (create-cassie-stylesheet!) old-ss)]
    (doseq [idx (range (count styles))]
      (.insertRule ss (css {:pretty-print false} (nth styles idx)) idx))))
