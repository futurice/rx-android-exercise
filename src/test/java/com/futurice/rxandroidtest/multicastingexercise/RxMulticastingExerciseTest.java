package com.futurice.rxandroidtest.multicastingexercise;

import com.futurice.rxandroidtest.articleexercise.ExerciseArticle;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by gval on 12/08/2016.
 */
public class RxMulticastingExerciseTest {
    ExerciseArticle exerciseArticle;
    TestScheduler testScheduler;
    TestSubscriber<Integer> testSubscriber1;
    TestSubscriber<Integer> testSubscriber2;
    TestSubscriber<Integer> testSubscriber3;
    List<Integer> listToSend;

    @Before
    public void setup() {
        testScheduler = new TestScheduler();
        testSubscriber1 = new TestSubscriber<>();
        testSubscriber2 = new TestSubscriber<>();
        testSubscriber3 = new TestSubscriber<>();

        listToSend = new ArrayList<>();
        listToSend.add(1);
        listToSend.add(2);
        listToSend.add(3);
        MultiCastingExercise.count = 0;
    }


    @Test
    public void testPublish() {
        Observable<Integer> multicastObservable = MultiCastingExercise.getMulticastInteger(testScheduler, listToSend);

        multicastObservable
                .subscribe(testSubscriber1);

        multicastObservable
                .subscribe(testSubscriber2);

        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS);

        multicastObservable
                .subscribe(testSubscriber3);

        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS);

        testSubscriber1.assertReceivedOnNext(listToSend);
        testSubscriber2.assertReceivedOnNext(listToSend);
        testSubscriber3.assertNoValues();
        assertEquals(3, MultiCastingExercise.count);
    }

    @Test
    public void test2Publish() {
        Observable<Integer> multicastObservable = MultiCastingExercise.getMulticast2Integer(testScheduler, listToSend);

        multicastObservable
                .subscribe(testSubscriber1);

        multicastObservable
                .subscribe(testSubscriber2);

        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS);

        multicastObservable
                .subscribe(testSubscriber3);

        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS);

        testSubscriber1.assertReceivedOnNext(listToSend);
        testSubscriber2.assertReceivedOnNext(listToSend);
        testSubscriber3.assertReceivedOnNext(listToSend);
        assertEquals(6, MultiCastingExercise.count);
    }

    @Test
    public void test3Publish() {
        Observable<Integer> multicastObservable = MultiCastingExercise.getMulticast3Integer(testScheduler, listToSend);

        multicastObservable
                .subscribe(testSubscriber1);

        multicastObservable
                .subscribe(testSubscriber2);

        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS);

        multicastObservable
                .subscribe(testSubscriber3);

        testScheduler.advanceTimeBy(200, TimeUnit.MILLISECONDS);

        testSubscriber1.assertReceivedOnNext(listToSend);
        testSubscriber2.assertReceivedOnNext(listToSend);
        testSubscriber3.assertReceivedOnNext(listToSend);
        assertEquals(3, MultiCastingExercise.count);
    }

    public void log(Object t) {
        System.out.println(t.toString());
    }
}
