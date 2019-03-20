package br.vinic.calendarview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiasAdapter extends RecyclerView.Adapter<DiasAdapter.ViewHolder> {

    private final List<EventDay> eventDays;
    private LayoutInflater mInflater;
    private Context context;
    private ArrayList<Dia> mData = new ArrayList<>();
    private int customSelectedDayTextColor, customTextDayColor;

    private int diaAtual;
    private OnDaySelectListener onDaySelectListener = null;

    DiasAdapter(Context context, int positionViewPager, int customTextDayColor, int customSelectedDayTextColor, List<EventDay> eventDays) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.eventDays = eventDays;

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
        if(calendarHj.get(Calendar.YEAR) == ano && calendarHj.get(Calendar.MONTH) == mes){
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
            if(dia == diaAtual) diaa.setSelecao(1);
            mData.add(diaa);
        }
        this.customTextDayColor = customTextDayColor;
        this.customSelectedDayTextColor = customSelectedDayTextColor;
        this.diaAtual = diaAtual;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_dia_mes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Dia dia = mData.get(position);
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

        final int positionFinal = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dia.getSelecao() != -1){
                    for(Dia d : mData){
                        if(d.getSelecao() == 1) d.setSelecao(0);
                    }

                    mData.get(positionFinal).setSelecao(1);
                    notifyDataSetChanged();
                    if(onDaySelectListener != null) onDaySelectListener.onSelectDay(mData.get(positionFinal));
                }
            }
        });

        if(holder.lnl_drawable.getBackground() != null){
            holder.lnl_drawable.setBackground(null);
        }

        if(dia.getSelecao() != -1){
            for(int i = 0; i < eventDays.size(); i++){
                Calendar cEv = eventDays.get(i).getDia();
                Calendar cThis = dia.getDia();

                if(cEv.get(Calendar.DAY_OF_MONTH) == cThis.get(Calendar.DAY_OF_MONTH) && cEv.get(Calendar.MONTH) == cThis.get(Calendar.MONTH) && cEv.get(Calendar.YEAR) == cThis.get(Calendar.YEAR)){
                    holder.lnl_drawable.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
                    switch (eventDays.get(i).getImportance()){
                        case 1:  holder.lnl_drawable.getBackground().setColorFilter(ViNiCalendarView.CIRCLE1_COLOR, PorterDuff.Mode.SRC_IN); break;
                        case 2:  holder.lnl_drawable.getBackground().setColorFilter(ViNiCalendarView.CIRCLE2_COLOR, PorterDuff.Mode.SRC_IN); break;
                        case 3:  holder.lnl_drawable.getBackground().setColorFilter(ViNiCalendarView.CIRCLE3_COLOR, PorterDuff.Mode.SRC_IN); break;
                    }
                    break;
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    void setOnDaySelectListener(OnDaySelectListener onDaySelectListener) {
        this.onDaySelectListener = onDaySelectListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_dia;
        LinearLayout lnl_drawable;

        ViewHolder(View itemView) {
            super(itemView);
            txt_dia = itemView.findViewById(R.id.txt_dia);
            lnl_drawable = itemView.findViewById(R.id.lnl_drawable);
        }
    }


    private int getQtdDiasMes(int mes, int ano) {
        if(ano % 4 == 0 && mes == 1) return 29; //fevereiro no ano bissexto
        int qtd[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return qtd[mes];
    }


}

