package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_getQuote;
    ListView lv_Quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_getQuote = findViewById(R.id.btn_getQuote);

        lv_Quote = findViewById(R.id.lv_Quote);

        btn_getQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuoteService quoteService = new QuoteService(MainActivity.this);
                quoteService.getCityForecastByName(new QuoteService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(MotivationQuoteModel motivationQuoteModel) {
                        List<MotivationQuoteModel> list = new ArrayList<>();
                        list.add(motivationQuoteModel);
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, list);
                        lv_Quote.setAdapter(arrayAdapter);
                    }
                });
            }
        });
    }
}