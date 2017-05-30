package com.app.rakez.bottomnav;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prof.youtubeparser.models.Main;

import java.util.List;

/**
 * Created by RAKEZ on 5/29/2017.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.MyViewHolder> {

    private Context mContext;
    private List<ChannelItem> channelList;
    private FragmentActivity activity;


    public ChannelAdapter(Context mContext, List<ChannelItem> channelList, FragmentActivity activity) {
        this.mContext = mContext;
        this.channelList = channelList;
        this.activity = activity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ChannelItem channel = channelList.get(position);
        holder.channelName.setText(channel.getChannelName());
        holder.imgId.setImageResource(channel.getImgId());

        holder.imgId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //holder.getAdapterPosition();
                //Bundle info = new Bundle();
                //String state = albumList.get(holder.getAdapterPosition()).getState();
                //String tableNo = albumList.get(holder.getAdapterPosition()).getName();

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, PlayListFragment.newInstance());
                transaction.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView channelName;
        public ImageButton imgId;
        public MyViewHolder(View view) {
            super(view);
            channelName = (TextView) view.findViewById(R.id.textView);
            imgId = (ImageButton) view.findViewById(R.id.imageButton);
        }
    }
}
