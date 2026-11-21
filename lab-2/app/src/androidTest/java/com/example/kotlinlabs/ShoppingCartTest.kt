package com.example.kotlinlabs

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ShoppingCartTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun addingAndRemovingOnlyChangesTheTotalByTheLastPrice() {
        composeRule.setContent { MaterialTheme { ShoppingCartScreen() } }
        assertTotal(306)

        composeRule.onNodeWithText("Добавить товар").performClick()
        val newProduct = composeRule.onNodeWithText("Товар #4 - ", substring = true)
        val rowText = newProduct.fetchSemanticsNode().config[SemanticsProperties.Text].single().text
        val price = rowText.substringAfter(" - ").substringBefore(' ').toInt()
        assertTotal(306 + price)

        composeRule.onNodeWithText("Удалить товар").performClick()
        newProduct.assertDoesNotExist()
        assertTotal(306)
    }

    @Test
    fun emptyCartStaysEmptyAfterRestoringState() {
        val restorationTester = StateRestorationTester(composeRule)
        restorationTester.setContent { MaterialTheme { ShoppingCartScreen() } }
        val remove = composeRule.onNodeWithText("Удалить товар")

        for (remainingTotal in listOf(250, 100, 0)) {
            remove.performClick()
            assertTotal(remainingTotal)
        }
        remove.assertDoesNotExist()

        restorationTester.emulateSavedInstanceStateRestore()
        remove.assertDoesNotExist()
        assertTotal(0)
    }

    private fun assertTotal(total: Int) {
        composeRule.onNodeWithText("Товаров на сумму: $total рублей").assertExists()
    }
}
