package com.example.qrdz4162.pixformance.movies;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.qrdz4162.pixformance.R;
import com.example.qrdz4162.pixformance.Splash.SplashScreen;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MovieActivityTest {

    @Rule
    public ActivityTestRule<MovieActivity> mActivityTestRule = new ActivityTestRule<>(MovieActivity.class);

    @Test
    public void movieActivityTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.movie_search_et), isDisplayed()));
        appCompatEditText.perform(replaceText("maze"), closeSoftKeyboard());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.movie_search_button), isDisplayed()));
        appCompatImageView.perform(click());

        // sleep for 3 second to see the view
        waitToRecognize();

        onView(withId(R.id.movies_recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(3));


        // Click on the RecyclerView item at position 2
        onView(withId(R.id.movies_recycler_view)).
                perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));


        // sleep for 3 second to see the scrolling
        waitToRecognize();

    }

    private void waitToRecognize() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
