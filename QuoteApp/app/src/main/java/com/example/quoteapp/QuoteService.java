package com.example.weatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class QuoteService {
    Context context;

    public QuoteService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(MotivationQuoteModel weatherReport);
    }

    public void getCityForecastByName(final VolleyResponseListener volleyResponseListener) {
        String url = "https://api.adviceslip.com/advice";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject quoteJSON;
                String quote;

                try {
                    quoteJSON = response.getJSONObject("slip");;
                    quote = quoteJSON.getString("advice");


                    MotivationQuoteModel report = new MotivationQuoteModel(quote);

                    volleyResponseListener.onResponse(report);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                Toast.makeText(context, quoteJSON.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error occurred", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something is wrong");
            }
        });

        RequestQueueSingleton.getInstance(context).addToRequestQueue(request);
    }
}
