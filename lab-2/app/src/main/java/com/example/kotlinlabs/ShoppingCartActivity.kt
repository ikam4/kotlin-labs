package com.example.kotlinlabs

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

private const val FreeDeliveryThreshold = 500

private data class Product(val id: Int, val price: Int)

private data class Cart(val products: List<Product>) {
    val total: Long get() = products.sumOf { it.price.toLong() }
}

private val CartSaver = Saver<Cart, IntArray>(
    save = { cart -> cart.products.map { it.price }.toIntArray() },
    restore = { prices -> Cart(prices.mapIndexed { id, price -> Product(id, price) }) },
)

class ShoppingCartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(Modifier.fillMaxSize()) {
                    ShoppingCartScreen(Modifier.safeDrawingPadding())
                }
            }
        }
    }
}

@Composable
internal fun ShoppingCartScreen(modifier: Modifier = Modifier) {
    var cart by rememberSaveable(stateSaver = CartSaver) {
        mutableStateOf(Cart(listOf(Product(0, 100), Product(1, 150), Product(2, 56))))
    }
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        LazyColumn(Modifier.weight(1f, fill = false).fillMaxWidth()) {
            items(cart.products, key = { it.id }) { product ->
                val name = stringResource(R.string.cart_product_name, product.id + 1)
                Text(stringResource(R.string.cart_product_line, name, product.price))
            }
        }
        Text(stringResource(R.string.cart_total, cart.total))
        Button(onClick = {
            val product = Product(id = cart.products.size, price = Random.nextInt(0, 100))
            val updatedCart = Cart(cart.products + product)
            cart = updatedCart
            if (updatedCart.total > FreeDeliveryThreshold) {
                Toast.makeText(context, R.string.cart_free_delivery, Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(stringResource(R.string.cart_add_product))
        }
        if (cart.products.isNotEmpty()) {
            Button(onClick = { cart = Cart(cart.products.dropLast(1)) }) {
                Text(stringResource(R.string.cart_remove_product))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun ShoppingCartPreview() {
    MaterialTheme {
        ShoppingCartScreen()
    }
}
