package api.cornell.data.eatery


/**
 * [MenuItem] contains all useful information about menu item.
 *
 * @param item name of the item.
 * @param healthy whether the item is healthy.
 * @param sortIdx index used for sorting.
 */
data class MenuItem(
        val item: String, val healthy: Boolean, private val sortIdx: Int
) : Comparable<MenuItem> {
    /**
     * @see Comparable
     */
    override fun compareTo(other: MenuItem): Int = Integer.compare(sortIdx, other.sortIdx)
}
