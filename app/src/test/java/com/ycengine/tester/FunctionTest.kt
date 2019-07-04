package com.ycengine.tester

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

data class DataClass(var name: String, var isMan: Boolean = true, var profile: String = "")
enum class ProfileType(val stringRes: Int) {
    NEW(R.string.profile_new),
    OLD(R.string.profile_old);

    companion object {
        fun valueIndex(index: Int) = values().find { it.ordinal == index } ?: NEW
    }
}

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
        // let : 이어지는 블록의 인자로 넘기고, 블록의 결과값을 반환
        var obj: String = data?.let {
            it
        }?.name ?: ""
        println("let : $obj")

        // apply : 이어지는 블록의 리시버로 전달하고, 객체 자체를 반환
        obj = data?.apply {
            name = "JH"
        }?.name ?: ""
        println("apply : $obj")

        // run : 이어지는 블록의 리시버로 전달하고, 블록의 결과값을 반환
        obj = data?.run {
            name = "YC"
            this
        }?.name ?: ""
        println("run : $obj")

        // with : 인자로 받는 객체를 이어지는 블록의 리시버로 전달하고, 블록의 결과값을 반환
        obj = data?.let {
            with(it) {
                name = "JH"
                this
            }.name
        } ?: ""
        println("with : $obj")

        // takeIf : 블록의 조건이 참일 경우 객체 자체를 반환
        obj = data?.takeIf {
            it.isMan
        }?.run {
            name = "YC"
            name
        } ?: "JH"
        println("takeIf : $obj")

        // also : 블록 내에 전달된 수신객체를 그대로 다시 반환
        obj = data?.also {
            it.name = "JH"
        }?.name ?: ""
        println("also : $obj")

        /////////////////////////////////////////////////////
        //////////////////// Collection ////////////////////
        /////////////////////////////////////////////////////

        // find : 주어진 predicate 에 만족하는 첫번째 원소를 반환. 따라서 명시적으로 firstOrNull 을 사용해도 됨.
        val type: ProfileType = ProfileType.values().find {
            it.ordinal == 1
        } ?: ProfileType.NEW
        println("find : ${type.stringRes}")

        // filter
        var arr: List<DataClass> = arrData.filter {
            it.name.equals(other = "YC", ignoreCase = false)
        }
        val item: String? = arr.firstOrNull() as? String
        println("filter : $arr, $item")

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