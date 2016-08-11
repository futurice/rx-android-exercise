Futurice RxJava exercise
========================


Your job is to implement the exercises grouped in each packages

You can run all the unit test and see it fail:

`./gradlew test`

or do it per exercise and run test by packages:

`./gradlew test --tests com.futurice.rxandroidtest.craneexercise.*`

## Crane exercise

 1. Make an RxJava chain that uses `Crane.fromPaper` to process the `paperStack` and at the end call
  subscriber with the finished list. Give the `Crane.fromPaper` function the same scheduler that the
  function receives. You do not need to worry about in which order the cranes are.

  2. Because some colors more difficult to fold than others, the execution time of `Crane.fromCrane`
  varies. Make sure your solution retains the order. If the papers are in list
  `["yellow", "orange", "red"]` the resulting list of cranes should be so that i. e. first crane is if
  color `"yellow"`.

  3. We need to fold faster! Make all `makeCrane` operations run simultanously while still retaining
  the order in which the papers are given.

  4. Make the chain work with a source of type `Observable<List<Paper>>` that does not complete. The
  time limit is the same as for function 3.

## article Exercise

 1. Create an observable which combine two source of ids, for each id, fetch the actual article.


