package com.example.marvelproject.ui.pages

import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.marvelproject.MainActivity
import com.example.marvelproject.TAG_LAZY_COLUMN
import com.example.marvelproject.TAG_LAZY_ROW
import com.example.marvelproject.ui.MarvelViewModel
import com.example.marvelproject.ui.navigation.Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomePageTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    private val context = InstrumentationRegistry.getInstrumentation().context
    lateinit var navController: NavHostController
    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            navController = rememberNavController()
            HomePage(
                context = context,
            viewModel = composeTestRule.activity.viewModels<MarvelViewModel>().value,
            onClick = {}
            )
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
        composeTestRule.waitForIdle()
    }

    @Test
    fun isLazyColumnDisplayedAfterSearch() {

        composeTestRule.onNodeWithTag(TAG_LAZY_COLUMN).assertIsNotDisplayed()

        composeTestRule.onNode(hasImeAction(ImeAction.Done)).performTextInput("iron man")
        composeTestRule.onNode(hasImeAction(ImeAction.Done)).performImeAction()

        composeTestRule.onNodeWithTag(TAG_LAZY_COLUMN).assertIsDisplayed()
        composeTestRule.onAllNodes(hasText("Iron Man"))
        composeTestRule.onAllNodes(hasText("Iron Man (LEGO Marvel Super Heroes)"))
        composeTestRule.onAllNodes(hasText("Iron Man (Marvel Heroes)"))
        composeTestRule.onAllNodes(hasText("Iron Man (Iron Man 3 - The Official Game)"))
    }

    @Test
    fun isLazyRowDisplayedAfterSearch() {

        composeTestRule.onNodeWithTag(TAG_LAZY_ROW).assertIsNotDisplayed()

        composeTestRule.onNode(hasImeAction(ImeAction.Done)).performTextInput("iron man")
        composeTestRule.onNode(hasImeAction(ImeAction.Done)).performImeAction()

        composeTestRule.onNodeWithTag(TAG_LAZY_ROW).assertIsDisplayed()
        composeTestRule.onAllNodes(hasText("1"))
        composeTestRule.onAllNodes(hasText("2"))

    }


}







