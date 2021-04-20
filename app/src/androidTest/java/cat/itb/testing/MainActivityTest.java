package cat.itb.testing;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    String USER_TO_BE_TYPED ="PepeBotella";
    String PASSWORD_TO_BE_TYPED = "PepeELpepe";
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);
    @Test
    public void view_elements_are_displayed() {
        onView(withId(R.id.main_activity_title)).check(matches(isDisplayed()));
    }
    @Test
    public void view_elements_are_the_correct_text(){
        onView(withId(R.id.main_activity_title)).check(matches(withText(R.string.main_activity_title)));
        onView(withId(R.id.button)).check(matches(withText(R.string.next)));
    }
    @Test
    public void view_button_clickable_and_changes_text(){
        onView(withId(R.id.button)).check(matches(isClickable()));
        onView(withId(R.id.button)).perform(click()).check(matches(withText(R.string.back)));
    }
    @Test
    public void login_form_behaviour(){
        onView(withId(R.id.editTextUser)).perform(typeText(USER_TO_BE_TYPED), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextTextPassword)).perform(typeText(PASSWORD_TO_BE_TYPED), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click()).check(matches(withText("Logged")));
    }

    @Test
    public void test_through_multiple_activities(){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.SecondActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.back_button)).perform(click());
        onView(withId(R.id.MainActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.button)).perform(click());
        pressBack();
        onView(withId(R.id.MainActivity)).check(matches(isDisplayed()));

    }
    @Test
    public void test_username_and_password_introduced_next_button_is_clicked_activity_change_2ndTextview_is_welcome_plus_user_back_button_is_clicked_activity_change_edittext_fields_are_empty(){
        onView(withId(R.id.editTextUser)).perform(typeText(USER_TO_BE_TYPED), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextTextPassword)).perform(typeText(PASSWORD_TO_BE_TYPED), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.SecondActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.SecondActivityText)).check(matches(withText("Welcome back "+USER_TO_BE_TYPED)));
        onView(withId(R.id.back_button)).perform(click());
        onView(withId(R.id.MainActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextUser)).check(matches(withText("")));
        onView(withId(R.id.editTextTextPassword)).check(matches(withText("")));
    }
}
