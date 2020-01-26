package api.cornell.data.classes

import com.google.gson.annotations.SerializedName

/**
 * [ClassSection] contains all the useful information of a class section.
 *
 * @param classNumber the class number.
 * @param isComponentGraded whether the component is graded.
 * @param section section of the class.
 * @param component component of the class.
 * @param meetings a list of all class meetings.
 */
data class ClassSection(
        @SerializedName(value = "classNbr") val classNumber: Int,
        val isComponentGraded: Boolean,
        val section: String,
        @SerializedName(value = "ssrComponent") val component: ClassComponent,
        val meetings: List<Meeting>
)
