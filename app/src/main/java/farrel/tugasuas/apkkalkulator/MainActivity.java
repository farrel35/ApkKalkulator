package farrel.tugasuas.apkkalkulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apkkalkulator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtAngka1, edtAngka2;
    RadioGroup rdgOperasi;
    Button btnHitung;
    TextView txtHasil;
    RecyclerView recRiwayat;
    static ArrayList<KalkulatorModel> listRiwayat;
    static RiwayatAdapter riwayatAdapter;
    static RiwayatHelper riwayatHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        riwayatHelper = new RiwayatHelper(this);
        listRiwayat = riwayatHelper.getRiwayat();
        edtAngka1 = findViewById(R.id.edtAngka1);
        edtAngka2 = findViewById(R.id.edtAngka2);
        rdgOperasi = findViewById(R.id.rdgOperasi);
        btnHitung = findViewById(R.id.btnHitung);
        txtHasil = findViewById(R.id.txtHasil);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = rdgOperasi.getCheckedRadioButtonId();
                switch (id){
                    case R.id.rbTambah:
                        operasiAritmatika("+");
                        break;
                    case R.id.rbKurang:
                        operasiAritmatika("-");
                        break;
                    case R.id.rbKali:
                        operasiAritmatika("*");
                        break;
                    case R.id.rbBagi:
                        operasiAritmatika("/");
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Pilih operasi", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        recRiwayat = findViewById(R.id.recRiwayat);
        riwayatAdapter = new RiwayatAdapter(listRiwayat);
        recRiwayat.setAdapter(riwayatAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
//        mLayoutManager.setReverseLayout(true);
//        mLayoutManager.setStackFromEnd(true);
        recRiwayat.setLayoutManager(mLayoutManager);
    }

    public void operasiAritmatika(String operasi){
        String angka1 = edtAngka1.getText().toString();
        String angka2 = edtAngka2.getText().toString();
        Double hasil = 0.0;

        if(angka1.isEmpty()){
            edtAngka1.setError("Data tidak boleh kosong");
            edtAngka1.requestFocus();
        }else if (angka2.isEmpty()){
            edtAngka2.setError("Data tidak boleh kosong");
            edtAngka1.requestFocus();
        }else{
            if(operasi == "+"){
                hasil = Double.parseDouble(angka1) + Double.parseDouble(angka2);
            }else if(operasi == "-") {
                hasil = Double.parseDouble(angka1) - Double.parseDouble(angka2);
            }else if(operasi == "*") {
                hasil = Double.parseDouble(angka1) * Double.parseDouble(angka2);
            }else if(operasi == "/") {
                Double bagi = Double.parseDouble(angka1) / Double.parseDouble(angka2);
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                hasil = Double.valueOf(decimalFormat.format(bagi));
                if(Double.isNaN(hasil) || Double.isInfinite(hasil)){
                    hasil = 0.0;
                }

            }
            txtHasil.setText(String.valueOf(hasil));
            listRiwayat.add(new KalkulatorModel(Double.parseDouble(angka1), Double.parseDouble(angka2), hasil, operasi));
            riwayatHelper.saveRiwayat(listRiwayat);
            riwayatAdapter.notifyDataSetChanged();
        }
    }

    public static void delete(int pos){
        listRiwayat.remove(pos);
        riwayatHelper.saveRiwayat(listRiwayat);
        riwayatAdapter.notifyDataSetChanged();
    }

    public static void deleteAll(){
        listRiwayat.clear();
        riwayatHelper.saveRiwayat(listRiwayat);
        riwayatAdapter.notifyDataSetChanged();
    }
}

