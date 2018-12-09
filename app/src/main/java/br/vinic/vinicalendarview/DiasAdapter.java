package br.vinic.vinicalendarview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class DiasAdapter extends RecyclerView.Adapter<DiasAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private ArrayList<Dia> mData = new ArrayList<>();
    private int customSelectedDayTextColor, customTextDayColor;
    private int customBackgroundColor;

    private int diaAtual;
    private OnDaySelectListener onDaySelectListener;

    public DiasAdapter(Context context, int positionViewPager, int customTextDayColor, int customSelectedDayTextColor, int customBackgroundColor) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;



        int ano = positionViewPager/12;
        int mes = positionViewPager%12;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int primeiroDiaMes = calendar.get(Calendar.DAY_OF_WEEK);
        int qtdDiaMes = getQtdDiasMes(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

        int diaAtual = 0;
        Calendar calendarHj = Calendar.getInstance();
        if(calendarHj.get(Calendar.YEAR) == ano && calendar.get(Calendar.MONTH) == mes){
            diaAtual = calendarHj.get(Calendar.DAY_OF_MONTH);
        }


        for (int i = 1; i < primeiroDiaMes + qtdDiaMes; i++) {
            int dia = -1;
            if(i >= primeiroDiaMes) {
                dia = (i + 1) - primeiroDiaMes;
            }

            Calendar data = Calendar.getInstance();
            data.set(Calendar.DAY_OF_MONTH, dia);
            data.set(Calendar.YEAR, ano);
            data.set(Calendar.MONTH, mes);
            Dia diaa = new Dia(data);
            if(dia == -1) diaa.setSelecao(-1);
            mData.add(diaa);
        }
        this.customTextDayColor = customTextDayColor;
        this.customSelectedDayTextColor = customSelectedDayTextColor;
        this.customBackgroundColor = customBackgroundColor;

        this.diaAtual = diaAtual;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_dia_mes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Dia dia = mData.get(position);
        holder.txt_dia.setText(dia.getSelecao() != -1 ? String.valueOf(dia.getDia().get(Calendar.DAY_OF_MONTH)) : "");

        if(dia.getDia().get(Calendar.DAY_OF_MONTH) == diaAtual){
            holder.txt_dia.setTypeface(holder.txt_dia.getTypeface(), Typeface.BOLD);
        }

        if(dia.getSelecao() == 1){
            holder.txt_dia.setBackground(ContextCompat.getDrawable(context, R.drawable.borda_dia));
            holder.txt_dia.setTextColor(customSelectedDayTextColor);
        }else{
            holder.txt_dia.setBackgroundColor(Color.TRANSPARENT);
            holder.txt_dia.setTextColor(customTextDayColor);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Dia d : mData){
                    if(d.getSelecao() == 1) d.setSelecao(0);
                }

                mData.get(position).setSelecao(1);
                notifyDataSetChanged();
                onDaySelectListener.onSelectDay(mData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnDaySelectListener(OnDaySelectListener onDaySelectListener) {
        this.onDaySelectListener = onDaySelectListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_dia;

        ViewHolder(View itemView) {
            super(itemView);
            txt_dia = itemView.findViewById(R.id.txt_dia);
        }
    }


    private int getQtdDiasMes(int mes, int ano) {
        if(ano % 4 == 0 && mes == 1) return 29; //fevereiro no ano bissexto
        int qtd[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return qtd[mes];
    }


}

