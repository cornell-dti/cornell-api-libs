package api.cornell.data.eatery

import java.util.Arrays
import java.util.stream.Collectors

/**
 * [EateryType] is a collection of supported eatery type.
 *
 * @param description a simple description of the type.
 */
enum class EateryType(val description: String) {
    /**
     * All You Care To Eat Dining Room.
     */
    ALL_YOU_CARE_TO_EAT(description = "All You Care To Eat Dining Room"),
    /**
     * Cafe.
     */
    CAFE(description = "Cafe"),
    /**
     * Coffee shop.
     */
    COFFEE_SHOP(description = "Coffee Shop"),
    /**
     * Convenience store.
     */
    CONVENIENCE_STORE(description = "Convenience Store"),
    /**
     * Cart.
     */
    CART(description = "Cart"),
    /**
     * Food court.
     */
    FOOD_COURT(description = "Food Court");

    /**
     * @see [Any]
     */
    override fun toString(): String {
        return name
    }

    companion object {

        /**
         * [map] contains the mapping from description to value.
         */
        private val map: Map<String, EateryType> = Arrays.stream(EateryType.values())
                .collect(Collectors.toMap({ it.description }, { it }))

        /**
         * [fromString] converts a string to a [EateryType].
         *
         * @param str string candidate.
         * @return corresponding [EateryType].
         * @throws NoSuchElementException if there is no such [EateryType],
         */
        fun fromString(str: String): EateryType = map[str] ?: throw NoSuchElementException()
    }
}
