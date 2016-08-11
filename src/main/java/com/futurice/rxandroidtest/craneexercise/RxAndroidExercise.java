package com.futurice.rxandroidtest.craneexercise;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class RxAndroidExercise {

    /**
     * Make an RxJava chain that uses Crane.fromPaper to process the paperStack and at the end call
     * subscriber with the finished list. Give the Crane.fromPaper function the same scheduler that
     * the function receives. You do not need to worry about in which order the cranes are.

     */
    public static Observable<List<Crane>> makeCranes1(Observable<Paper> paperObservable,
                                                      Scheduler scheduler) {
        return paperObservable
                .flatMap(paper -> Crane.fromPaper(paper, scheduler))
                .toList();
    }

    /**
     * Because some colors more difficult to fold than others, the execution time of Crane.fromCrane varies.
     * Make sure your solution retains the order. If the papers are in list ["yellow", "orange", "red"]
     * the resulting list of cranes should be so that i. e. first crane is if color "yellow".
     * @param paperObservable
     * @param scheduler
     * @return
     */
    public static Observable<List<Crane>> makeCranes2(Observable<Paper> paperObservable,
                                                      Scheduler scheduler) {
        return paperObservable
                .concatMap(paper -> Crane.fromPaper(paper, scheduler))
                .toList();
    }

    /**
     * We need to fold faster! Make all makeCrane operations run simultanously
     * while still retaining the order in which the papers are given.
     * @param paperObservable
     * @param scheduler
     * @return
     */
    public static Observable<List<Crane>> makeCranes3(Observable<Paper> paperObservable,
                                                      Scheduler scheduler) {
        return paperObservable
                .map(new Func1<Paper, Pair<Integer, Paper>>() {
                    int index = 0;

                    @Override
                    public Pair<Integer, Paper> call(Paper paper) {
                        // Collect the index of this item, we will use it to retain the order later.
                        return new Pair<>(index++, paper);
                    }
                })
                .flatMap(pair -> Crane
                        .fromPaper(pair.second, scheduler)
                        .map(crane -> new Pair<>(pair.first, crane)))
                .toSortedList((a, b) -> Integer.compare(a.first, b.first))
                .flatMap(Observable::from)
                .map(pair -> pair.second)
                .toList();
    }

    /**
     * Make the chain work with a source of type Observable<List<Paper>> that does not complete.
     * The time limit is the same as for function 3.
     * @param paperListObservable
     * @param scheduler
     * @return
     */
    public static Observable<List<Crane>> makeCranes4(Observable<List<Paper>> paperListObservable,
                                                      Scheduler scheduler) {
        return paperListObservable.flatMap(
                papers -> makeCranes3(Observable.from(papers), scheduler));
    }

    public static class Pair<T, U> {
        final public T first;
        final public U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }
}
