(ns clojure-truebrave.chapter3
  (:require
   [clojure.string :as str]))

(defn symmetrize-part
  "Symmetrize one part"
  [{:keys [name] :as part}]
  (let [new-name
        (cond
          (str/includes? name "right") (str/replace name "right" "left")
          (str/includes? name "left") (str/replace name "left" "right")
          :else name)]
    (if (= new-name name)
      part
      [part (assoc part :name new-name)])))

(defn symmetrize
  "Symmetric multiple parts"
  [parts]
  (-> (map symmetrize-part parts) flatten))

(defn my-reduce
  "Reduce an array of values into one value with the help of one function"
  ([f init ar]
   (loop [result init
          remain ar]
     (if (empty? remain)
       result
       (recur (f result (first remain)) (rest remain)))))
  ([f [head & remain]]
   (my-reduce f head remain)))

(my-reduce + 0 [1 2])

(defn hit
  "Hit a part of a human"
  ([human damage]
   (let [sum-of-parts (reduce #(+ %1 (:size %2)) 0 human)
         to-hit damage
         ]
     (loop [[head & remain] human
            accumulated 0
            last nil
            ]
       (let
           []
         (if (or (nil? head) (> (+ accumulated (:size head)) to-hit))
           last
           (recur remain (+ accumulated (:size head)) head)))))))

(hit [{:size 2 :name "eye"} {:size 3 :name "head"} {:size 1 :name "feet"}] 2)


