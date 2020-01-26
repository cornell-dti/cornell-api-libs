package api.cornell

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

/**
 * [PlaygroundTest] contains many example code that can also be used as tests.
 */
@Disabled
class PlaygroundTest {

    /**
     * This test obtains the most recent information of all existing classes found on Roster.
     */
    @Test
    fun getAllClassWithMostRecentInfoTest() {
        runBlocking { ClassesApiClient.getAllCourses(doPrintDebuggingInfo = true) }
    }
}
