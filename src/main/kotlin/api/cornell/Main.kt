@file:JvmName(name = "Main")

package api.cornell

import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking

/**
 * Print the help message.
 */
private fun printHelp() {
    println("""
            Usage: java -jar cornell-api-lib-kotlin.jar [arg]
            Possible args:
            print-all-courses: prints the JSON of a list of all courses in all semesters
            print-all-courses-in [sem]: prints the JSON of a list of the all courses in semester [sem].
            print-all-cs-recent: prints the JSON of a list of all CS courses in recent semesters.
        """.trimIndent())
}

/**
 * The main entry point.
 */
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        printHelp()
        return
    }
    val gson = GsonBuilder().setPrettyPrinting().create()
    when (args[0]) {
        "print-all-courses" -> {
            val courses = runBlocking {
                ClassesApiClient.getAllCourses(doPrintDebuggingInfo = true)
            }
            gson.toJson(courses, System.out)
        }
        "print-all-courses-in" -> {
            val courses = runBlocking {
                ClassesApiClient.getAllCoursesInSemester(
                        semester = args[1], doPrintDebuggingInfo = true
                )
            }
            gson.toJson(courses, System.out)
        }
        "print-all-cs-recent" -> {
            val courses = runBlocking {
                ClassesApiClient.getAllRecentCSCourses(doPrintDebuggingInfo = true)
            }
            gson.toJson(courses, System.out)
        }
    }
}
