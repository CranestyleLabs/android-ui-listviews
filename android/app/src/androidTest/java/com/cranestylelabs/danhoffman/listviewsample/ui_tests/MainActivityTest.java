package com.cranestylelabs.danhoffman.listviewsample.ui_tests;

import android.support.test.espresso.matcher.ViewMatchers;
import android.test.suitebuilder.annotation.LargeTest;

import com.cranestylelabs.danhoffman.listviewsample.R;
import com.cranestylelabs.danhoffman.listviewsample.activities.MainActivity;

import org.junit.After;
import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;

@LargeTest
public class MainActivityTest extends android.test.ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
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

    public void testHelloText() {
        onView(withId(R.id.hello)).check(matches(withText(R.string.hello_world)));
    }

    public void testComplexButtonGoesToComplexActivity() {
        onView(withId(R.id.complex_button)).perform(click());
        onView(withId(R.id.ComplexListViewActivity)).check(matches(isDisplayed()));
    }

    public void testSimpleButtonGoesToSimpleActivity() {
        onView(withId(R.id.simple_button)).perform(click());
        onView(withId(R.id.SimpleListViewActivity)).check(matches(isDisplayed()));
    }
}