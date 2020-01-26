package api.cornell.data.eatery

import com.google.gson.annotations.SerializedName

/**
 * [Page] contains all the useful information about eatery pages.
 *
 * @param path path of the page.
 * @param title title of the page.
 * @param body body content of the page.
 * @param updatedDatetime last updated date time.
 * @param navInclude whether the page is included in the nav bar.
 * @param navSortIdx index in the nav bar.
 */
data class Page(
        @SerializedName(value = "slug") val path: String,
        val title: String,
        val body: String,
        @SerializedName(value = "updatedDttm") val updatedDatetime: String,
        val navInclude: Boolean,
        val navSortIdx: Int
)
