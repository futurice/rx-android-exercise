package com.futurice.rxandroidtest.craneexercise;

import com.futurice.rxandroidtest.craneexercise.Crane;
import com.futurice.rxandroidtest.craneexercise.Paper;
import com.futurice.rxandroidtest.craneexercise.RxAndroidExercise;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;
import rx.subjects.BehaviorSubject;

import static org.junit.Assert.assertEquals;

public class RxAndroidExerciseTest {
    Paper bluePaper;
    Paper greenPaper;
    Paper redPaper;
    List<Paper> paperStack;

    @Before
    public void setup() {
        bluePaper = new Paper("blue", 400);
        greenPaper = new Paper("green", 100);
        redPaper = new Paper("red", 200);
        paperStack = Arrays.asList(
                bluePaper,
                greenPaper,
                redPaper
        );
    }


    @Test
    public void noOrderMaintained() {
        Observable<Paper> paperObservable = Observable.from(paperStack);
        TestSubscriber<List<Crane>> testSubscriber = new TestSubscriber<>();
        TestScheduler testScheduler = new TestScheduler();

        RxAndroidExercise.makeCranes1(paperObservable, testScheduler).subscribe(testSubscriber);
        testScheduler.advanceTimeBy(400, TimeUnit.MILLISECONDS);

        assertEquals(1, testSubscriber.getOnNextEvents().size());
        List<Crane> cranes = testSubscriber.getOnNextEvents().get(0);
        assertEquals(3, cranes.size());
    }

    @Test
    public void orderMaintainedSequential() {
        Observable<Paper> paperObservable = Observable.from(paperStack);
        TestSubscriber<List<Crane>> testSubscriber = new TestSubscriber<>();
        TestScheduler testScheduler = new TestScheduler();

        RxAndroidExercise.makeCranes2(paperObservable, testScheduler).subscribe(testSubscriber);
        testScheduler.advanceTimeTo(700, TimeUnit.MILLISECONDS);

        assertEquals(1, testSubscriber.getOnNextEvents().size());
        List<Crane> cranes = testSubscriber.getOnNextEvents().get(0);
        assertEquals(3, cranes.size());
        assertEquals("blue", cranes.get(0).getColor());
        assertEquals("green", cranes.get(1).getColor());
        assertEquals("red", cranes.get(2).getColor());
    }

    @Test
    public void orderMaintainedParallel() {
        Observable<Paper> paperObservable = Observable.from(paperStack);
        TestSubscriber<List<Crane>> testSubscriber = new TestSubscriber<>();
        TestScheduler testScheduler = new TestScheduler();

        RxAndroidExercise.makeCranes3(paperObservable, testScheduler).subscribe(testSubscriber);
        testScheduler.advanceTimeTo(400, TimeUnit.MILLISECONDS);

        assertEquals(1, testSubscriber.getOnNextEvents().size());
        List<Crane> cranes = testSubscriber.getOnNextEvents().get(0);
        assertEquals(3, cranes.size());
        assertEquals("blue", cranes.get(0).getColor());
        assertEquals("green", cranes.get(1).getColor());
        assertEquals("red", cranes.get(2).getColor());
        testSubscriber.assertCompleted();
    }


    @Test
    public void orderMaintainedParallelNonCompleting() {
        BehaviorSubject<List<Paper>> paperListObservable = BehaviorSubject.create(paperStack);
        TestSubscriber<List<Crane>> testSubscriber = new TestSubscriber<>();
        TestScheduler testScheduler = new TestScheduler();

        RxAndroidExercise.makeCranes4(paperListObservable, testScheduler).subscribe(testSubscriber);
        testScheduler.advanceTimeTo(400, TimeUnit.MILLISECONDS);

        assertEquals(1, testSubscriber.getOnNextEvents().size());
        List<Crane> cranes = testSubscriber.getOnNextEvents().get(0);
        assertEquals(3, cranes.size());
        assertEquals("blue", cranes.get(0).getColor());
        assertEquals("green", cranes.get(1).getColor());
        assertEquals("red", cranes.get(2).getColor());
        testScheduler.advanceTimeBy(500,TimeUnit.MILLISECONDS);
        testSubscriber.assertNotCompleted();
    }
}
