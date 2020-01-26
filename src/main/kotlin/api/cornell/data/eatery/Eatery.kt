package api.cornell.data.eatery

import com.google.gson.annotations.SerializedName

/**
 * [Eatery] contains all the useful information about eatery.
 *
 * @param id id of the eatery.
 * @param code code of the eatery.
 * @param name name of the eatery.
 * @param shortName short name of the eatery.
 * @param about about the eatery.
 * @param shortAbout about the eatery, shorter version.
 * @param cornellDining whether it's related to Cornell dining.
 * @param latitude latitude of the eatery.
 * @param longitude longitude of the eatery.
 * @param operatingHours operating hours of the eatery.
 * @param eateryTypes types of eatery.
 * @param payMethods payment methods for the eatery.
 * @param diningItems list of dining items.
 * @param diningCuisines list of dining cuisines.
 */
data class Eatery(
        val id: Int,
        @SerializedName(value = "slug") val code: String,
        val name: String,
        @SerializedName(value = "nameshort") val shortName: String,
        val about: String,
        @SerializedName(value = "aboutshort") val shortAbout: String,
        val cornellDining: Boolean,
        val latitude: Double, val longitude: Double,
        val operatingHours: List<OperatingHour>,
        val eateryTypes: List<EateryType>,
        val payMethods: List<PayMethod>,
        val diningItems: List<DiningItem>,
        val diningCuisines: List<DiningCuisine>
)
