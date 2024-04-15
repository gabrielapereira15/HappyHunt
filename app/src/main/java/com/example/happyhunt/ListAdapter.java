package com.example.happyhunt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happyhunt.databinding.PlaceItemLayoutBinding;
import com.google.android.libraries.places.api.model.Place;

import java.util.List;
import java.util.Vector;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Place> dataList;
    PlaceItemLayoutBinding placeItemBinding;

    public ListAdapter(List<Place> placesList, Context context) {
        super();
        this.dataList = placesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        placeItemBinding = PlaceItemLayoutBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(placeItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder). bindView(dataList.get(position));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        PlaceItemLayoutBinding recyclerRowBinding;
        public ViewHolder(PlaceItemLayoutBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }

        public void bindView(Place placeData) {
            recyclerRowBinding.txtPlaceDRating.setText(String.valueOf(placeData.getRating()));
            recyclerRowBinding.txtPlaceName.setText(placeData.getName());
            recyclerRowBinding.txtPlaceAddress.setText(placeData.getAddress());
        }
    }
}