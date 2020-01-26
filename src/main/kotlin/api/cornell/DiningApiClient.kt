package api.cornell

import api.cornell.data.eatery.Eatery
import api.cornell.data.eatery.EateryType
import api.cornell.data.eatery.Page
import api.cornell.data.eatery.PayMethod
import api.cornell.response.eatery.EateriesResponse
import api.cornell.response.eatery.PagesResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer

/**
 * [DiningApiClient] defines a set of operation that the Cornell Dining API Client supports.
 */
object DiningApiClient {

    private val gson: Gson = GsonBuilder()
            .registerTypeAdapter(EateryType::class.java,
                    JsonDeserializer<EateryType> { json, _, _ ->
                        val value = if (json.isJsonObject) {
                            val obj = json.asJsonObject
                            obj["descr"].asString
                        } else {
                            json.asString
                        }
                        EateryType.fromString(value)
                    })
            .registerTypeAdapter(PayMethod::class.java,
                    JsonDeserializer<PayMethod> { json, _, _ ->
                        val value = if (json.isJsonObject) {
                            val obj = json.asJsonObject
                            obj["descr"].asString
                        } else {
                            json.asString
                        }
                        PayMethod.fromString(value)
                    })
            .create()
    private val http: Http = Http(prefix = "https://now.dining.cornell.edu/api/1.0", gson = gson)

    suspend fun getPages(): List<Page> =
            http.request<PagesResponse>(path = "/config/pages.json").pages

    suspend fun getEateries(): List<Eatery> =
            http.request<EateriesResponse>(path = "/dining/eateries.json").eateries
}
