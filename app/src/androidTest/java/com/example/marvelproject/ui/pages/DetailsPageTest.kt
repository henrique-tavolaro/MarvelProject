package com.example.marvelproject.ui.pages

import androidx.activity.viewModels
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.marvelproject.MainActivity
import com.example.marvelproject.TAG_DETAILS_NAME
import com.example.marvelproject.repository.result
import com.example.marvelproject.ui.MarvelViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DetailsPageTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    private val context = InstrumentationRegistry.getInstrumentation().context

    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            DetailsPage(
                result = result,
                onClick = {}
            )
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule.waitForIdle()
    }

    @Test
    fun isNameDisplayed(){
        composeTestRule.onNodeWithTag(TAG_DETAILS_NAME).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TAG_DETAILS_NAME).assert(hasText("Iron Man"))
    }
}