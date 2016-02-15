package com.almapp.ucaccess.lib.models

/**
 * Created by patriciolopez on 19-01-16.
 */
data class WebPageGroup(val name: String, val detail: String? = null, val categories: List<WebPageCategory> = emptyList<WebPageCategory>()) {

}
