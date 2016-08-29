package com.tarot.sdfnash.tarot.registnew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sdfnash on 16/8/19.
 */
public abstract class AbstractAdapter<T>  extends BaseAdapter {


    protected String TAG;
    protected Context mContext;
    protected List<T> list = new ArrayList<T>();



    public AbstractAdapter(Context context) {
        mContext = context;
        TAG = this.getClass().getSimpleName();
    }

    public void setData(List<T> list) {
        this.list.clear();
        addData(list);
    }

    public void setData(T[] array) {
        this.list.clear();
        addData(array);
    }

    public List<T> getData() {
        return list;
    }

    public void addData(List<T> list) {
        if (list != null) {
            this.list.addAll(list);
        }
    }

    public void addData(T[] array) {
        if (array != null) {
            this.list.addAll(Arrays.asList(array));
        }
    }

    public void clearData() {
        this.list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= 0 && position < list.size()) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            int layoutId = getLayoutId();
            if(layoutId > 0) {
                convertView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
            } else {
                convertView = getLayoutView();
            }
        }
        T t = getItem(position);
        convertView.setTag(t);
        initView(position, convertView, t);
        return convertView;
    }

    public abstract int getLayoutId();

    public View getLayoutView(){
        return null;
    }

    public abstract void initView(int position, View convertView, T item);

}
