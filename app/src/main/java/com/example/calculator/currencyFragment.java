package com.example.calculator;

import static com.example.calculator.key.key.KEY_FridayFactory;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.calculator.Database.MainData;
import com.example.calculator.Database.RoomDB;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class currencyFragment extends Fragment
{
    private static final String TAG = "currencyFragment";
    private static final String api_key = "api-key";
    private List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private List<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();
    private List<HashMap<String, String>> list2 = new ArrayList<HashMap<String, String>>();
    private List<HashMap<String, String>> listF = new ArrayList<HashMap<String, String>>();
    private List<MainData> mainDataList = new ArrayList<>();
    private Spinner sp1,sp2;
    private EditText edt1;
    private TextView txv2;
    private Button btn_cur,btn_update;
    private RoomDB roomDB;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearlayoutmanager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_fragment,container,false);

        roomDB = RoomDB.getInstance(view.getContext());
        mainDataList = roomDB.mainDao().getAll();

        Activity activity = (Activity) view.getContext();
        recyclerView = view.findViewById(R.id.recyclerView);
        linearlayoutmanager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearlayoutmanager);
        adapter = new RecyclerViewAdapter(activity,mainDataList);
        recyclerView.setAdapter(adapter);

        sp1 = view.findViewById(R.id.sp1);
        sp2 = view.findViewById(R.id.sp2);
        edt1 = view.findViewById(R.id.edt1);
        txv2 = view.findViewById(R.id.txv2);

        btn_cur = view.findViewById(R.id.btn_cur);
        btn_update = view.findViewById(R.id.btn_update);


        btn_cur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection();
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new currencyFragment()).commit();
            }
        });

        return view;
    }

    private void connection()
    {
        String c1 = sp1.getSelectedItem().toString();
        String c2 = sp2.getSelectedItem().toString();
        String M = edt1.getText().toString();

        final String url = "https://sg.fridayfactory.io/currency/"+c1+"/"+c2+"/"+M;
        new Thread(()->{
            try {
                URL URL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty(api_key,KEY_FridayFactory);
                InputStream is = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String line = in.readLine();
                StringBuffer json = new StringBuffer();
                while (line != null) {
                    json.append(line);
                    line = in.readLine();
                }

                MainData mainData = new MainData();

                JSONObject jsonObject = new JSONObject(String.valueOf(json));
                String from = jsonObject.getString("from");
                String to = jsonObject.getString("to");

                JSONObject jsonObject1 = jsonObject.getJSONObject("from");
                String currency = jsonObject1.getString("currency");
                String amount = jsonObject1.getString("amount");
                mainData.setCurrency1(currency);
                mainData.setResult1(amount);

                JSONObject jsonObject2 = jsonObject.getJSONObject("to");
                String currency2 = jsonObject2.getString("currency");
                String amount2 = jsonObject2.getString("amount");
                mainData.setCurrency2(currency2);
                mainData.setResult2(amount2);

                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("from",from);
                hashMap.put("to",to);

                HashMap<String,String> hashMap1 = new HashMap<>();
                hashMap1.put("currency",currency);
                hashMap1.put("amount",amount);

                HashMap<String,String> hashMap2 = new HashMap<>();
                hashMap2.put("currency",currency2);
                hashMap2.put("amount",amount2);

                HashMap<String,String> hashMapF = new HashMap<>();
                hashMapF.put("currency",currency);
                hashMapF.put("amount",amount);
                hashMapF.put("currency2",currency2);
                hashMapF.put("amount2",amount2);

                roomDB.mainDao().insert(mainData);

                list.add(hashMap);
                for(int i=0;i<list.size();i++) {
                    Log.d(TAG, list.get(i).get("from")+"..."+list.get(i).get("to"));
                }

                list1.add(hashMap1);
                for(int i=0;i<list.size();i++) {
                    Log.d(TAG, list1.get(i).get("currency")+"..."+list1.get(i).get("amount"));
                }

                list2.add(hashMap2);
                for(int i=0;i<list.size();i++) {
                    Log.d(TAG, list2.get(i).get("currency")+"..."+list2.get(i).get("amount"));
                    String result = list2.get(i).get("amount");
                    txv2.setText(result);
                }

                listF.add(hashMapF);
                for(int i=0;i<listF.size();i++) {
                    Log.d(TAG, listF.get(i).get("currency")+"..."+listF.get(i).get("amount")+"\n"+listF.get(i).get("currency2")+"..."+listF.get(i).get("amount2"));
                }

            }
            catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
