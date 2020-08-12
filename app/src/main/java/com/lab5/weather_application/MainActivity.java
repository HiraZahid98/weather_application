package com.lab5.weather_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lab5.weather_application.retrofit.ApiClient;
import com.lab5.weather_application.retrofit.ApiInterface;
import com.lab5.weather_application.retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView search;
    TextView tempText, descText , humidityText;
    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search_button);
        tempText = findViewById(R.id.tempText);
        descText = findViewById(R.id.descText);
        humidityText = findViewById(R.id.humidityText);
        textField = findViewById(R.id.textField);

        search.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                getWeatherData(textField.getText().toString().trim());
            }
        });
    }
    private void getWeatherData(String name){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Example> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                tempText.setText("Temp" + " " +response.body().getMain().getTemp() + "c");
                descText.setText("Feels Like" + " " +response.body().getMain().getFeels_like());
                humidityText.setText("Humidity" + " " +response.body().getMain().getHumidity());

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t){

            }


        });
    }
}
