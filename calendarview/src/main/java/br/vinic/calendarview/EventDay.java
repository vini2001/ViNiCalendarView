package br.vinic.calendarview;

import java.util.Calendar;

public class EventDay {
    private int importance = 0;
    public static int LITTLE_IMPORTANT = 1;
    public static int IMPORTANT = 2;
    public static int VERY_IMPORTANT = 3;
    private Calendar dia;

    public Calendar getDia() {
        return dia;
    }

    public void setDia(Calendar dia) {
        this.dia = dia;
    }


    public EventDay(Calendar day) {
        this.dia = day;
    }

    public EventDay(Calendar day, int importance) {
        this.dia = day;
        this.importance = importance;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
