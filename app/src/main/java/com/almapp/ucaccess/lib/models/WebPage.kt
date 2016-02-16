package com.almapp.ucaccess.lib.models

/**
 * Created by patriciolopez on 19-01-16.
 */
data class WebPage(
        val id: String? = null,
        val name: String = "",
        val description: String = "",
        val URL: String? = null,
        val imageURL: String? = null,
        var selected: Boolean = true
)
