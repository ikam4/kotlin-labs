package com.example.kotlinlabs

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WaterTrackerTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun showWaterTracker() {
        composeRule.setContent {
            MaterialTheme {
                WaterTracker()
            }
        }
    }

    @Test
    fun addWaterIncreasesAmountBy250EveryTime() {
        composeRule.onNodeWithText("100 мл").assertExists()
        val addWater = composeRule.onNodeWithText("+250 мл")
        addWater.assertExists().performClick()
        composeRule.onNodeWithText("350 мл").assertExists()
        addWater.performClick()
        composeRule.onNodeWithText("600 мл").assertExists()
    }

    @Test
    fun finishDayResetsWaterAmount() {
        composeRule.onNodeWithText("+250 мл").performClick()
        composeRule.onNodeWithText("Завершить день").assertExists().performClick()
        composeRule.onNodeWithText("0 мл").assertExists()
        assertStreak(0)
    }

    @Test
    fun streakIncludesExactGoalAndResetsAfterMissedDay() {
        val addWater = composeRule.onNodeWithText("+250 мл")
        val finishDay = composeRule.onNodeWithText("Завершить день")

        assertStreak(0)
        repeat(6) { addWater.performClick() }
        composeRule.onNodeWithText("1600 мл").assertExists()
        finishDay.performClick()
        assertStreak(1)

        repeat(6) { addWater.performClick() }
        composeRule.onNodeWithText("1500 мл").assertExists()
        finishDay.performClick()
        assertStreak(2)

        repeat(5) { addWater.performClick() }
        composeRule.onNodeWithText("1250 мл").assertExists()
        finishDay.performClick()
        composeRule.onNodeWithText("0 мл").assertExists()
        assertStreak(0)
    }

    private fun assertStreak(days: Int) {
        composeRule.onNodeWithText("Дней подряд с 1500 мл и больше: $days").assertExists()
    }
}
