package com.example.photoalbum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private int[] array;
    private String[] names = {"Tiger", "Lion", "Bald Eagle", "Dog", "Dove", "Snake"};

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.txt_view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }

        public TextView getTextView() {
            return textView;
        }
        public ImageView getImageView() {return  imageView;}
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public RecycleViewAdapter(int[] dataSet) {
        this.array = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return (ViewHolder) viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.getTextView().setText(array[position]);
        //viewHolder.getTextView().setCompoundDrawablePadding(array[position]);
        viewHolder.getImageView().setImageResource(array[position]);
        viewHolder.getTextView().setText(names[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return array.length;
    }
}
