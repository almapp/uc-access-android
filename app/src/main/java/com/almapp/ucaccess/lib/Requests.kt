package com.almapp.ucaccess.lib

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.task

/**
 * Created by patriciolopez on 05-02-16.
 */
fun Request.promise(): Promise<Triple<Request, Response, ByteArray>, Exception> = task {
    response()
}
