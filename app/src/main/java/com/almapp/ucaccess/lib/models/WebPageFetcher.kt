package com.almapp.ucaccess.lib.models

import com.almapp.ucaccess.lib.promise
import com.github.kittinunf.fuel.Fuel
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.then
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by patriciolopez on 19-01-16.
 */
class WebPageFetcher(val URL: String) {
    fun fetch(): Promise<List<WebPageGroup>, Exception> {
        return Fuel.get(this.URL).promise() then {
            val (request, response, bytes) = it
            val array = JSONArray(String(bytes))

            listOf(
                    WebPageGroup(name = "Oficiales", categories = listOf(
                            WebPageCategory(name = "Requieren Credenciales", webpages = listOf(
                                    WebPage(name = "Portal UC"),
                                    WebPage(name = "Cursos UC")
                            )),
                            WebPageCategory(name = "Requieren Holas", webpages = listOf(
                                    WebPage(name = "Otros UC"),
                                    WebPage(name = "Ewe UC")
                            ))
                    )),
                    WebPageGroup(name = "Comunidad"),
                    WebPageGroup(name = "Partidos"),
                    WebPageGroup(name = "Facultades")
            )
        }
    }
}
