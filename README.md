# cassie

ClojureScript library for generating and injecting CSS at runtime. Uses [Garden](https://github.com/noprompt/garden) for CSS rendering.

## Usage

Not on clojars yet, so clone the repo and `lein install`.
Then use:

```
[cassie "0.1.0-SNAPSHOT"]
```

### Add a stylesheet
```clojure
(require '[cassie.core :as cass])
(cass/add-stylesheet! "http://yui.yahooapis.com/pure/0.6.0/pure-min.css")
```

### Inject a style
```clojure
(require '[cassie.core :as cass])
(require '[garden.color :as color :refer [rgb]])
(def base-color (rgb 0 83 150))
(cass/set-style! [[:html {:background-color "#ccc"}]
                    [:h1 {:color base-color}]
                    [:h2 {:color (color/lighten base-color 10)}]
                    [:h3 {:color (color/lighten base-color 30)}]]))

```

## License

Copyright © 2015 Antony Woods/MastodonC

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
