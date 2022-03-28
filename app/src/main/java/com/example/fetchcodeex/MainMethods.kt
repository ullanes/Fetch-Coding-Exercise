package com.example.fetchcodeex

import org.json.JSONArray

class MainMethods {
    /*
   Removes all the blank and null names from JsonArray
    */
    fun removeBlankAndNoNames(jsonArray: JSONArray): JSONArray {
        var len = jsonArray.length()
        var i = 0

        while(i < len){
            if(jsonArray.getJSONObject(i).get("name").equals("")  || jsonArray.getJSONObject(i).get("name").equals(null)){
                jsonArray.remove(i)
                len--
                if(i >0) i--
            } else {
                i++
            }
        }
        return jsonArray
    }

    /*
     Adds a jsondata to and ArrayList of FetchEx Data Class
  */
    fun addJsonInDataClass(jsonArray: JSONArray):ArrayList<FetchEx>{
        var dataList = ArrayList<FetchEx>()
        for(i in 0 until jsonArray.length()){
            val id = jsonArray.getJSONObject(i).getInt("id")
            val lisId = jsonArray.getJSONObject(i).getInt("listId")
            val name = jsonArray.getJSONObject(i).getString("name")
            dataList.add(FetchEx(id,lisId, name))
        }
        return dataList
    }

    /*
       Sorts an ArrayList of the json file by listId then by name
    */

    fun sortDataClass(dataList:ArrayList<FetchEx>) :ArrayList<FetchEx>{
        dataList.sortWith(compareBy<FetchEx>{it.listId}.thenBy { it.name })
        return dataList
    }
}