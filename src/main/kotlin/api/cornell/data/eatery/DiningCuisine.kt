package api.cornell.data.eatery

import com.google.gson.annotations.SerializedName

/**
 * [DiningCuisine] contains all useful information about dining cuisines.
 *
 * @param name name of the cuisine.
 * @param shortName short name of the cuisine.
 * @param description description of the cuisine.
 */
data class DiningCuisine(
        val name: String,
        @SerializedName(value = "nameshort") val shortName: String,
        @SerializedName(value = "descr") val description: String
)
