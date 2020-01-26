package api.cornell.response.classes

import api.cornell.data.classes.Course
import com.google.gson.annotations.SerializedName

/**
 * [CoursesResponse] is the response of a Classes request.
 */
internal class CoursesResponse private constructor() {

    /**
     * Main data of response.
     */
    private val data: Data = Data()
    /**
     * Obtain the academic groups information.
     */
    val courses: List<Course> get() = data.courses

    /**
     * [Data] is an uninteresting class that just holds an array of [Course].
     */
    private inner class Data internal constructor() {
        @SerializedName(value = "classes")
        val courses: List<Course> = emptyList()
    }

}
