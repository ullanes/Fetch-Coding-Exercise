package com.example.fetchcodeex

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.internal.matchers.Null

class MainActivityTest {

    var jsonArray = JSONArray()
    var jsonObj1 = JSONObject()
    var jsonObj2 = JSONObject()
    var jsonObj3 = JSONObject()
    var jsonObj4 = JSONObject()
    var jsonObj5 = JSONObject()
    var dataArray = ArrayList <FetchEx>()

    @Before
    fun setUp() {
        jsonObj1.put("id", 75)
        jsonObj1.put("listId",4)
        jsonObj1.put("name", "Item 75")

        jsonObj2.put("id", 68)
        jsonObj2.put("listId",1)
        jsonObj2.put("name", "")

        jsonObj3.put("id", 99)
        jsonObj3.put("listId",2)
        jsonObj3.put("name", "Item 99")

        jsonObj4.put("id", 50)
        jsonObj4.put("listId",3)
        jsonObj4.put("name", JSONObject.NULL)

        jsonObj5.put("id", 2)
        jsonObj5.put("listId",1)
        jsonObj5.put("name", "Item 2")

        jsonArray.put(jsonObj1).put(jsonObj2).put(jsonObj3).put(jsonObj4).put(jsonObj5)

        dataArray.add(FetchEx(jsonObj1.getInt("id"), jsonObj1.getInt("listId"), jsonObj1.getString("name")))
        dataArray.add(FetchEx(jsonObj3.getInt("id"), jsonObj3.getInt("listId"), jsonObj3.getString("name")))
        dataArray.add(FetchEx(jsonObj5.getInt("id"), jsonObj5.getInt("listId"), jsonObj5.getString("name")))
        println()
    }

    @Test
    fun removeBlankAndNoNames() {
        var expected = JSONArray().put(jsonObj1).put(jsonObj3).put(jsonObj5)
        println("Before remove $jsonArray")
        var result = MainMethods().removeBlankAndNoNames(jsonArray).toString()
        println("After remove $jsonArray")
        println("$expected = $result ")
        assertEquals(expected.toString(), result)
    }

    @Test
    fun addJsonInDataClass() {
        var test = JSONArray().put(jsonObj1).put(jsonObj3).put(jsonObj5)
        var result = MainMethods().addJsonInDataClass(test).toString()
        println("$dataArray = $result")
        assertEquals(dataArray.toString(),result)

    }

    @Test
    fun sortDataClass() {
        var sortedData = ArrayList<FetchEx>()
        sortedData.add(FetchEx(jsonObj5.getInt("id"), jsonObj5.getInt("listId"), jsonObj5.getString("name")))
        sortedData.add(FetchEx(jsonObj3.getInt("id"), jsonObj3.getInt("listId"), jsonObj3.getString("name")))
        sortedData.add(FetchEx(jsonObj1.getInt("id"), jsonObj1.getInt("listId"), jsonObj1.getString("name")))
        println("DATA BEFORE $dataArray")
        var result = MainMethods().sortDataClass(dataArray).toString()
        println("DATA AFTER $dataArray")
        println("$sortedData =  $dataArray")

        assertEquals(sortedData.toString(), result)
    }
}