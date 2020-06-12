package com.techasylum.jsonparseexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "";
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);
        mQueue = Volley.newRequestQueue(this);
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }
    private void jsonParse() {
        String url = "https://data.gov.in/node/6672029/datastore/export/json?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvZGF0YS5nb3YuaW5cLyIsImF1ZCI6Imh0dHBzOlwvXC9kYXRhLmdvdi5pblwvIiwiaWF0IjoxNTkxOTM1MjA4LCJuYmYiOjE1OTE5MzUyMDgsImV4cCI6MTU5MTkzNTIzOCwiZGF0YSI6eyJuaWQiOiI2NjcyMDI5In19.CUZbzJeBWaPI5hqm9VnNZ2eG0xnDINVmmVP41lGGrTY";

        /*JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count=0;
                        while (count<response.length()){
                            try {
                                JSONObject jsonObject=response.getJSONObject(count);
                                int id = jsonObject.getInt("id");
                                String title = jsonObject.getString("title");
                                mTextViewResult.append(id+","+title+"\n");
                                count++;
                            } catch (JSONException e) {
                                mTextViewResult.setText("inner........."+e.getMessage());

                                Toast.makeText(MainActivity.this, "inner response "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.i(LOG_TAG,"inner response "+e.getMessage());
                                e.printStackTrace();
                                count++;

                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "outer response "+error.getMessage(), Toast.LENGTH_SHORT).show();
                mTextViewResult.setText("ourtr........."+error.getMessage());

                Log.i(LOG_TAG,"outer response "+error.getMessage());
                error.printStackTrace();


            }
        });*/


       JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                                JSONArray jsonArray=response.getJSONArray("fields");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);
                                String statename = employee.getString("label");

                                String stateid = employee.getString("id");
                                String districtname = employee.getString("type");

                                mTextViewResult.append(stateid + "\n"+statename+"\n" + districtname+"\n\n" );

                            }
                           /*  try{
                                   JSONArray jsonArray2= response.getJSONArray("comments");

                                for (int i = 0; i < jsonArray2.length(); i++) {
                                    JSONObject employee1 = jsonArray2.getJSONObject(i);
                                    String id = employee1.getString("id");
                                    String body = employee1.getString("body");
                                    String postid = employee1.getString("postId");

                                    mTextViewResult.append(id + ", " + body+"\n"+postid+"\n" );
                                }
                                JSONObject employee3 = response.getJSONObject("profile");
                                String name = employee3.getString("name");
                                mTextViewResult.append("profile nmae "+name);
                            }catch (JSONException e) {
                                mTextViewResult.setText("fail on iii " + e.getMessage());
                                e.printStackTrace();
                            }*/

                        }
                        catch (JSONException e) {
                            mTextViewResult.setText("fail on s"+e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextViewResult.setText("fail on s"+error.getMessage());
                error.printStackTrace();
            }
        });

                mQueue.add(request);
    }
}
