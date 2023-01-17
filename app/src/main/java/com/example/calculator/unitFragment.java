package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.Database2.MainData;
import com.example.calculator.Database2.RoomDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class unitFragment extends Fragment
{
    private static final String TAG = "unitFragment";
    private Spinner spType,spMeasure,spMeasure2;
    private String[][] array = new String[][]{{"g-force","m/s2"},{"rad", "deg", "grad", "arcmin", "arcsec"},{"VA", "mVA", "kVA", "MVA", "GVA"},{"nm2", "μm2", "mm2", "cm2", "m2", "ha", "km2", "in2", "yd2", "ft2", "ac", "mi2"},
            {"c", "mC", "μC", "nC", "pC"},{"A", "mA", "kA"},{"b", "Kb", "Mb", "Gb", "Tb", "B", "KB", "MB", "GB", "TB"},{"ea","dz"},{"Wh","mWh","kWh","MWh","GWh","J","kJ"},{"N","kN","lbf"},{"mHz","Hz","kHz","MHz","GHz","THz","rpm","deg/s","rad/s"},
            {"lx","ft-cd"},{"nm","μm","mm","cm","m","km","in","yd","ft-us","ft","fathom","mi","nMi"},{"mcg","mg","g","kg","mt","oz","lb","t"},{"min/km","s/m","min/mi","s/ft"},{"ppm","ppb","ppt","ppq"},
            {"pcs","bk-doz","cp","doz-doz","doz","gr-gr","gros","half-dozen","long-hundred","ream","scores","sm-gr","trio"},{"W","mW","kW","MW","GW","PS","Btu/s","ft-lb/s","hp"},{"Pa","kPa","MPa","hPa","bar","torr","psi","ksi","inHg"},
            {"VARh","mVARh","kVARh","MVARh","GVARh"},{"VAR","mVAR","kVAR","MVAR","GVAR"},{"m/s","km/h","mph","knot","ft/s","ft/min"},{"C","K","F","R"},{"ns","mu","ms","s","min","h","d","week","month","year"},
            {"V","mV","kV"},{"mm3","cm3","ml","cl","dl","l","kl","m3","km3","krm","tsk","msk","kkp","glas","kanna","tsp","Tbs","in3","fl-oz","cup","pnt","qt","gal","ft3","yd3"},{"mm3/s","cm3/s","ml/s","cl/s","dl/s","l/s","l/min","l/h","kl/s","kl/min","kl/h","m3/s","m3/min","m3/h","km3/s","tsp/s","Tbs/s","in3/s","in3/min","in3/h","fl-oz/s","fl-oz/min","fl-oz/h","cup/s","pnt/s","pnt/min","pnt/h","qt/s","gal/s","gal/min","gal/h","ft3/s","ft3/min","ft3/h","yd3/s","yd3/min","yd3/h"}};
    private Button btn_convert,updateList;
    private EditText edV;
    private TextView txv_result;
    private RoomDB roomDB;
    private List<MainData> mainDataList = new ArrayList<>();
    private RecyclerView_Unit recyclerView_unit;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unit_fragment,container,false);

        roomDB = RoomDB.getInstance(view.getContext());
        mainDataList = roomDB.mainDao().getAll();

        spType = view.findViewById(R.id.spType);
        spMeasure = view.findViewById(R.id.spMeasure);
        spMeasure2 = view.findViewById(R.id.spMeasure2);

        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,array[i]);
                spMeasure.setAdapter(adapter);
                spMeasure2.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        btn_convert = view.findViewById(R.id.btn_convert);
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                converter();
            }
        });

        edV = view.findViewById(R.id.edV);
        txv_result = view.findViewById(R.id.txv_result);

        recyclerView = view.findViewById(R.id.recyclerView_Unit);
        Activity activity = (Activity) view.getContext();
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView_unit = new RecyclerView_Unit(activity,mainDataList);
        recyclerView.setAdapter(recyclerView_unit);

        updateList = view.findViewById(R.id.updateList);
        updateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new unitFragment()).commit();
            }
        });

        return view;
    }
    private void converter() {
        MainData mainData = new MainData();
        String value = edV.getText().toString();
        mainData.setFrom_value(value);
        String from = spMeasure.getSelectedItem().toString();
        String to = spMeasure2.getSelectedItem().toString();
        String require = spType.getSelectedItem().toString();
        String apiKey = "581|eihPFjrEYmHTIkj7JFz09IWqUiH8cWDgbAtwYPmn";
        final String url = "https://zylalabs.com/api/189/measurement+unit+conversion+api/202/unit+converter?value=" + value + "&from=" + from + "&to=" + to + "&measure=" + require;
        new Thread(() -> {
            try {
                URL URL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization","Bearer "+apiKey);
                connection.connect();
                InputStream is = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String line = in.readLine();
                StringBuffer json = new StringBuffer();
                while (line != null) {
                    json.append(line);
                    line = in.readLine();
                }
                JSONObject jsonObject = new JSONObject(String.valueOf(json));
                String measure = jsonObject.getString("measure");
                mainData.setMeasure(measure);

                JSONObject jsonObject1 = jsonObject.getJSONObject("from");
                String abbrF = jsonObject1.getString("abbr");
                mainData.setFrom_abbr(abbrF);
                String measureF = jsonObject1.getString("measure");
                mainData.setFrom_measure(measureF);
                String systemF = jsonObject1.getString("system");
                mainData.setFrom_system(systemF);
                String singularF = jsonObject1.getString("singular");
                mainData.setFrom_singular(singularF);
                String pluralF = jsonObject1.getString("plural");
                mainData.setFrom_plural(pluralF);

                JSONObject jsonObject2 = jsonObject.getJSONObject("to");
                String abbrT = jsonObject2.getString("abbr");
                mainData.setTo_abbr(abbrT);
                String measureT = jsonObject2.getString("measure");
                mainData.setTo_measure(measureT);
                String systemT = jsonObject2.getString("system");
                mainData.setTo_system(systemT);
                String singularT = jsonObject2.getString("singular");
                mainData.setTo_singular(singularT);
                String pluralT = jsonObject2.getString("plural");
                mainData.setTo_plural(pluralT);

                double value_ = jsonObject.getDouble("value");
                mainData.setValue(value_);
                String result = jsonObject.getString("result");
                mainData.setResult(result);

                Log.d(TAG, "RESULT..." + "= " + result);
                Log.d(TAG, "VALUE..." + "= " + String.valueOf(value_));

                txv_result.setText(String.valueOf(value_));

                roomDB.mainDao().insert(mainData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
