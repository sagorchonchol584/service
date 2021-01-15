package com.example.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class otg_code extends AppCompatActivity implements RecyclerViewAdapter.ItemListener {
    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;
    String url = "http://192.168.2.117/android/test.php";

    ArrayList arrayLists;
    List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        arrayList = new ArrayList<>();
        //  arrayList.add(new DataModel("Item 1", R.drawable.battle, "#09A9FF"));
        //  arrayList.add(new DataModel("Item 2", R.drawable.beer, "#3E51B1"));
        //  arrayList.add(new DataModel("Item 3", R.drawable.ferrari, "#673BB7"));
        //  arrayList.add(new DataModel("Item 4", R.drawable.jetpack_joyride, "#4BAA50"));
        //  arrayList.add(new DataModel("Item 5", R.drawable.three_d, "#F94336"));
        //  arrayList.add(new DataModel("Item 6", R.drawable.terraria, "#0A9B88"));



        //  RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList, this);
        // recyclerView.setAdapter(adapter);


        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);


        Loginsss();
    }

    @Override
    public void onItemClick(Product item) {
      //  Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
    }


    public void Loginsss() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                productList = new ArrayList<>();
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject product = array.getJSONObject(i);
                        productList.add(new Product(
                                product.getString("productid")
                        ));

                        // Toast.makeText(getApplicationContext(),"Response :" + ss.getAdd().toString(), Toast.LENGTH_LONG).show();
                    }

                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(otg_code.this, productList, otg_code.this);
                    recyclerView.setAdapter(adapter);
                    AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(otg_code.this, 500);
                    recyclerView.setLayoutManager(layoutManager);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //   testshow.setText("Chack " + response.toString());
                //    Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }



        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(otg_code.this);
        requestQueue.add(request);
    }

}
