package api.cornell.data.eatery

/**
 * [MenuCategory] contains all useful information about menu categories.
 *
 * @param category category name.
 * @param sortIdx index used for sorting.
 * @param items a list of items in the category.
 */
data class MenuCategory(
        val category: String, private val sortIdx: Int, val items: List<MenuItem>
) : Comparable<MenuCategory> {
    /**
     * @see Comparable
     */
    override fun compareTo(other: MenuCategory): Int = Integer.compare(sortIdx, other.sortIdx)
}
