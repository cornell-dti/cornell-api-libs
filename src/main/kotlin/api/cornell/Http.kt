package api.cornell

import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.await
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import java.io.InputStreamReader

private typealias Parameters = List<Pair<String, Any?>>

/**
 * [Http] is used for providing support for API access.
 *
 * @param prefix prefix for URL.
 * @param gson gson used for data deserialization.
 */
internal class Http(private val prefix: String, private val gson: Gson) {

    /**
     * [request] is a function that fetch the result from Cornell APIs without the prefix.
     *
     * @param path API path without prefix.
     * @param parameters defines a list of parameters to give. This is optional.
     * @return parsed response.
     */
    suspend inline fun <reified T : Any> request(path: String, parameters: Parameters? = null): T {
        return (prefix + path).httpGet(parameters = parameters).await(deserializer())
    }

    private inline fun <reified T : Any> deserializer(): Deserializable<T> =
            object : Deserializable<T> {
                override fun deserialize(response: Response): T =
                        gson.fromJson(InputStreamReader(response.body().toStream()), T::class.java)

            }
}
