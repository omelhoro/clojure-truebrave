(ns clojure-truebrave.chapter3_test
  (:require
   [clojure.test :refer :all]
   clojure.data
   [clojure-truebrave.chapter3 :refer :all]
   ))

(def test-body-unsym [
                      {:name "left-arm" :size 4}
                      {:name "head" :size 1}
                      ])
(def test-body-sym [
                    {:name "left-arm" :size 4}
                    {:name "right-arm" :size 4}
                    {:name "head" :size 1}
                    ])

(deftest symmetric-test
  (testing "should give a symmetric person"
    (let [
          in test-body-unsym
          out test-body-sym
          ]
      (is (= (symmetrize in) out)))))

(deftest my-reduce-test
  (testing "If my reduce works"
    (is (= 3 (my-reduce + [1 2]))))
  (testing "If my reduce works with initial values"
    (is (= 3 (my-reduce + 0 [1 2]))))
  )


(deftest hit-test
  (testing "Should hit a part of body"
    (is (= (hit test-body-sym 99) (last test-body-sym))))
  (testing "Should hit nothing"
    (is (= (hit test-body-sym 0) nil)))
  (testing "Should hit head"
    (is (= (hit test-body-sym 4) (first test-body-sym))))
  )
