package com.example.qrdz4162.pixformance.base.view;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public interface BaseView {

    void showProgressLoading();
    void hideProgressLoading();
    void showInlineError(String error);
    void showInlineConnectionError();
}
