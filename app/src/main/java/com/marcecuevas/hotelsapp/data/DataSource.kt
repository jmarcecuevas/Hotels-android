package com.marcecuevas.hotelsapp.data

import androidx.lifecycle.LiveData
import java.util.concurrent.CompletableFuture

interface DataSource<T: Any> {

    fun getAll(): LiveData<List<T>>

    fun getAll(query: Query<T>): LiveData<List<T>>

    fun saveAll(list: List<T>): LiveData<List<T>>

    fun removeAll(list: List<T>): Boolean

    fun removeAll(): Boolean

    fun query(): Query<T> {
        return Query(this)
    }


    class Query<T: Any> constructor(private val dataSource: DataSource<T>) {

        val params: MutableMap<String,String> = mutableMapOf()

        fun has(property: String): Boolean {
            return params[property] != null
        }

        fun get(property: String): String? {
            return params[property]
        }

        fun where(property: String, value: String): Query<T> {
            params[property] = value
            return this
        }

        fun findAll(): LiveData<List<T>> {
            return dataSource.getAll(this)
        }
    }
}