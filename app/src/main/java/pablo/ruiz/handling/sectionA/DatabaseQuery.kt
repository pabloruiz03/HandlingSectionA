data class Product(
    val name: String,
    val price: Double,
    val productLine: String,
    val expressDelivery: Boolean
)

object DatabaseQuery {
    private val products = listOf(
        Product("Product A", 100.0, "Electronics", true),
        Product("Product B", 200.0, "Furniture", false),
        Product("Product C", 150.0, "Electronics", true)
    )

    fun filterProducts(form: CommercialForm): List<Product> {
        return products.filter { product ->
            product.price in form.minPrice..form.maxPrice &&
                    product.productLine == form.productLine &&
                    (!form.expressDelivery || product.expressDelivery)
        }.sortedBy { it.price }
    }
}
