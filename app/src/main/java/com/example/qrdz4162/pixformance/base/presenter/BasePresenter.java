package com.example.qrdz4162.pixformance.base.presenter;

import com.example.qrdz4162.pixformance.base.view.BaseView;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public interface BasePresenter {

    void onViewAttached(BaseView view);
    void onViewDetached();
}
