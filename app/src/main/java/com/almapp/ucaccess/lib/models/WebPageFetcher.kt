package com.almapp.ucaccess.lib.models

import com.almapp.ucaccess.lib.promise
import com.github.kittinunf.fuel.Fuel
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.then
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Created by patriciolopez on 19-01-16.
 */
class WebPageFetcher(val URL: String) {
    fun fetch(): Promise<List<WebPageGroup>, Exception> {
        return Fuel.get(this.URL).promise() then {
            val (request, response, bytes) = it
            // TODO: Fix this mess, please.
            // TODO: Forgive me :(
            val result = ArrayList<WebPageGroup>()
            val array = JSONArray(String(bytes))
            for (index in 0..array.length() - 1) {
                val item = array.get(index) as JSONObject
                var categories = ArrayList<WebPageCategory>()
                var categoriesJSON = item.getJSONArray("categories")
                for (i in 0..categoriesJSON.length() - 1) {
                    val catJSON = categoriesJSON.get(i) as JSONObject
                    var pages = ArrayList<WebPage>()
                    val pagesJSON = catJSON.getJSONArray("services")
                    for (j in 0..pagesJSON.length() - 1) {
                        val webJSON = pagesJSON.get(j) as JSONObject
                        var page = WebPage(id = webJSON.optString("id"), name = webJSON.optString("name"), description = webJSON.optString("description"), imageURL = webJSON.optString("image"), URL = webJSON.optString("url"))
                        pages.add(page)
                    }
                    var cat = WebPageCategory(name = catJSON.getString("name"), detail = catJSON.optString("detail"), webpages = pages)
                    categories.add(cat)
                }
                var group = WebPageGroup(name = item.getString("name"), detail = item.optString("detail"), categories = categories)
                result.add(group)
            }
            result
        }
    }
}
