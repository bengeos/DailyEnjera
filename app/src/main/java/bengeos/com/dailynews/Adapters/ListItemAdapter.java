package bengeos.com.dailynews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import bengeos.com.dailynews.DetailView;
import bengeos.com.dailynews.R;

/**
 * Created by bengeos on 1/25/17.
 */

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.DataObjectHolder> {
    private static List<Album> myItems;
    private static Context myContext;

    public ListItemAdapter(List<Album> myItems, Context myContext) {
        this.myItems = myItems;
        if(myItems != null){
            Collections.reverse(myItems);
        }
        this.myContext = myContext;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.Title.setText(myItems.get(position).getTitle());
        holder.Content.setText(myItems.get(position).getContent());
        holder.AlbumImage.setImageResource(myItems.get(position).getImageID());
//        Glide.with(myContext)
//                .load(myItems.get(position).getImageURL())
//                .into(holder.AlbumImage);
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView Title, Content, PubDate;
        private ImageView AlbumImage,Overflow;


        public DataObjectHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.txt_album_title);
            Content = (TextView) itemView.findViewById(R.id.txt_album_content);
            AlbumImage = (ImageView) itemView.findViewById(R.id.img_album_image);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(myContext, DetailView.class);
            Bundle b = new Bundle();
            b.putString("ImageID",String.valueOf(myItems.get(getAdapterPosition()).getImageID()));
            b.putString("Title",String.valueOf(myItems.get(getAdapterPosition()).getTitle()));
            b.putString("Content",String.valueOf(myItems.get(getAdapterPosition()).getContent()));
            intent.putExtras(b);
            myContext.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {

            return true;
        }
    }
    private static void showPopupMenu(View view, final Album album) {
        // inflate menu
        PopupMenu popup = new PopupMenu(myContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.pop_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.action_settings){

                }
                return false;
            }
        });
        popup.show();
    }
}
