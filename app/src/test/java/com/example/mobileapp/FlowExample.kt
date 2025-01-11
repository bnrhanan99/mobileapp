package com.example.mobileapp
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Test
class FlowExample {

    @Test
    fun xyz():Unit= runBlocking{
        (0..10)
            .asFlow()
            .onEach{ println(it) }
            .collect()
    }
}