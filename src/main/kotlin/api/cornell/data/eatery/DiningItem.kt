package api.cornell.data.eatery

import com.google.gson.annotations.SerializedName

/**
 * [DiningItem] contains all useful information about dining items.
 *
 * @param description description of the item.
 * @param category category of the item.
 * @param item item name of the item.
 * @param healthy whether the item is healthy.
 * @param icon icon of the item.
 */
data class DiningItem(
        @SerializedName(value = "descr") val description: String,
        val category: String,
        val item: String,
        val healthy: Boolean,
        val icon: String
)
