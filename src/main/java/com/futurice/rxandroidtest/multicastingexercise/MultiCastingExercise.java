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
     *
     * This observable send each item of the array every 50ms
     */

    public static Observable<Integer> sendDelayedItem(Scheduler scheduler, List<Integer> array) {
        return Observable.from(array)
                .concatMap(id -> Observable.just(id).delay(50, TimeUnit.MILLISECONDS, scheduler))
                .doOnNext(i -> count++);
    }


    /**
     * Multicast, start the emission when you have two subscriber, and share the items among the subscribers.
     *
     * When your observable has finished emitting all the items of the array, he wont emmit a thing even if
     * a later subsriber subscribes.
     *
     * @param scheduler
     * @param array
     * @return
     */
    public static Observable<Integer> getMulticastInteger(Scheduler scheduler, List<Integer> array) {
        return sendDelayedItem(scheduler, array)
                //". write here right combination of operator : publish/autoConnect/refcount/replay"
                ;
    }


    /**
     * Multicast, start the emission as soon as a subscriber subscribe, and share the items among the subscribers currently connected.
     *
     * When your observable has finished emitting all the items of the array, if
     * a later subsriber subscribes, the observable we ll start the work again.
     *
     * @param scheduler
     * @param array
     * @return
     */
    public static Observable<Integer> getMulticast2Integer(Scheduler scheduler, List<Integer> array) {
        return sendDelayedItem(scheduler, array)
                //". write here right combination of operator : publish/autoConnect/refcount/replay"
                ;
    }


    /**
     * Multicast, start the emission as soon as a subscriber subscribe, and share the items among the subscribers currently connected.
     *
     * When your observable has finished emitting all the items of the array, if
     * a later subsriber subscribes, the observable we ll replay the sequence that he has emitted, keeping it in memory.
     *

     * @param scheduler
     * @param array
     * @return
     */
    public static Observable<Integer> getMulticast3Integer(Scheduler scheduler, List<Integer> array) {
        return sendDelayedItem(scheduler, array)
                //". write here right combination of operator : publish/autoConnect/refcount/replay"
                ;
    }
}
