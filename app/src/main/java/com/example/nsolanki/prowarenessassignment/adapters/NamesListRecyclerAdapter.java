package com.example.nsolanki.prowarenessassignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nsolanki.prowarenessassignment.R;
import com.example.nsolanki.prowarenessassignment.interfaces.DeleteButtonListener;
import com.example.nsolanki.prowarenessassignment.model.ContactDetailsModel;

import java.util.List;

/**
 * Created by nsolanki on 7/10/2017.
 */

public class NamesListRecyclerAdapter extends RecyclerView.Adapter<NamesListRecyclerAdapter.NameListViewHolder> {

    private Context context;
    private List<ContactDetailsModel> detailsModels;
    DeleteButtonListener deleteButtonListener;

    public NamesListRecyclerAdapter(Context context, List<ContactDetailsModel> detailsModels, DeleteButtonListener deleteButtonListener) {
        this.context = context;
        this.detailsModels = detailsModels;
        this.deleteButtonListener = deleteButtonListener;
    }

    @Override
    public NameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.contact_row, parent, false);
        return new NameListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NameListViewHolder holder, int position) {
        holder.setPosition(position);
        holder.myTextView.setText(this.detailsModels.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return detailsModels.size();
    }

    public class NameListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView myTextView;
        public Button deleteButton;
        public int mPosition;

        public NameListViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.nameTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);
        }

        public void setPosition(int position) {
            this.mPosition = position;
        }

        public int getmPosition() {
            return mPosition;
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.deleteButton) {
                deleteButtonListener.onItemClickedForDelete(getmPosition());
            }
        }
    }
}
