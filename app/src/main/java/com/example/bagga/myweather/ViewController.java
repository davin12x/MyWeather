package com.example.bagga.myweather;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewController extends AppCompatActivity {
    TextView temp ;
    Services downloadData;
    TextView city;
    TextView cloud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_controller);
        temp = (TextView)findViewById(R.id.temp);
        city = (TextView)findViewById(R.id.city);
        cloud = (TextView)findViewById(R.id.cloud);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/comic.ttf");
        city.setTypeface(custom_font);
        temp.setTypeface(custom_font);
        downloadData = new Services();
        try{
            String data = downloadData.execute("http://api.apixu.com/v1/current.json?key=0f5936975be54b10ad9183811160704&q=43.4256890,-80.4408190").get();
            if (data != null){
                downloadData.result(data);
                System.out.println(downloadData.Temp());
                temp.setText(downloadData.Temp());
                city.setText(downloadData.Name());
                cloud.setText(downloadData.Text());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
