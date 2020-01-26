package api.cornell.data.eatery

import java.util.Arrays
import java.util.stream.Collectors

/**
 * [PayMethod] is a collection of supported pay methods.
 *
 * @param description a simple description of the method.
 */
enum class PayMethod(val description: String) {

    /**
     * Meal Plan - Debit Plans (Big Red Bucks, Meal Choice, etc)
     */
    MEAL_PLAN_DEBIT(description = "Meal Plan - Debit Plans (Big Red Bucks, Meal Choice, etc)"),
    /**
     * Meal Plan - Meal Swipe
     */
    MEAL_PLAN_SWIPE(description = "Meal Plan - Meal Swipe"),
    /**
     * Cornell Card
     */
    CORNELL_CARD(description = "Cornell Card"),
    /**
     * Major Credit Cards (VISA, MasterCard, AMEX)
     */
    CREDIT_CARD(description = "Major Credit Cards (VISA, MasterCard, AMEX)"),
    /**
     * Mobile Payments (Apple Pay, Google Wallet)
     */
    MOBILE_PAYMENTS(description = "Mobile Payments (Apple Pay, Google Wallet)"),
    /**
     * Cash.
     */
    CASH(description = "Cash");

    companion object {

        /**
         * [map] contains the mapping from description to value.
         */
        private val map: Map<String, PayMethod> = Arrays.stream(PayMethod.values())
                .collect(Collectors.toMap({ it.description }, { it }))

        /**
         * [fromString] converts a string to a [PayMethod]
         *
         * @param str string candidate.
         * @return corresponding [PayMethod].
         * @throws NoSuchElementException if there is no such [PayMethod].
         */
        fun fromString(str: String): PayMethod = map[str] ?: throw NoSuchElementException()
    }

}
