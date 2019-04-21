package com.test.homeautomation.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.test.homeautomation.R;
import com.test.homeautomation.api.ApiUtils;
import com.test.homeautomation.api.deviceRequest;
import com.test.homeautomation.models.Device;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sun.rmi.runtime.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import static okhttp3.MediaType.*;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Device> devices = new ArrayList<>();
    deviceRequest d = new deviceRequest();
    PopupWindow add_device_popup;
    Call<List<deviceRequest>> getDS;
    Call<Void> delete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add_device_popup = new PopupWindow();
        setContentView(R.layout.activity_main);
        test();
        initRV();
        //get devices from server to RV
        getDS= ApiUtils.getApiService().getDevices();
        getDS.enqueue(new Callback<List<deviceRequest>>() {
            @Override
            public void onResponse(Call<List<deviceRequest>>
                                   call, Response<List<deviceRequest>> response) {
                if (!response.isSuccessful()){

                    Log.v("Code: " + response.code());
                    Toast.makeText(getApplicationContext(),
                            "Error",Toast.LENGTH_SHORT).show();

                }

                String jsonResponse = response.body().toString();
                writeRv(jsonResponse);

                Toast.makeText(getApplicationContext(),
                        response.body().toString(),Toast.LENGTH_SHORT).show();
                Log.v("post submitted to API." + response.body().toString());

            }
            @Override
            public void onFailure(Call<List<deviceRequest>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Unable to submit post to API" ,Toast.LENGTH_SHORT).show();
                Log.v(t.getMessage())
                call.cancel();
            }
        });



        delete = ApiUtils.getApiService().deleteDevice(d.getId());
        delete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Log.v("Code: " + response.code());
                    Toast.makeText(getApplicationContext(),
                            "Error",Toast.LENGTH_SHORT).show();
                }
                //get adapter pos not found
                deleteRV(RVAdapter.getAdapterPosition());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.v(t.getMessage())
                Toast.makeText(getApplicationContext(),
                        "Unable to submit post to API" ,Toast.LENGTH_SHORT).show();
                call.cancel();


            }
        });


    }





    private void test() {
        devices.add(new Device("d1", true, 1));
        devices.add(new Device("d2", false, 2));
        devices.add(new Device("d3", true, 3));
        devices.add(new Device("d4", false, 4));
        devices.add(new Device("d5", true, 5));
        devices.add(new Device("d6", false, 6));
        devices.add(new Device("d7", true, 7));
        devices.add(new Device("d8", false, 8));
        devices.add(new Device("d9", true, 9));
        devices.add(new Device("d10", false, 10));
        devices.add(new Device("d11", true, 11));
        devices.add(new Device("d12", false, 12));
        devices.add(new Device("d13", true, 13));
        devices.add(new Device("d14", false, 14));
        devices.add(new Device("d15", true, 15));
        devices.add(new Device("d16", false, 16));
        devices.add(new Device("d17", true, 17));
        devices.add(new Device("d18", false, 18));
    }

    private void initRV() {
        RecyclerView rv = findViewById(R.id.my_recycler_view);
        RVAdapter adapter = new RVAdapter(devices);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


    public void add_device_popup(View view) {
        add_device_popup.init_popup(this);
        add_device_popup.show_popup(0, 0);
    }

    public void submit_device(View view) {
        //submit device

        Call<List<deviceRequest>>  add =
                ApiUtils.getApiService().addDevice(d.getName(),d.getPin());


        RequestBody requestBody = RequestBody.create(MediaType.parse(d.getName())
                ,MediaType.parse(d.getPin().toString()));

        add.enqueue(new Callback<List<deviceRequest>>() {
            @Override
            public void onResponse(Call<List<deviceRequest>> call, Response<List<deviceRequest>> response) {
                if(!response.isSuccessful()){
                    Log.v("Code: " + response.code());
                    Toast.makeText(getApplicationContext(),
                            "Error",Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(getApplicationContext(),
                        response.body().toString(),Toast.LENGTH_SHORT).show();
                Log.v("post submitted to API." + response.body().toString());


            }

            @Override
            public void onFailure(Call<List<deviceRequest>> call, Throwable t) {
                Log.v(t.getMessage())
                Toast.makeText(getApplicationContext(),
                        "Unable to submit post to API" ,Toast.LENGTH_SHORT).show();
                call.cancel();

            }
        });

        add_device_popup.dismiss_popup();
    }


//    private void fetchJSON(){
//          update,getDS,getTemp,getD,delete;
//
//
//        update = ApiUtils.getApiService().updateState(d.getId(),d.getState());
//        getD = ApiUtils.getApiService().getDevice(d.getId());
//        getTemp = ApiUtils.getApiService().getTempreture();
//
//        update.enqueue(new Callback<List<deviceRequest>>() {
//            @Override
//            public void onResponse(Call<List<deviceRequest>> call, Response<List<deviceRequest>> response) {
//                if(!response.isSuccessful()){
//                    Log.v("Code: " + response.code());
//                    Toast.makeText(getApplicationContext(),
//                            "Error",Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(getApplicationContext(),
//                        response.body().toString(),Toast.LENGTH_SHORT).show();
//                Log.v("post submitted to API." + response.body().toString());
//
//            }
//
//            @Override
//            public void onFailure(Call<List<deviceRequest>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),
//                        "Unable to submit post to API" + ,Toast.LENGTH_SHORT).show();
//                call.cancel();
//            }
//        });
//        getD.enqueue(new Callback<List<deviceRequest>>() {
//            @Override
//            public void onResponse(Call<List<deviceRequest>> call, Response<List<deviceRequest>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<deviceRequest>> call, Throwable t) {
//
//            }
//        });
//
//        getTemp.enqueue(new Callback<List<deviceRequest>>() {
//            @Override
//            public void onResponse(Call<List<deviceRequest>> call, Response<List<deviceRequest>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<deviceRequest>> call, Throwable t) {
//
//            }
//        });
//
//            }






    //helper to retrive data in recyclerview from request
    private void writeRv(String response){

        try {
            JSONObject obj = new JSONObject(response);

            if(obj.optString("status").equals("true")){

                ArrayList<deviceRequest> deviceRequestArrayList = new ArrayList<>();
                JSONArray dataArray  = obj.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {
                d.setId(dataobj.getString("id"));
                d.setName(dataobj.getString("name"));
                d.setState(dataobj.getString("state"));
                d.setPin(dataobj.getString("pin"));

                deviceRequestArrayList.add(d);
                }
                    /*here**/
            }
            else {
                Toast.makeText(MainActivity.this,
                        obj.optString("message")+"", Toast.LENGTH_SHORT).show();
            }

            }
        catch (JsonIOException e){
            e.printStackTrace();
        }

    }


    //helper to delete from recyclerview
    private void deleteRV(int pos){
        devices.remove(pos);
        notifyAll();
    }



    }






