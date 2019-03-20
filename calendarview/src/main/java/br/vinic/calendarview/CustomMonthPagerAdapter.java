package br.vinic.calendarview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class CustomMonthPagerAdapter extends PagerAdapter {

    private List<EventDay> eventDays;
    private Context context;
    private DiasAdapter adapter;
    private int customSelectedDayTextColor = -1;
    private int customTextDayColor = -1;
    private OnDaySelectListener onDaySelectListener = null;

    CustomMonthPagerAdapter(Context context, List<EventDay> eventDays) {
        this.context = context;
        this.eventDays = eventDays;
    }

    CustomMonthPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.month_view, container, false);


        RecyclerView recyclerView = layout.findViewById(R.id.recycler_view);
        int numberOfColumns = 7;
        recyclerView.setLayoutManager(new GridLayoutManager(context, numberOfColumns));

        adapter = new DiasAdapter(context, position, customTextDayColor, customSelectedDayTextColor, eventDays);
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
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    void setCustomTextDayColor(int customTextDayColor) {
        this.customTextDayColor = customTextDayColor;
    }

    void setCustomSelectedDayTextColor(int customSelectedDayTextColor) {
        this.customSelectedDayTextColor = customSelectedDayTextColor;
    }


    void setOnDaySelectListener(OnDaySelectListener onDaySelectListener) {
        this.onDaySelectListener = onDaySelectListener;
        if(adapter != null) adapter.setOnDaySelectListener(onDaySelectListener);
    }

    public void addEventDays(List<EventDay> eventDayList) {
        eventDays.addAll(eventDayList);
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

    public void addEventDay(EventDay eventDay) {
        eventDays.add(eventDay);
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }
}
