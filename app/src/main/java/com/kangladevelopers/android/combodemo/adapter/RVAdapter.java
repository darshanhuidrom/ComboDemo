package com.kangladevelopers.android.combodemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kangladevelopers.android.combodemo.R;

import java.util.List;

/**
 * Created by DARSHAN HUIDROJM on 7/11/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private RecyclerListener recyclerListener;
    private List<String> strings;
    private Context context;

    public RVAdapter(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.text_view, null, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String string = strings.get(position);
        holder.textView.setText(string);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public void notifyDataSetChanged(List<String> strings) {
        if(strings==null&& strings.isEmpty()){
            return;
        }
        this.strings.addAll(strings);
        notifyDataSetChanged();
    }

    public void setRecyclerListener(RecyclerListener recyclerListener) {
        this.recyclerListener = recyclerListener;
    }

   public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (recyclerListener != null) {
                recyclerListener.onRecyclerClick(getLayoutPosition(), view);
            }
        }
    }

    /////////////////////////interface

    public interface RecyclerListener {
        void onRecyclerClick(int position, View view);
    }

}
