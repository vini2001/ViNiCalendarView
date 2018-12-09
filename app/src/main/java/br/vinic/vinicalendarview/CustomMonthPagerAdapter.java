package br.vinic.vinicalendarview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

class CustomMonthPagerAdapter extends PagerAdapter {

    private Context context;
    private ViNiCalendarView viNiCalendarView;
    private DiasAdapter adapter;
    private int customSelectedDayTextColor = -1;
    private int customTextDayColor = -1;
    private int customBackgroundColor;
    private OnDaySelectListener onDaySelectListener = null;

    public CustomMonthPagerAdapter(Context context, ViNiCalendarView viNiCalendarView) {
        this.context = context;
        this.viNiCalendarView = viNiCalendarView;
        customBackgroundColor = ContextCompat.getColor(context, R.color.colorPrimary);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.month_view, container, false);


        RecyclerView recyclerView = layout.findViewById(R.id.recycler_view);
        int numberOfColumns = 7;
        recyclerView.setLayoutManager(new GridLayoutManager(context, numberOfColumns));



        adapter = new DiasAdapter(context, position, customTextDayColor, customSelectedDayTextColor, customBackgroundColor);
        recyclerView.setAdapter(adapter);

        if(onDaySelectListener != null) adapter.setOnDaySelectListener(onDaySelectListener);


        container.addView(layout);
        return layout;
    }


    @Override
    public int getCount() {
        return 10000 * 12; //vai até o ano 9999
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    public void setCustomTextDayColor(int customTextDayColor) {
        this.customTextDayColor = customTextDayColor;
    }

    public void setCustomSelectedDayTextColor(int customSelectedDayTextColor) {
        this.customSelectedDayTextColor = customSelectedDayTextColor;
    }

    public void setCustomBackgroundColor(int customBackgroundColor){
        this.customBackgroundColor = customBackgroundColor;
    }

    public void setOnDaySelectListener(OnDaySelectListener onDaySelectListener) {
        this.onDaySelectListener = onDaySelectListener;
        if(adapter != null) adapter.setOnDaySelectListener(onDaySelectListener);
    }
}
