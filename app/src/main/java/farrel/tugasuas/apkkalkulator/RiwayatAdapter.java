package farrel.tugasuas.apkkalkulator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apkkalkulator.R;

import java.util.ArrayList;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {
    public RiwayatAdapter(ArrayList<KalkulatorModel> listRiwayat) {
        this.listRiwayat = listRiwayat;
    }
    private Context context;
    private ArrayList<KalkulatorModel> listRiwayat;
    @NonNull
    @Override
    public RiwayatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.list_riwayat,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatAdapter.ViewHolder holder, int position) {
        KalkulatorModel kalkulatorModel = listRiwayat.get(position);
        holder.txtAngka1.setText(String.valueOf(kalkulatorModel.getAngka1()));
        holder.txtAngka2.setText(String.valueOf(kalkulatorModel.getAngka2()));
        holder.txtOperasi.setText(kalkulatorModel.getOperasi());
        holder.txtHasilriwayat.setText(String.valueOf(kalkulatorModel.getHasil()));
    }

    @Override
    public int getItemCount() {
        return listRiwayat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, PopupMenu.OnMenuItemClickListener {
        TextView txtAngka1, txtAngka2, txtOperasi, txtHasilriwayat;
        ConstraintLayout listRiwayat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAngka1 = itemView.findViewById(R.id.txtAngka1);
            txtAngka2 = itemView.findViewById(R.id.txtAngka2);
            txtOperasi = itemView.findViewById(R.id.txtOperasi);
            txtHasilriwayat = itemView.findViewById(R.id.txtHasilriwayat);
            listRiwayat =itemView.findViewById(R.id.listRiwayat);
            listRiwayat.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            showPopup(view);
            return true;
        }

        private void showPopup(View v){
            PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
            popupMenu.inflate(R.menu.list_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.action_delete:
                    MainActivity.delete(getAdapterPosition());
                    Toast.makeText(context, "Berhasil menghapus riwayat", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_deleteAll:
                    MainActivity.deleteAll();
                    Toast.makeText(context, "Berhasil menghapus semua riwayat", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        }
    }
}
