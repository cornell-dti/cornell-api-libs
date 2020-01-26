package api.cornell

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * [DataValidTest] ensures that all the data are valid.
 */
class DataValidTest {

    @Test
    fun classApiClientValidityTest() {
        val allItems = ArrayList<String?>(1 shl 10)
        val rosters = runBlocking { ClassesApiClient.getRosters() }
        for (roster in rosters) {
            allItems.add(roster.toString())
            val semester = roster.semester
            if (!semester.contains("FA") && !semester.contains("SP")) {
                continue
            }
            runBlocking {
                val job1 = launch {
                    ClassesApiClient.getSubjects(roster = semester).forEach { allItems.add(it) }
                }
                val job2 = launch {
                    ClassesApiClient.getCourses(roster = semester, subject = "CS").forEach {
                        allItems.add(it.toString())
                    }
                }
                job1.join()
                job2.join()
            }
        }
        for (item in allItems) {
            if (item == null) {
                error(message = "Bad NULL!")
            }
        }
    }

    /**
     * Test the validity of data from [DiningApiClient]
     */
    @Test
    fun diningApiClientValidityTest() {
        val allItems = ArrayList<String?>(1 shl 10)
        runBlocking {
            val job1 = launch {
                DiningApiClient.getPages().forEach { allItems.add(element = it.toString()) }
            }
            val job2 = launch {
                DiningApiClient.getEateries().forEach { allItems.add(element = it.toString()) }
            }
            job1.join()
            job2.join()
        }
        for (item in allItems) {
            if (item == null) {
                error(message = "Bad NULL!")
            }
        }
    }
}
