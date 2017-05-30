package com.app.rakez.bottomnav;

/**
 * Created by RAKEZ on 5/29/2017.
 */

public class ChannelItem {
    private int imgId;
    private String channelName;
    private String channelId;

    public ChannelItem(int imgId, String channelName, String channelId) {
        this.imgId = imgId;
        this.channelName = channelName;
        this.channelId = channelId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
