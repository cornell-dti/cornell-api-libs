package api.cornell.response.classes

/**
 * [SubjectsResponse] is the response of a Subjects request.
 */
internal class SubjectsResponse private constructor() {

    /**
     * Main data of response.
     */
    private val data: Data = Data()
    /**
     * Obtain the class levels information.
     */
    val subjects: List<String> get() = data.subjects.map { it.value }

    private data class Subject(val value: String)

    /**
     * [Data] is an uninteresting class that just holds an array of subjects.
     */
    private inner class Data internal constructor() {
        val subjects: List<Subject> = emptyList()
    }

}
