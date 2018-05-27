package com.masyarakat.slamtein.cekongkoskuy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.masyarakat.slamtein.cekongkoskuy.adapter.CityAdapter;
import com.masyarakat.slamtein.cekongkoskuy.model.Ongkir;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.masyarakat.slamtein.cekongkoskuy.ApiEndPoint.ApiKey;
import static com.masyarakat.slamtein.cekongkoskuy.ApiEndPoint.BaseUrl;
import static com.masyarakat.slamtein.cekongkoskuy.ApiEndPoint.City;

public class CityActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CityAdapter cityAdapter;
    final static String TAG = CityActivity.class.getSimpleName();
    String province, province_id, name , bornday , address, telp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            province = extras.getString("province");
            province_id = extras.getString("province_id");
            name = extras.getString("name");
            bornday = extras.getString("bornday");
            address = extras.getString("address");
            telp = extras.getString("telp");

        }
        getCity();
    }

    private void getCity(){
        AndroidNetworking.get(BaseUrl + City)
                .addQueryParameter("province", province_id)
                .addHeaders("key", ApiKey)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject jsonObject = response.getJSONObject("rajaongkir");
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            List<Ongkir> ongkirs = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++){
                                Ongkir ongkir = new Ongkir();
                                JSONObject object = jsonArray.getJSONObject(i);
                                ongkir.city_id = object.getLong("city_id");
                                ongkir.city_name = object.getString("city_name");
                                ongkirs.add(ongkir);
                            }

                            recyclerView = findViewById(R.id.rcData);
                            cityAdapter = new CityAdapter(ongkirs ,R.layout.list_data , getApplicationContext(), CityActivity.this);
                            recyclerView.setAdapter(cityAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, String.valueOf(anError.getErrorCode()));
                    }
                });
    }

    public String getProvince(){
        return province;
    }

    public String  getName(){
        return name;
    }

    public String getBornday(){
        return bornday;
    }

    public String getAddress(){
        return address;
    }

    public String getTelp(){
        return telp;
    }


}



