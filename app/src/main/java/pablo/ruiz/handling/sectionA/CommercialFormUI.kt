import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommercialFormUI() {
    var minPrice by remember { mutableStateOf("") }
    var maxPrice by remember { mutableStateOf("") }
    var selectedProductLine by remember { mutableStateOf("Electronics") }
    var expressDelivery by remember { mutableStateOf(false) }
    var filteredProducts by remember { mutableStateOf(emptyList<Product>()) }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = minPrice,
            onValueChange = { minPrice = it },
            label = { Text("Min Price") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = maxPrice,
            onValueChange = { maxPrice = it },
            label = { Text("Max Price") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Select Product Line")
        ProductLineSelector(
            selectedProductLine = selectedProductLine,
            onProductLineSelected = { selectedProductLine = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(
                checked = expressDelivery,
                onCheckedChange = { expressDelivery = it }
            )
            Text("Express Delivery")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val form = CommercialForm(
                    minPrice = minPrice.toDoubleOrNull() ?: 0.0,
                    maxPrice = maxPrice.toDoubleOrNull() ?: Double.MAX_VALUE,
                    productLine = selectedProductLine,
                    expressDelivery = expressDelivery
                )
                filteredProducts = DatabaseQuery.filterProducts(form)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Filter Products")
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(filteredProducts) { product ->
                Text("${product.name} - $${product.price}", Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun ProductLineSelector(
    selectedProductLine: String,
    onProductLineSelected: (String) -> Unit
) {
    val productLines = listOf("Electronics", "Furniture", "Clothing")

    Column {
        productLines.forEach { productLine ->
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedProductLine == productLine,
                    onClick = { onProductLineSelected(productLine) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(productLine)
            }
        }
    }
}
