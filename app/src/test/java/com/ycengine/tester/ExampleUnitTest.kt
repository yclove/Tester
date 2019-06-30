package com.ycengine.tester

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.ycengine.tester.vo.User
import com.ycengine.tester.vo.UserModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExampleUnitTest {
    private val data: MutableLiveData<List<UserModel>> = MutableLiveData()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun initData() {
        println("initData >> data = ${data.value}, size = ${data.value?.size?:-1}, first = ${data.value?.firstOrNull()?:-1}") // data = null, size = -1, first = -1
    }

    @Test
    fun addition_isCorrect() {
        data.value = listOf()
        println("data = ${data.value}, size = ${data.value?.size?:-1}, first = ${data.value?.firstOrNull()?:-1}") // data = [], size = 0, first = -1

        data.value = listOf(UserModel(RESPONSE = User(UAI = "A56B3C")))
        println("data = ${data.value}, size = ${data.value?.size?:-1}, first = ${data.value?.firstOrNull()}") // data = [UserModel(RESPONSE=User(UAI=A56B3C))], size = 1, first = UserModel(RESPONSE=User(UAI=A56B3C))

        assertEquals(4, 2 + 2)
    }

    @After
    fun clearData() {
        data.value = null
        println("clearData >> data = ${data.value}, size = ${data.value?.size?:-1}, first = ${data.value?.firstOrNull()?:-1}") // data = null, size = -1, first = -1
    }
}