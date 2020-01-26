package api.cornell.data.eatery

import com.google.gson.annotations.SerializedName

/**
 * [Event] contains all useful information about an event of dining hall (e.g. lunch, dinner).
 *
 * @param description description of the event.
 * @param startTimestamp start time in timestamp format.
 * @param endTimestamp end time in timestamp format.
 * @param start start time in string.
 * @param end end time in string.
 * @param calendarSummary the summary of the calendar.
 * @param menu a list of menu categories.
 */
data class Event(
        @SerializedName(value = "descr") val description: String,
        val startTimestamp: Long, val endTimestamp: Long,
        val start: String, val end: String,
        @SerializedName(value = "calSummary") val calendarSummary: String,
        val menu: List<MenuCategory>
)