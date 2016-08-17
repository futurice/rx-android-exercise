package com.futurice.rxandroidtest.articleexercise;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by gval on 11/08/2016.
 */
public class ExerciseArticle {
    private final DataLayer dataLayer;

    public ExerciseArticle(DataLayer dataLayer) {
        this.dataLayer = dataLayer;
    }

    /**
     * create a function which merge both ids stream to create one list,
     * for each id , use the datalayer.fetcharticle() to retrieve the article.
     *
     * only article with a even id interest us.
     *
     *
     * @param articleIdsObservable1 gives a stream of list<ids>,  this list can refresh and should refresh the total list
     * @param articleIdsObservable2 gives a stream of list<ids> from another source
     * @return Observable<List<Article>> merge the two list and for each id fetch the corresponding article.
     */
    public Observable<List<Article>> getArticleStream(Observable<List<Integer>> articleIdsObservable1,
                                                      Observable<List<Integer>> articleIdsObservable2) {

        return null;
    }

    /**
     * same as above but optimise it that if a new list comes up from one of the two observables
     * articleIdsObservable1 or articleIdsObservable2, it cancel all fetching and focus on new list
     *
     * @param articleIdsObservable1 gives a stream of list<ids>,  this list can refresh and should refresh the total list
     * @param articleIdsObservable2 gives a stream of list<ids> from another source
     * @return
     */
    public Observable<List<Article>> getArticleStreamOptimised(Observable<List<Integer>> articleIdsObservable1,
                                                      Observable<List<Integer>> articleIdsObservable2) {

        return null;
    }
}
