package com.futurice.rxandroidtest;

import java.util.List;

import rx.Observable;
import rx.Scheduler;

public class RxAndroidExercise {
    public static Observable<List<Crane>> makeCranes1(Observable<Paper> paperObservable,
                                                      Scheduler scheduler) {
        return Observable.empty();
    }

    public static Observable<List<Crane>> makeCranes2(Observable<Paper> paperObservable,
                                                      Scheduler scheduler) {
        return Observable.empty();
    }

    public static Observable<List<Crane>> makeCranes3(Observable<Paper> paperObservable,
                                                      Scheduler scheduler) {
        return Observable.empty();
    }

    public static Observable<List<Crane>> makeCranes4(Observable<List<Paper>> paperListObservable,
                                                      Scheduler scheduler) {
        return Observable.empty();
    }
}
