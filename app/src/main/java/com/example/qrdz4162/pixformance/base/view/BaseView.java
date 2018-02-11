package com.example.qrdz4162.pixformance.base.view;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public interface BaseView {

    // display Progress loading
    void showProgressLoading();

    // hide Progress loading
    void hideProgressLoading();

    /**
     * @desc display error message to user with description
     * @param error error msg to be printed to user
     */
    void showInlineError(String error);

    // display error msg to user when connection to internet failed
    void showInlineConnectionError();
}
