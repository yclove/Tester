package com.ycengine.tester

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

data class DataClass(var name: String, var isMan: Boolean = true)

class FunctionTest {
    private var data: DataClass? = null
    private var arrData: ArrayList<DataClass> = arrayListOf()

    @Before
    fun initData() {
        data = DataClass(name = "YC")
        arrData = arrayListOf(DataClass(name = "YC"), DataClass(name = "JH", isMan = false))
    }

    @Test
    fun addition_isCorrect() {
        // let 함수는 함수를 호출하는 객체를 이어지는 블록의 인자로 넘기고, 블록의 결과값을 반환합니다.
        var obj: String = data?.let {
            it
        }?.name ?: ""
        println("let : $obj")

        // apply 함수는 함수를 호출하는 객체를 이어지는 블록의 리시버 로 전달하고, 객체 자체를 반환합니다.
        obj = data?.apply {
            name = "JH"
        }?.name ?: ""
        println("apply : $obj")

        // run 함수는 이어지는 블럭 내에서 처리할 작업들을 넣어줄 수 있으며, 일반 함수와 마찬가지로 값을 반환하지 않거나 (Unit) 특정 값을 반환할 수도 있습니다.
        obj = data?.run {
            name = "YC"
            this
        }?.name ?: ""
        println("run : $obj")

        // with 함수는 인자로 받는 객체를 이어지는 블록의 리시버로 전달하며, 블록의 결과값을 반환합니다.
        obj = data?.let {
            with(it) {
                name = "JH"
                this
            }.name
        } ?: ""
        println("with : $obj")

        // takeIf
        obj = data?.takeIf {
            it.isMan
        }?.run {
            name = "YC"
            name
        } ?: "JH"
        println("takeIf : $obj")

        // also
        obj = data?.also {
            it.name = "JH"
        }?.name ?: ""
        println("also : $obj")

        // filter
        var arr: List<DataClass> = arrData.filter {
            it.name.equals(other = "YC", ignoreCase = false)
        }
        println("filter : $arr")

        // filterIndexed
        arr = arrData.filterIndexed { index, _ ->
            index > 0
        }
        println("filterIndexed : $arr")

        // map
        arr = arrData.map {
            it.name = "LOVE"
            it
        }
        println("map : $arr")

        // mapIndexed
        arr = arrData.mapIndexed { index, item ->
            if (index == 0) {
                item.name = "YC"
            } else {
                item.name = "JH"
            }
            item
        }
        println("mapIndexed : $arr")

        assertEquals(4, 2 + 2)
    }

    @After
    fun clearData() {
    }
}