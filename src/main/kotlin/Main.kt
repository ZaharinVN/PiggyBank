object PiggyBank {

    private var isSmashed = false
    private var moneys = mutableListOf<Money>()

    fun putMoney(money: Money) {
        if (!isSmashed) {
            moneys.add(money)
            println("Добавлено в копилку ${money.toString()}")
        } else {
            println("Вы разбили копилку, вы больше ничего положить туда не можете")
        }
    }

    fun shake(): Money? {
        if (isSmashed) {
            println("Вы разбили копилку, больше оттуда ничего не вытрясти")
            return null
        }

        val coins = moneys.filter{it.isCoin}.toList()
        return if (coins.isNotEmpty()){
            val index = (coins.indices).random()
            val money = coins[index]
            moneys.remove(money)
            money
        } else {
            null
        }
    }

    fun smash(): List<Money> {
        isSmashed = true
        val result = moneys.toList()
        moneys.clear()
        println("Копилка разбита, вы достали оттуда: $result")
        return result
    }
}


class Money private constructor(val amount: Float, val isCoin: Boolean, val name: String) {

    init {
        require(amount == 0.1f || amount == 0.5f || amount == 1f || amount == 50f || amount == 100f || amount == 500f || amount == 1000f) { "Недопустимая номинал монеты/купюры" }
    }

    override fun toString(): String {
        return if (isCoin) {
            when (amount) {
                0.1f -> "10 коп."
                0.5f -> "50 коп."
                1f -> "1 руб."
                else -> throw IllegalArgumentException("Не тот номинал монеты")
            }
        } else {
            when (amount) {
                50f -> "50 руб."
                100f -> "100 руб."
                500f -> "500 руб."
                1000f -> "1000 руб."
                else -> throw IllegalArgumentException("Не тот номинал купюры")
            }
        }
    }
    companion object {
        val coins_10 = Money(0.1f, true, "10 коп.")
        val coins_50 = Money(0.5f, true, "50 коп.")
        val coins_100 = Money(1f, true, "1 руб.")
        val bill_50 = Money(50f, false, "50 руб.")
        val bill_100 = Money(100f, false, "100 руб.")
        val bill_500 = Money(500f, false, "500 руб.")
        val bill_1000 = Money(1000f, false, "1000 руб.")
    }
}