package model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Source {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

//    public static Source parseSourceResponse(JSONObject jsonObject){
//        Source item = new Source();
//
//        item.id = jsonObject.optString("id");
//        item.name = jsonObject.optString("name");
//        return item;
//    }
}
