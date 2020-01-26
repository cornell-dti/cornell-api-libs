package api.cornell.response.eatery

import api.cornell.data.eatery.Eatery

/**
 * [EateriesResponse] is the response of a Eateries request.
 */
internal class EateriesResponse private constructor() {

    /**
     * Main data of response.
     */
    private val data: Data = Data()
    /**
     * Obtain the eateries information.
     */
    val eateries: List<Eatery> get() = data.eateries

    /**
     * [Data] is an uninteresting class that just holds an array of [Eatery].
     */
    private inner class Data internal constructor() {
        val eateries: List<Eatery> = emptyList()
    }

}
