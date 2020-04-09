class BaseConverter(private val base: Int, private val number: IntArray) {

    init {
        require(base > 1) { "Bases must be at least 2." }
        require(number.isNotEmpty()) { "You must supply at least one digit." }
        require(number.all { it < base }) { "All digits must be strictly less than the base." }
        require(number.all { it >= 0 }) { "Digits may not be negative." }
        require((number.first() != 0) || (number.size == 1)) { "Digits may not contain leading zeros." }
    }

    fun convertToBase(newBase: Int): IntArray {

        require(newBase > 1) { "Bases must be at least 2." }

        return number.fold(0) { acc, i -> acc * base + i }.convertToBase(newBase)
    }

    private fun Int.convertToBase(newBase: Int): IntArray =
            if (this < newBase) intArrayOf(this)
            else (this / newBase).convertToBase(newBase).plus(this % newBase)
}
