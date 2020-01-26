package api.cornell.data.classes

import com.google.gson.annotations.SerializedName

/**
 * @param semesterCode code of the semester.
 * @param courseId ID of the course.
 * @param subject subject of the course.
 * @param catalogNumber number of the course.
 * @param shortTitle short title of the course.
 * @param catalogBreadth breath of the course.
 * @param catalogDistribution distribution of the course.
 * @param catalogLanguage language catalog.
 * @param catalogForbiddenOverlaps some forbidden overlaps of the course.
 * @param catalogWhenOffered when is the class offered.
 * @param catalogPrerequisitesCorequisites some prerequisites and corequisites of the course.
 * @param catalogSatisfiesRequisite what requisites does this class satisfy.
 * @param enrollGroups a list of all enroll groups.
 */
data class Course(
        @SerializedName(value = "strm") val semesterCode: Int,
        @SerializedName(value = "crseId") val courseId: Int,
        val subject: String,
        @SerializedName(value = "catalogNbr") val catalogNumber: String,
        @SerializedName(value = "titleShort") val shortTitle: String,
        val catalogBreadth: String,
        @SerializedName(value = "catalogDistr") val catalogDistribution: String,
        @SerializedName(value = "catalogLang") val catalogLanguage: String,
        val catalogForbiddenOverlaps: String,
        val catalogWhenOffered: String,
        @SerializedName(value = "catalogPrereqCoreq") val catalogPrerequisitesCorequisites: String,
        @SerializedName(value = "catalogSatisfiesReq") val catalogSatisfiesRequisite: String,
        val enrollGroups: List<EnrollGroup>
)
