package br.vinic.calendarview;

import java.util.Calendar;

public class Dia {
    private Calendar dia;
    private int selecao;

    public Calendar getDia() {
        return dia;
    }

    public void setDia(Calendar dia) {
        this.dia = dia;
    }

    public int getSelecao() {
        return selecao;
    }

    public void setSelecao(int selecao) {
        this.selecao = selecao;
    }

    public Dia(Calendar day) {
        this.dia = day;
    }
}
