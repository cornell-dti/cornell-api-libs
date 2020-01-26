package api.cornell.data.classes

/**
 * [Meeting] contains all the information about class meetings.
 *
 * @param pattern pattern of the meeting. e.g. MWF, TR, etc.
 * @param instructors a list of instructors.
 */
data class Meeting(
        val pattern: String,
        val instructors: List<Instructor>
)
