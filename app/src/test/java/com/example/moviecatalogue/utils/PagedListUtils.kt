@file:Suppress("DEPRECATION")

package com.example.moviecatalogue.utils

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import org.mockito.Mockito.mock

object PagedListUtil {

    fun <T> mockPagedList(list: List<T>): PagedList<Any> {
        val pagedList = mock(PagedList::class.java) as PagedList<Any>
        Mockito.`when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        Mockito.`when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}
