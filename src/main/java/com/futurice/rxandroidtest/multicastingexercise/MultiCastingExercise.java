package com.futurice.rxandroidtest.multicastingexercise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by gval on 12/08/2016.
 */
public class MultiCastingExercise {
    Schedulers schedulers;
    static int count = 0;

    /**
     * ./gradlew test --tests com.futurice.rxandroidtest.multicastingexercise.*
     */

    /**
     * In ALl exercise use a combination of publish/autoConnect/refcount/replay to make this observable
     * multicasting in the way you want.
     */

    public static Observable<Integer> sendDelayedItem(Scheduler scheduler, List<Integer> array) {
        return Observable.from(array)
                .doOnNext(i -> count++)
                .delay(200, TimeUnit.MILLISECONDS, scheduler);
    }


    /**
     * Multicast, start the emission when you have two subscriber, when your observable is finished emiting
     * if another subscriber subscribe he won't receive anything
     * @param scheduler
     * @param array
     * @return
     */
    public static Observable<Integer> getMulticastInteger(Scheduler scheduler, List<Integer> array) {
        return sendDelayedItem(scheduler, array)
                .publish().autoConnect(2);
    }


    /**
     * start the emission when as soon as you have one subscriber, when your observable is finished emiting
     * if another subscriber subscribe it will re-trigger the observable and emit.
     * @param scheduler
     * @param array
     * @return
     */
    public static Observable<Integer> getMulticast2Integer(Scheduler scheduler, List<Integer> array) {
        return sendDelayedItem(scheduler, array)
                .publish().refCount();
    }


    /**
     * Multicast, multicast as soon as one observer subscribes, if a later subscriber subscribe, he gets all the past events
     * @param scheduler
     * @param array
     * @return
     */
    public static Observable<Integer> getMulticast3Integer(Scheduler scheduler, List<Integer> array) {
        return sendDelayedItem(scheduler, array)
                .replay().autoConnect();
    }

    public static Observable<Integer> getMulticast4Integer(Scheduler scheduler, List<Integer> array) {
        return sendDelayedItem(scheduler, array)
                .replay().refCount();
    }

}
