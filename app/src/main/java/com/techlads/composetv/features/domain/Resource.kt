package com.techlads.composetv.features.domain

import java.lang.Exception

class Resource<out T>(val  data: T? = null, val message: String? = null) {


    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(data)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(null, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(data, null)
        }
    }
}