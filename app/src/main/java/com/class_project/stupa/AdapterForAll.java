package com.class_project.stupa;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterForAll extends RecyclerView.Adapter<AdapterForAll.ViewHolder> {

    List<String> place_name;
    List<String> location;
    List<Integer> image;
    LayoutInflater inflater;

    public AdapterForAll(Context context, List<String> place_name, List<String> location, List<Integer> image, RecyclerViewClickListener listener) {
        this.place_name = place_name;
        this.location = location;
        this.image = image;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    RecyclerViewClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView loc;
        ImageView img;

        public ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.item_name);
            loc = view.findViewById(R.id.item_location);
            img = view.findViewById(R.id.item_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForAll.ViewHolder holder, int position) {
        holder.img.setImageResource(image.get(position));
        holder.name.setText(place_name.get(position));
        holder.loc.setText(location.get(position));
    }

    @Override
    public int getItemCount() {
        return place_name.size();
    }
}