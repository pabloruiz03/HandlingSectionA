package pablo.ruiz.handling.sectionB
fun checkPositiveNumber(value: Int) {
    require(value > 0) { "Value must be greater than 0" }
}

fun validateInput(value: Int) {
    if (value <= 0) {
        throw IllegalArgumentException("Value must be greater than 0")
    }
}
