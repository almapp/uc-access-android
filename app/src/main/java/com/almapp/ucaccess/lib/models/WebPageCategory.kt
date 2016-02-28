package com.almapp.ucaccess.lib.models
import java.io.Serializable
/**
 * Created by patriciolopez on 19-01-16.
 */
data class WebPageCategory(
        val name: String,
        val detail: String? = null,
        val webpages: List<WebPage> = emptyList<WebPage>()
) : Serializable
