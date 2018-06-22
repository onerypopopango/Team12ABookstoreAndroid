package com.example.henry.getbooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BooksListAdapter
    extends RecyclerView.Adapter<BooksListAdapter.BooksListViewHolder>{

    private LayoutInflater mInflater;

    List<Book> mBooksList;

    class BooksListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titleTextView;
        private ImageView imageView;

        public BooksListViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.textView_rv_title);
            imageView = itemView.findViewById(R.id.imageView_rv);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            final Context context = v.getContext();

            Intent intent = new Intent(context, BookDetails.class);

            ((Activity) context).startActivity(intent);

        }
    }

    BooksListAdapter(Context context, List<Book> mBooksList){
        mInflater = LayoutInflater.from(context);
        this.mBooksList = mBooksList;
    }

    @NonNull
    @Override
    public BooksListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_book, parent, false);
        return new BooksListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksListViewHolder holder, int position) {

        Book current = mBooksList.get(position);
        holder.titleTextView.setText(current.getTitle());
        holder.imageView.setImageResource(R.drawable.test);

    }

    @Override
    public int getItemCount() {
        if (mBooksList != null)
            return mBooksList.size();
        else return 0;
    }




}
