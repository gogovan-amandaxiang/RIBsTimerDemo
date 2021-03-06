package com.gogovan.history;

import android.support.annotation.Nullable;

import com.gogovan.data.TimerTasksRepository;
import com.gogovan.data.entities.TimerTaskEntity;
import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Coordinates Business Logic for {@link HistoryScope}.
 * <p>
 * Show history
 */
@RibInteractor
public class HistoryInteractor
        extends Interactor<HistoryInteractor.HistoryPresenter, HistoryRouter> {

    @Inject
    HistoryPresenter presenter;

    @Inject
    TimerTasksRepository repository;


    @Override
    protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
        super.didBecomeActive(savedInstanceState);


        //get all tasks saved locally
        repository.getAllTask()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    presenter.loadData(list);
                });
    }

    @Override
    protected void willResignActive() {
        super.willResignActive();


    }


    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface HistoryPresenter {

        /**
         * load data
         *
         * @param list
         */
        void loadData(List<TimerTaskEntity> list);

    }
}
