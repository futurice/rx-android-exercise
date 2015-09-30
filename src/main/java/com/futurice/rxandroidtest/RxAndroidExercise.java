package com.futurice.rxandroidtest;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class RxAndroidExercise {
    public static Observable<List<Crane>> makeCranes1(Observable<Paper> paperObservable,
                                                      Scheduler scheduler) {
        return paperObservable
                .flatMap(paper -> Crane.fromPaper(paper, scheduler))
                .toList();
    }

    public static Observable<List<Crane>> makeCranes2(Observable<Paper> paperObservable,
                                                      Scheduler scheduler) {
        return paperObservable
                .concatMap(paper -> Crane.fromPaper(paper, scheduler))
                .toList();
    }

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
