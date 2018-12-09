package br.vinic.calendarview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class ViNiCalendarView extends FrameLayout {
    private ViewPager viewPager;
    private CustomMonthPagerAdapter pagerAdapter;
    private Calendar dataAtual;
    private TextView txt_mes;
    private LinearLayout lnl_dias_semana, lnl_transicao;

    private TextView[] txtsDiasSemana;
    private OnMonthChangeListener onMonthChangeListener = null;


    public ViNiCalendarView(Context context) {
        super(context);
        init();
    }

    public ViNiCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViNiCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.calendar_view, null);
        addView(view);

        lnl_transicao = view.findViewById(R.id.lnl_transicao);
        lnl_dias_semana = view.findViewById(R.id.lnl_dias_semana);
        txt_mes = view.findViewById(R.id.txt_mes);
        viewPager = view.findViewById(R.id.viewPager);
        pagerAdapter = new CustomMonthPagerAdapter(getContext());
        viewPager.setAdapter(pagerAdapter);
        txtsDiasSemana = new TextView[]{findViewById(R.id.txt_domingo), findViewById(R.id.txt_segunda), findViewById(R.id.txt_terca), findViewById(R.id.txt_quarta), findViewById(R.id.txt_quinta), findViewById(R.id.txt_sexta), findViewById(R.id.txt_sabado)};

        if(dataAtual == null) dataAtual = Calendar.getInstance();
        int ano = dataAtual.get(Calendar.YEAR)*12;
        int mes = dataAtual.get(Calendar.MONTH);
        int mesTot = ano + mes;
        viewPager.setCurrentItem(mesTot);
        txt_mes.setText(getStringMes(mesTot));


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                txt_mes.setText(getStringMes(i));
                int ano = i/12;
                int mes = i%12;
                if(onMonthChangeListener != null) onMonthChangeListener.onMonthChanged(ano, mes);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private String getStringMes(int i) {
        int ano = i/12;
        int mes = i%12;
        String meses[] = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return ano + "\n"+ meses[mes];
    }

    public void setCurrentDate(Calendar dataAtual) {
        this.dataAtual = dataAtual;
    }

    public void setCustomBackgroundColor(int color) {
        txt_mes.setBackgroundColor(color);
        lnl_dias_semana.setBackgroundColor(color);
        viewPager.setBackgroundColor(color);
    }

    public void setCustomSelectedDayColor(int customSelectedDayTextColor) {
        pagerAdapter.setCustomSelectedDayTextColor(customSelectedDayTextColor);
    }

    public void setCustomTextDayColor(int customTextDayColor) {
        pagerAdapter.setCustomTextDayColor(customTextDayColor);
    }

    public void setCustomWeekDayColor(int customWeekDayColor) {
        for(TextView txt : txtsDiasSemana){
            txt.setTextColor(customWeekDayColor);
        }
    }

    public void setCustomMonthYearColor(int customMonthYearColor) {
        txt_mes.setTextColor(customMonthYearColor);
    }

    public void setOnDaySelectListener(OnDaySelectListener onDaySelectListener){
        pagerAdapter.setOnDaySelectListener(onDaySelectListener);
    }

    public void setOnMonthChangeListener(OnMonthChangeListener onMonthChangeListener){
        this.onMonthChangeListener = onMonthChangeListener;
    }

    public void setBackgroundTransicao(Drawable drawable){
        lnl_transicao.setBackground(drawable);
    }

}
