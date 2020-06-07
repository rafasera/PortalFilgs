package com.rafaelfilgueiras.portalanimes.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.rafaelfilgueiras.portalanimes.R;
import com.rafaelfilgueiras.portalanimes.activity.PostActivity;
import com.rafaelfilgueiras.portalanimes.model.FilgsAPI;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<FilgsAPI> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<FilgsAPI> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Requisiçao para o Glide option
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_item, viewGroup, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        // definindo clique para o container do LynearLayout
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent postIntent = new Intent(mContext, PostActivity.class);
                postIntent.putExtra("post_name", mData.get(viewHolder.getAdapterPosition()).getTitle()); // nome da postagem
                postIntent.putExtra("post_data", mData.get(viewHolder.getAdapterPosition()).getRating()); // data da postagem
                postIntent.putExtra("post_Content", mData.get(viewHolder.getAdapterPosition()).getContent()); // conteudo do post
                //postIntent.putExtra("post_name", mData.get(viewHolder.getAdapterPosition()).getTitle());

                mContext.startActivity(postIntent);

            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.txtName.setText(mData.get(i).getTitle());
        myViewHolder.txtStudio.setText(mData.get(i).getExcerpt());
        myViewHolder.txtRating.setText(mData.get(i).getRating());
        //myViewHolder.txtCategory.setText(mData.get(i).getCategorie());

        // Carregar a imagem da internet dentro do imageview

        //Glide.with(mContext).load(mData.get(i).getImg_url()).apply(option).into(myViewHolder.imgThumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtRating;
        TextView txtStudio;
        //TextView txtCategory;
        //ImageView imgThumbnail;
        LinearLayout view_container;


        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            txtName = itemView.findViewById(R.id.post_name);
            //txtCategory = itemView.findViewById(R.id.categoria);
            txtRating = itemView.findViewById(R.id.tv_data);
            txtStudio = itemView.findViewById(R.id.tv_mid_description);
            //imgThumbnail = itemView.findViewById(R.id.thumbnail);

        }


    }

}
