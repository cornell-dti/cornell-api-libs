package api.cornell.data.classes

import com.google.gson.annotations.SerializedName

/**
 * [Roster] contains all the useful information about Cornell Roster.
 *
 * @param semester semester of the roster.
 * @param semesterCode code of the semester.
 * @param description a description of the semester.
 * @param shortDescription a short description of the semester.
 * @param defaultSessionCode the default session code.
 * @param lastModifiedDatetime a datetime stamp of the last modified date time.
 */
data class Roster(
        @SerializedName(value = "slug") val semester: String,
        @SerializedName(value = "strm") val semesterCode: String,
        @SerializedName(value = "descr") val description: String,
        @SerializedName(value = "descrshort") val shortDescription: String,
        val defaultSessionCode: String,
        @SerializedName(value = "lastModifiedDttm") val lastModifiedDatetime: String
)
