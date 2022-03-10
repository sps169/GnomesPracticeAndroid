package com.example.gnomespractice;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gnomespractice.model.Gnome;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.GnomeHolder> {

    private List<Gnome> gnomeList;

    public RecycleAdapter(List<Gnome> list) {
        gnomeList = list;
    }

    public RecycleAdapter () {
        gnomeList = new ArrayList<>();
    }
    @Override
    public GnomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gnome_item_view, parent, false);
        return new GnomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GnomeHolder holder, int position) {
        holder.getGnomeName().setText(gnomeList.get(position).getName());
        holder.getGnomeSweetId().setText(gnomeList.get(position).getCaramelo_id());
        holder.itemView.setBackgroundColor(Color.parseColor(gnomeList.get(position).getColor().name()));
        holder.detailsButton.setOnClickListener(e -> {
            holder.itemView.animate().setDuration(100).rotationBy(180).start();
        });
    }

    @Override
    public int getItemCount() {
        return gnomeList.size();
    }

    public List<Gnome> getGnomeList() {
        return gnomeList;
    }

    public void setGnomeList(List<Gnome> gnomeList) {
        this.gnomeList = gnomeList;
    }

    public class GnomeHolder extends RecyclerView.ViewHolder {

        private TextView gnomeName;
        private TextView gnomeSweetId;
        private Button detailsButton;

        public GnomeHolder(@NonNull View itemView) {
            super(itemView);
            this.gnomeName = itemView.findViewById(R.id.gnomeName);
            this.gnomeSweetId = itemView.findViewById(R.id.gnomeSweetId);
            this.detailsButton = itemView.findViewById(R.id.do_the_barrer_roll);
        }

        public TextView getGnomeName() {
            return gnomeName;
        }

        public void setGnomeName(TextView gnomeName) {
            this.gnomeName = gnomeName;
        }

        public TextView getGnomeSweetId() {
            return gnomeSweetId;
        }

        public void setGnomeSweetId(TextView gnomeSweetId) {
            this.gnomeSweetId = gnomeSweetId;
        }

        public Button getDetailsButton() {
            return detailsButton;
        }

        public void setDetailsButton(Button detailsButton) {
            this.detailsButton = detailsButton;
        }
    }
}
