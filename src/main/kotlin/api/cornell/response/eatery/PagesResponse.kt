package api.cornell.response.eatery

import api.cornell.data.eatery.Page

/**
 * [PagesResponse] is the response of a Pages request.
 */
internal class PagesResponse private constructor() {

    /**
     * Main data of response.
     */
    private val data: Data = Data()
    /**
     * Obtain the pages information.
     */
    val pages: List<Page> get() = data.pages

    /**
     * [Data] is an uninteresting class that just holds an array of [Page].
     */
    private inner class Data internal constructor() {
        val pages: List<Page> = emptyList()
    }

}
