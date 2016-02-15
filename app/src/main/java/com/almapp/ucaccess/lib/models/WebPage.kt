package com.almapp.ucaccess.lib.models

/**
 * Created by patriciolopez on 19-01-16.
 */
data class WebPage(
        val id: String?,
        val name: String?,
        val description: String?,
        val URL: String?,
        val imageURL: String?,
        var selected: Boolean = true
)
