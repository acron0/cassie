# cassie

ClojureScript librarty for generating and injecting CSS at runtime. Uses [Garden](https://github.com/noprompt/garden) for CSS rendering.

## Usage

Not on clojars yet, so glone the repo and `lein install`

### Add a stylesheet
```clojure
(require '[cassie.core :as cass])
(cass/add-stylesheet! "http://yui.yahooapis.com/pure/0.6.0/pure-min.css")
```

### Inject a style
```clojure
(require '[cassie.core :as cass])
(cass/set-style! [:html {:background-color "#fff"}
                    :h1 {:font-size "3em"}]))
```

## License

Copyright © 2015 Antony Woods

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
