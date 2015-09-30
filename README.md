Futurice RxJava exercise
========================

You can run the attached unit test and see it fail:

`./gradlew test`


Your job is to implement the missing functions in RxAndroidExercise. Their written specifications
are as follows:

  1. Make an RxJava chain that uses Crane.fromPaper to process the paperStack and at the end call
  subscriber with the finished list. Give the Crane.fromPaper function the same scheduler that the
  function receives. You do not need to worry about in which order the cranes are.

  2. Because some colors more difficult to fold than others, the execution time of Crane.fromCrane
  varies. Make sure your solution retains the order. If the papers are in list
  ["yellow", "orange", "red"] the resulting list of cranes should be so that i. e. first crane is if
  color "yellow".

  3. We need to fold faster! Make all makeCrane operations run simultanously while still retaining
  the order in which they papers given.

  4. Make the chain work with a source of type Observable<List<Paper>> that does not complete. The
  time limit is the same as for function 3.
