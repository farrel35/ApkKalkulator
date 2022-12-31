package farrel.tugasuas.apkkalkulator;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RiwayatHelper {
    private SharedPreferences riwayatDatabase;
    private Gson gson;

    public RiwayatHelper(Context context) {
        riwayatDatabase = context.getSharedPreferences("riwayatDatabase", Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveRiwayat(ArrayList<KalkulatorModel> riwayat){
        SharedPreferences.Editor editor = riwayatDatabase.edit();
        editor.putString("riwayat",gson.toJson(riwayat));
        editor.apply();
    }

    public ArrayList<KalkulatorModel> getRiwayat(){
        String riwayatString = riwayatDatabase.getString("riwayat",null);
        Type riwayatListType = new TypeToken<ArrayList<KalkulatorModel>>(){}.getType();
        ArrayList<KalkulatorModel> riwayat = gson.fromJson(riwayatString, riwayatListType);

        if(riwayat!= null) return  riwayat;
        else return new ArrayList<>();
    }
}
