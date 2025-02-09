package com.example.buspass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private Context context;
    private ArrayList<ClassModel> classList;

    public ClassAdapter(Context context, ArrayList<ClassModel> classList) {
        this.context = context;
        this.classList = classList;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.class_item, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassModel classModel = classList.get(position);

        holder.nameTextView.setText("Name: " + classModel.getName());
        holder.genderTextView.setText("Gender: " + classModel.getGender());
        holder.contactTextView.setText("Contact: " + classModel.getContact());
        holder.categoryTextView.setText("Category: " + classModel.getCategory());
        holder.sourceTextView.setText("Source: " + classModel.getSource());
        holder.destinationTextView.setText("Destination: " + classModel.getDestination());
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, genderTextView, contactTextView, categoryTextView, sourceTextView, destinationTextView;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            genderTextView = itemView.findViewById(R.id.genderTextView);
            contactTextView = itemView.findViewById(R.id.contactTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            sourceTextView = itemView.findViewById(R.id.sourceTextView);
            destinationTextView = itemView.findViewById(R.id.destinationTextView);
        }
    }
}
