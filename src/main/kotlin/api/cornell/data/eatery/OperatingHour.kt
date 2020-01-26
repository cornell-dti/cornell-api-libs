package api.cornell.data.eatery

/**
 * [OperatingHour] contains all useful information about operating hours.
 *
 * @param date date of operation.
 * @param status status of operation. Most of the time it is "EVENTS".
 * @param events a list of events.
 */
data class OperatingHour(val date: String, val status: String, val events: List<Event>)
