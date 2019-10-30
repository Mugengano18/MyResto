package com.example.myrestaurants;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.locationEditText)).perform(typeText("Kigali is a very good place ")).check(matches(withText("Kigali is a very good place ")));


    }

    @Test
    public void locationIsSentToRestaurantsActivity() {
        String location = "Kigali is a very good place ";
        onView(withId(R.id.locationEditText)).perform(typeText(location)).perform(closeSoftKeyboard());
        try {                             // the sleep method requires to be checked and handled so we use try block
            Thread.sleep(250);
        } catch (InterruptedException e){
            System.out.println("got interrupted!");
        }
        onView(withId(R.id.findRestaurantsButton)).perform(click());
        onView(withId(R.id.locationTextView)).check(matches
                (withText("Here are all the restaurants near: " + location)));
    }
}



//   some definition i will be needing
//Instrumentation testing: Testing for user interfaces that requires an actual emulator or device to simulate user interaction with the application.
//Espresso: A framework designed to test user interfaces with instrumentation tests.
//
//Tips
//Next, let’s learn the basics of instrumentation testing in Android. We will use Espresso, a UI test framework that creates automated tests that run on an actual device or emulator. Instrumentation tests are meant to simulate the actions of a user and allow us to test the app through the stages of the android activity lifecycle.