package com.futurice.rxandroidtest.articleexercise;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;

public class ExerciseArticlesTest {
    DataLayer dataLayer;
    ExerciseArticle exerciseArticle;
    TestScheduler testScheduler;

    @Before
    public void setup() {
        testScheduler = new TestScheduler();
        dataLayer = new DataLayer(testScheduler);
        exerciseArticle = new ExerciseArticle(dataLayer);
    }

    @Test
    public void retrieveListOfEvenArticleFrom2SourcesTest() {
        TestSubscriber<List<Article>> testSubscriber = new TestSubscriber<>();


        exerciseArticle.getArticleStream(dataLayer.fetchArticlesIdsFromDailyNews(),
                dataLayer.fetchArticlesIdsFromTime()).subscribe(testSubscriber);
        testScheduler.advanceTimeBy(400, TimeUnit.MILLISECONDS);

        assertEquals(4, testSubscriber.getOnNextEvents().get(0).size());
        assertEquals(1, testSubscriber.getOnNextEvents().size());
    }

    @Test
    public void retrieveListOfEvenArticleFrom2SourcesCheckUpdateTest() {
        TestSubscriber<List<Article>> testSubscriber = new TestSubscriber<>();


        exerciseArticle.getArticleStream(dataLayer.fetchArticlesIdsFromDailyNews2(), dataLayer.fetchArticlesIdsFromTime()).subscribe(testSubscriber);
        testScheduler.advanceTimeBy(800, TimeUnit.MILLISECONDS);

        assertEquals(4, testSubscriber.getOnNextEvents().get(0).size());
        assertEquals(2, testSubscriber.getOnNextEvents().size());
    }

    @Test
    public void exercise2BisTest() {
        TestSubscriber<List<Article>> testSubscriber = new TestSubscriber<>();

        exerciseArticle.getArticleStreamOptimised(dataLayer.fetchArticlesIdsFromDailyNews2(),
                dataLayer.fetchArticlesIdsFromTime()).
                subscribe(testSubscriber);
        testScheduler.advanceTimeBy(800, TimeUnit.MILLISECONDS);

        assertEquals(4, testSubscriber.getOnNextEvents().get(0).size());
        assertEquals(1, testSubscriber.getOnNextEvents().size());
    }
}
