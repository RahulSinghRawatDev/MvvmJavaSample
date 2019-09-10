package com.rahul.mainactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rahul.mainactivity.R;
import com.rahul.mainactivity.models.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<UserProfile> mUserProfiles;
    private Context mContext;

    public RecyclerAdapter(Context context, List<UserProfile> mUserProfiles) {
        this.mContext = context;
        this.mUserProfiles = mUserProfiles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitems, parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.profilename.setText(mUserProfiles.get(position).getTitle());
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext).setDefaultRequestOptions(defaultOptions).load(mUserProfiles.get(position).getImageUrl()).into(holder.profilepic);
    }

    @Override
    public int getItemCount() {
        return mUserProfiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView profilepic;
        TextView profilename;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profilepic = itemView.findViewById(R.id.profilepic);
            profilename = itemView.findViewById(R.id.profilename);
        }
    }
}
