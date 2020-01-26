package api.cornell

import api.cornell.data.classes.Course
import api.cornell.data.classes.Roster
import api.cornell.response.classes.CoursesResponse
import api.cornell.response.classes.RostersResponse
import api.cornell.response.classes.SubjectsResponse
import com.google.gson.Gson
import kotlinx.coroutines.delay

/**
 * [ClassesApiClient] defines a set of operation that the Cornell Classes API Client supports.
 */
object ClassesApiClient {

    private val http: Http = Http(prefix = "https://classes.cornell.edu/api/2.0", gson = Gson())

    suspend fun getRosters(): List<Roster> =
            http.request<RostersResponse>(path = "/config/rosters.json").rosters

    /**
     * @param roster roster semester & year (i.e. FA14, SP15)
     * @return all available course subjects for a roster.
     */
    suspend fun getSubjects(roster: String): List<String> =
            http.request<SubjectsResponse>(
                    path = "/config/subjects.json", parameters = listOf("roster" to roster)
            ).subjects

    /**
     * Information like `academicCareers`, `academicGroups`, `classLevels`, `classAttributes`,
     * `query` are defaulted to `null`.
     * This method `getCourses(roster, subject)` is equivalent as
     * `getCourses(roster, subject, null, null, null, null, null)`.
     *
     * @param roster roster semester & year (i.e. FA14, SP15)
     * @param subject academic subject (i.e. CS, PHIL)
     * @return subject that match the input query.
     */
    suspend fun getCourses(roster: String, subject: String): List<Course> =
            getCourses(roster = roster, subject = subject, query = null)

    /**
     * @param roster roster semester & year (i.e. FA14, SP15)
     * @param subject academic subject (i.e. CS, PHIL)
     * @param query search term (i.e. graphics)
     * @return subject that match the input query.
     */
    suspend fun getCourses(roster: String, subject: String, query: String?): List<Course> =
            http.request<CoursesResponse>(
                    path = "/search/classes.json",
                    parameters = arrayListOf<Pair<String, Any?>>(
                            "roster" to roster, "subject" to subject
                    ).apply { query?.let { add("q" to it) } }
            ).courses

    /**
     * It blocks until the results are ready. It may take you five to ten minutes.
     *
     * @param semester the semester id.
     * @param coolingTimeMs the time to wait between two consecutive fetch of classes within a
     * subject. It defaults to 50.
     * @param doPrintDebuggingInfo whether to print debugging information, which defaults to false.
     */
    suspend fun getAllCoursesInSemester(
            semester: String,
            coolingTimeMs: Long = 50,
            doPrintDebuggingInfo: Boolean = false
    ): List<Course> {
        val courseList = arrayListOf<Course>()
        val subjects = http.request<SubjectsResponse>(
                path = "/config/subjects.json", parameters = listOf("roster" to semester)
        ).subjects
        if (doPrintDebuggingInfo) {
            System.err.println("We have ${subjects.size} subjects in $semester total.")
        }
        var subjectCount = 0
        for (subject in subjects) {
            val courses = http.request<CoursesResponse>(
                    path = "/search/classes.json",
                    parameters = listOf("roster" to semester, "subject" to subject)
            ).courses
            courseList.addAll(elements = courses)
            delay(timeMillis = coolingTimeMs)
            subjectCount++
            if (doPrintDebuggingInfo) {
                System.err.println(
                        "There're ${courses.size} courses in $subject in $semester."
                )
                System.err.println(
                        "We fetched $subjectCount out of ${subjects.size} subjects in $semester."
                )
            }
        }
        return courseList
    }

    /**
     * It blocks until the results are ready. It may take you five to ten minutes.
     *
     * @param coolingTimeMs the time to wait between two consecutive fetch of classes within a
     * subject. It defaults to 50.
     * @param doPrintDebuggingInfo whether to print debugging information, which defaults to false.
     */
    suspend fun getAllCourses(
            coolingTimeMs: Long = 50,
            doPrintDebuggingInfo: Boolean = false
    ): List<Course> {
        val startTime = System.currentTimeMillis()
        val courseList = arrayListOf<Course>()
        val rosters = http
                .request<RostersResponse>(path = "/config/rosters.json")
                .rosters
                .asSequence()
                .map { it.semester }
                .filter { it.contains(other = "FA") || it.contains(other = "SP") }
                .toList()
        if (doPrintDebuggingInfo) {
            System.err.println("We have ${rosters.size} semesters in total.")
        }
        var semesterCount = 0
        for (roster in rosters) {
            courseList.addAll(getAllCoursesInSemester(
                    semester = roster,
                    coolingTimeMs = coolingTimeMs,
                    doPrintDebuggingInfo = doPrintDebuggingInfo
            ))
            semesterCount++
            if (doPrintDebuggingInfo) {
                System.err.println("We fetched $semesterCount out of ${rosters.size} semesters.")
            }
        }
        if (doPrintDebuggingInfo) {
            System.err.println("Total Running Time: ${System.currentTimeMillis() - startTime}ms.")
        }
        return courseList
    }

    /**
     * It blocks until the results are ready. It may take you five to ten minutes.
     *
     * @param coolingTimeMs the time to wait between two consecutive fetch of classes within a
     * subject. It defaults to 50.
     * @param doPrintDebuggingInfo whether to print debugging information, which defaults to false.
     */
    suspend fun getAllRecentCSCourses(
            coolingTimeMs: Long = 50, doPrintDebuggingInfo: Boolean = false
    ): List<Course> {
        val startTime = System.currentTimeMillis()
        val courseMap = hashMapOf<String, Course>()
        val rosters = http
                .request<RostersResponse>(path = "/config/rosters.json")
                .rosters
                .asSequence()
                .map { it.semester }
                .filter { it.contains(other = "FA") || it.contains(other = "SP") }
                .toList()
        if (doPrintDebuggingInfo) {
            System.err.println("We have ${rosters.size} semesters in total.")
        }
        var semesterCount = 0
        for (roster in rosters) {
            val courses = http.request<CoursesResponse>(
                    path = "/search/classes.json",
                    parameters = listOf("roster" to roster, "subject" to "CS")
            ).courses
            courses.asSequence().map { it.catalogNumber to it }.forEach { (code, course) ->
                courseMap[code] = course
            }
            delay(timeMillis = coolingTimeMs)
            if (doPrintDebuggingInfo) {
                System.err.println(
                        "There are ${courses.size} courses in CS in $roster."
                )
            }
            semesterCount++
            if (doPrintDebuggingInfo) {
                System.err.println("We fetched $semesterCount out of ${rosters.size} semesters.")
            }
        }
        if (doPrintDebuggingInfo) {
            System.err.println("Total Running Time: ${System.currentTimeMillis() - startTime}ms.")
        }
        return courseMap.values.sortedBy { it.catalogNumber }
    }
}
