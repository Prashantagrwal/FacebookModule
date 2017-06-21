package com.example.dell.facebookmodule.module;


import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONObject;

public class GraphApi
{

    ObjectInterface objectRes;
    int count;
    public GraphApi(ObjectInterface objectRes) {

        this.objectRes=objectRes;
    }

    public void getResponse(String string) {

        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                 //       Log.e("response",object.toString());
                        objectRes.Object(object);
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields",string);
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void getResponse(String page_id, String feild, String after, Boolean check, final int count)
    {
     this.count=count;
        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+page_id,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {

                        objectRes.Object(response.getJSONObject(),count);
                    }
                });

        Bundle parameters = new Bundle();
        if(check)
        parameters.putString("fields", feild);
        else {
            parameters.putString("fields", feild);
            parameters.putString("after", after);
        }
            request.setParameters(parameters);
        request.executeAsync();
    }
}
