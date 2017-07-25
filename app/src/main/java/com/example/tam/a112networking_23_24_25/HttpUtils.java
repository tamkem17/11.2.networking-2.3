package com.example.tam.a112networking_23_24_25;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tam on 7/25/2017.
 */

public class HttpUtils {
    public static String urlContentPost(String address, String ... paramNamesAndValues) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(address);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (int i = 0; i <= paramNamesAndValues.length - 1; i = i + 2){
            String paramName = paramNamesAndValues[i];
            String paramValue = paramNamesAndValues[i+1];
            params.add(new BasicNameValuePair(paramName, paramValue));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(entity);
        ResponseHandler<String> handler = new BasicResponseHandler();
        return(client.execute(httpPost, handler));

    }
}
