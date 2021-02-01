package com.example.domain.network.resource

import com.example.domain.network.AppException


class Resource<T> private constructor(
    val status: Status, val data: T?, val exception: AppException?
) {

    var code: Int = -1

    enum class Status {
        SUCCESS, ERROR, DATA_NOT_AVAILABLE, LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return if (data != null)
                Resource(Status.SUCCESS, data, null)
            else
                Resource(Status.DATA_NOT_AVAILABLE, data, null)
        }

        fun <T> error(exception: AppException?): Resource<T> {
            return Resource(Status.ERROR, null, exception)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    fun isSuccess(): Boolean {
        return data != null && exception == null
    }
}