package br.vinic.vinicalendarview;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.vinic.calendarview.Dia;
import br.vinic.calendarview.EventDay;
import br.vinic.calendarview.OnDaySelectListener;
import br.vinic.calendarview.OnMonthChangeListener;
import br.vinic.calendarview.ViNiCalendarView;

public class MainActivity extends AppCompatActivity {

    private ViNiCalendarView calendar_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null) getSupportActionBar().setElevation(0);

        calendar_view = findViewById(R.id.calendar_view);
        calendar_view.setCurrentDate(Calendar.getInstance());
        calendar_view.setCustomBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
        calendar_view.setCustomSelectedDayColor(Color.WHITE);
        calendar_view.setCustomMonthYearColor(Color.WHITE);
        calendar_view.setCustomTextDayColor(Color.WHITE);
        calendar_view.setCustomWeekDayColor(Color.WHITE);
        calendar_view.setBackgroundTransicao(ContextCompat.getDrawable(MainActivity.this, R.drawable.background_transicao));

        List<EventDay> eventDayList = new ArrayList<>();
        eventDayList.add(new EventDay(Calendar.getInstance(), EventDay.IMPORTANT));
        calendar_view.addEventDays(eventDayList);

        calendar_view.setOnDaySelectListener(new OnDaySelectListener() {
            @Override
            public void onSelectDay(Dia dia) { //Apenas um exemplo, não use este código
                Toast.makeText(MainActivity.this, "Dia "+dia.getDia().get(Calendar.DAY_OF_MONTH)+" selecionado.", Toast.LENGTH_SHORT).show();
                List<EventDay> eventDayList = new ArrayList<>();
                eventDayList.add(new EventDay(dia.getDia(), EventDay.VERY_IMPORTANT));
                calendar_view.addEventDays(eventDayList);
            }
        });

        calendar_view.setOnMonthChangeListener(new OnMonthChangeListener() {
            @Override
            public void onMonthChanged(int ano, int mes) {
                Toast.makeText(MainActivity.this, "Mês "+mes+" do ano "+ano+" selecionado.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
