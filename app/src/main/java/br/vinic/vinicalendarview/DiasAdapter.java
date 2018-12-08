package br.vinic.vinicalendarview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DiasAdapter extends RecyclerView.Adapter<DiasAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private ArrayList<Dia> mData = new ArrayList<>();

    public DiasAdapter(Context context, int primeiroDiaMes, int qtdDiaMes) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        for (int i = 1; i < primeiroDiaMes + qtdDiaMes; i++) {
            int dia = i >= primeiroDiaMes ? (i + 1) - primeiroDiaMes : -1;
            Dia diaa = new Dia(dia);
            mData.add(diaa);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_dia_mes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dia dia = mData.get(position);
        holder.txt_dia.setText(dia.getDia() != -1 ? String.valueOf(dia.getDia()) : "");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_dia;


        ViewHolder(View itemView) {
            super(itemView);
            txt_dia = itemView.findViewById(R.id.txt_dia);
        }


    }


}

