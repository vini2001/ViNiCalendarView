package br.vinic.vinicalendarview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class ViNiCalendarView extends FrameLayout {
    private RecyclerView recyclerView;
    private int primeiroDiaMes, qtdDiaMes, primeiroDiaSemanaMes;
    private DiasAdapter adapter;

    public ViNiCalendarView(Context context) {
        super(context);
        init(null, 0);
    }

    public ViNiCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ViNiCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        View view = inflate(getContext(), R.layout.calendar_view, null);
        addView(view);

        recyclerView = view.findViewById(R.id.recycler_view);
        int numberOfColumns = 7;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));

        primeiroDiaMes = 7;
        qtdDiaMes = 31;
        adapter = new DiasAdapter(getContext(), primeiroDiaMes, qtdDiaMes);
        recyclerView.setAdapter(adapter);
    }
}
