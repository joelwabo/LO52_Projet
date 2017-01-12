package com.example.joelw.multimediaserver.Model;

import android.graphics.Color;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joelw on 13/12/2016.
 */

public class GetXMLTask extends AsyncTask<String, Void, String> {
    String mediaType;
    ArrayList<String> response = new ArrayList<String>();
    List<Media> mediaData = new ArrayList<Media>();
    @Override
    protected String doInBackground(String... urls) {
        try {
            sendRequest(urls[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendRequest(String url) throws JSONException {
        try {
            List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("typeRequest", mediaType ));

            HttpPost httpPost = new HttpPost(url);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            response.add(EntityUtils.toString(httpEntity));
            //We add response on the media list
            if (!response.isEmpty()) {
                JSONArray jsonArr = new JSONArray(response.get(0));
                for (int i =0; i< jsonArr.length(); i++) {
                    try {
                        JSONArray jsonArrMedia = new JSONArray(jsonArr.get(i).toString());
                        String path = url.replace("index.php","")+mediaType+"/"+jsonArrMedia.get(0).toString();
                        if(mediaType.equals("movie"))
                        {
                            mediaData.add(new Media(path+".jpg", jsonArrMedia.get(0).toString(), jsonArrMedia.get(1).toString(),path+".mp4"));
                        }
                        else
                        {
                            path=path+".JPG";
                            mediaData.add(new Media(jsonArrMedia.get(0).toString(), jsonArrMedia.get(1).toString(),path));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            response.remove(0);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onPostExecute() {

    }

    public List<Media> getMediaData(){
        return mediaData;
    }
    public void setMediaType(String type){
        this.mediaType = type;
    }
}
