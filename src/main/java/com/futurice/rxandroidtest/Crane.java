package com.futurice.rxandroidtest;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by ttuo on 30/09/15.
 */
public class Crane {
    Paper paper;

    private Crane(Paper paper) {
        this.paper = paper;
    }

    public static Observable<Crane> fromPaper(Paper paper, Scheduler scheduler) {
        return Observable.just(new Crane(paper))
                .delay(paper.getProcessingTimeMs(), TimeUnit.MILLISECONDS, scheduler);
    }

    public String getColor() {
        return paper.getColor();
    }
}
