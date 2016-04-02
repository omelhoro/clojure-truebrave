(ns clojure-truebrave.chapter3_test
  (:require
   [clojure.test :refer :all]
   clojure.data
   [clojure-truebrave.chapter3 :refer :all]))

(def test-body-unsym [{:name "left-arm" :size 4}
                      {:name "head" :size 1}])
(def test-body-sym [{:name "left-arm" :size 4}
                    {:name "right-arm" :size 4}
                    {:name "head" :size 1}])

(deftest symmetric-test
  (testing "should give a symmetric person"
    (let [in test-body-unsym
          out test-body-sym]
      (is (= (symmetrize in) out)))))

(deftest my-reduce-test
  (testing "If my reduce works"
    (is (= 3 (my-reduce + [1 2]))))
  (testing "If my reduce works with initial values"
    (is (= 3 (my-reduce + 0 [1 2])))))

(deftest my-reduce-test
  (testing "If my reduce works"
    (are [x y a] (= x (my-reduce y a))
      3 + [1 2])))

(deftest hit-test
  (testing "Should hit a part of body"
    (is (= (hit test-body-sym 99) (last test-body-sym))))
  (testing "Should hit nothing"
    (is (= (hit test-body-sym 0) nil)))
  (testing "Should hit head"
    (is (= (hit test-body-sym 4) (first test-body-sym)))))

(deftest add100-test
  (testing "Should add 100"
    (are [x y] (= x (add100 y))
      200 100
      400 300
      101 1)))

(deftest dec-maker-test
  (testing "Should create a fn to decrement something"
    (are [x y z] (= x (y z))
      5 (dec-maker 5) 10
      12 (dec-maker 2) 14
      3 (dec-maker 40) 43)
    ))

(deftest mapset-test
  (testing "Mapset should work"
    (are [x y f] (= x (mapset f y))
      #{1 2 3} [0 1 0 2] inc
      #{2 3 4} [3 3 3 4 4 5] dec)))

(deftest symmetric-n-test
  (testing "Test should verify that n parts are symmetrized"
    (are [x y] (= y (symmetrize-n x))
      [{:name "right-ear"}] [{:name "right-ear"} {:name "left-ear"}])
    (are [x y n] (= y (symmetrize-n x n))
      [{:name "right-ear"} {:name "right-ear"}]
      [{:name "right-ear"} {:name "left-ear"} {:name "right-ear"} {:name "left-ear"}]
      4)))
