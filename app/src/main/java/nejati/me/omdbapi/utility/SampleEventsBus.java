package nejati.me.omdbapi.utility;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 * Copyright © 2017
 */
public class SampleEventsBus {
    private static final String TAG = SampleEventsBus.class.getSimpleName();
    private static final String TAG2 = SampleEventsBus.class.getCanonicalName();

    public static final int ACTION_FRAGMENT_CREATED = 1;
    public static final int ACTION_FRAGMENT_OTHER = 2;

    private static SampleEventsBus mInstance;

    public static SampleEventsBus getInstance() {
        if (mInstance == null) {
            mInstance = new SampleEventsBus();
        }
        return mInstance;
    }

    private SampleEventsBus() {}

    private PublishSubject<Integer> fragmentEventSubject = PublishSubject.create();

    public Observable<Integer> getFragmentEventObservable() {
        return fragmentEventSubject;
    }

    public void postFragmentAction(Integer actionId) {
        fragmentEventSubject.onNext(actionId);
    }
}