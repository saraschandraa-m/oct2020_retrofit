package model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class News {

    @SerializedName("articles")
    public ArrayList<Article> articlesList;

    @SerializedName("status")
    public String status;

    @SerializedName("totalResults")
    public int totalResults;

//    public static News parseNewsResponse(JSONObject jsonObject){
//        News item = new News();
//
//        item.status = jsonObject.optString("status");
//        item.totalResults = jsonObject.optInt("totalResults");
//
//        JSONArray articlesArray = jsonObject.optJSONArray("articles");
//        item.articles = new ArrayList<>();
//        if(articlesArray.length() > 0){
//            for (int i=0 ;i <articlesArray.length(); i++){
//                Article newArticle = Article.parseArticleResponse(articlesArray.optJSONObject(i));
//                item.articles.add(newArticle);
//            }
//        }
//
//        return item;
//    }
}
