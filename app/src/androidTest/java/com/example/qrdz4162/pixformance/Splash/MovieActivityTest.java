package com.example.qrdz4162.pixformance.Splash;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.qrdz4162.pixformance.R;
import com.example.qrdz4162.pixformance.movies.MovieActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
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

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.movies_recycler_view), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

    }

}
