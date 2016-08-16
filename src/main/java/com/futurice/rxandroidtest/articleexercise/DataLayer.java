package com.futurice.rxandroidtest.articleexercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by gval on 11/08/2016.
 */
public class DataLayer {

    Scheduler scheduler;

    public DataLayer(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * simulate a fetch of an article which takes 200 milliseconds.
     * @param id id of the article
     * @return Observable<Article>
     */
    public  Observable<Article> fetchArticle(int id) {
        return Observable.just(id)
                .delay(200, TimeUnit.MILLISECONDS, scheduler)
                .map(idArticle -> new Article(id,  "article: " + idArticle));
    }

    /**
     * observable of a list of ids from the Time
     * here we only send one list
     * @return Observable<List<Integer>>
     */
    public  Observable<List<Integer>> fetchArticlesIdsFromTime() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        return Observable.just(list);
    }

    /**
     * observable of a list of ids from the DailyNews
     * here we only send one list
     * @return Observable<List<Integer>>
     */
    public  Observable<List<Integer>> fetchArticlesIdsFromDailyNews() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        return Observable.just(list);
    }

    /**
     * observable of a list of ids from the DailyNews
     * here we only send two list with a delay of 200 simulating an update of the ids
     * @returnObservable<List<Integer>>
     */
    public  Observable<List<Integer>> fetchArticlesIdsFromDailyNews2() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(6);
        list2.add(7);
        list2.add(8);

        return Observable.merge(Observable.just(list), Observable.just(list2).delay(200, TimeUnit.MILLISECONDS, scheduler));
    }
}
