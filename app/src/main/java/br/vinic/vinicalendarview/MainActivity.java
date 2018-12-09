package br.vinic.vinicalendarview;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ViNiCalendarView calendar_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null) getSupportActionBar().setElevation(0);

        calendar_view = findViewById(R.id.calendar_view);
        calendar_view.setCurrentDate(Calendar.getInstance());

        /*calendar_view.setCustomBackgroundColor(Color.YELLOW);
        calendar_view.setCustomSelectedDayColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
        calendar_view.setCustomMonthYearColor(Color.BLUE);
        calendar_view.setCustomTextDayColor(Color.GRAY);
        calendar_view.setCustomWeekDayColor(Color.RED);*/

        calendar_view.setOnDaySelectListener(new OnDaySelectListener() {
            @Override
            public void onSelectDay(Dia dia) {
                Toast.makeText(MainActivity.this, "Dia "+dia.getDia().get(Calendar.DAY_OF_MONTH)+" selecionado.", Toast.LENGTH_SHORT).show();
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
