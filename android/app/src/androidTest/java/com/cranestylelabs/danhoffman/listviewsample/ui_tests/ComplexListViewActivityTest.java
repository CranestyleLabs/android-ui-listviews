package com.cranestylelabs.danhoffman.listviewsample.ui_tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.TextView;

import com.cranestylelabs.danhoffman.listviewsample.R;
import com.cranestylelabs.danhoffman.listviewsample.activities.ComplexListViewActivity;
import com.cranestylelabs.danhoffman.listviewsample.ui_tests.custom_assertions.EspressoViewCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class ComplexListViewActivityTest extends ActivityInstrumentationTestCase2<ComplexListViewActivity> {

    public ComplexListViewActivityTest() {
        super(ComplexListViewActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testScreenText() {
        onView(withId(R.id.screen_text)).check(matches(withText(R.string.complex_screen_text)));
    }

    public void testFilterBoxIsEmpty() {
        onView(withId(R.id.text_entry))
                .check(matches(isDisplayed()))
                .check(matches(withText("")));
    }

}