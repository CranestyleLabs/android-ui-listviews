package com.cranestylelabs.danhoffman.listviewsample.ui_tests.custom_assertions;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.util.HumanReadables;
import android.test.AssertionFailedError;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.util.TreeIterables.breadthFirstViewTraversal;

public class EspressoViewCountAssertion {

    /**
     * Sugar for has(int, isAssignableFrom(Class)).
     *
     *  Example: onView(rootView).check(has(3, EditText.class);
     */
    public static ViewAssertion has(final int expectedCount, Class<? extends View> clazz) {
        return has(expectedCount, isAssignableFrom(clazz));
    }

    /**
     * Returns a generic {@link ViewAssertion} that asserts that there is a
     * given number of descendant views that match the specified matcher.
     *
     * Example: onView(rootView).check(has(3, isAssignableFrom(EditText.class));
     *
     * @param expectedCount the number of descendant views that should match the specified matcher
     * @param selector the matcher to select the descendant views
     * @throws AssertionError if the number of views that match the selector is different from expectedCount
     */
    public static ViewAssertion has(final int expectedCount, final Matcher<View> selector) {
        return new ViewAssertion() {
            @Override
            public void check(@Nullable View view, @Nullable NoMatchingViewException e) {

                View rootView = view;

                Iterable<View> descendantViews = breadthFirstViewTraversal(rootView);
                List<View> selectedViews = new ArrayList<View>();
                for (View descendantView : descendantViews) {
                    if (selector.matches(descendantView)) {
                        selectedViews.add(descendantView);
                    }
                }

                if (selectedViews.size() != expectedCount) {
                    String errorMessage = HumanReadables.getViewHierarchyErrorMessage(
                            rootView,
                            selectedViews,
                            String.format("Found %d views instead of %d matching: %s", selectedViews.size(), expectedCount, selector),
                            "****MATCHES****");
                    throw new AssertionFailedError(errorMessage);
                }
            }
        };
    }

}
