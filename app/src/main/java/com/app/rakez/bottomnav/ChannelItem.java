package com.app.rakez.bottomnav;

/**
 * Created by RAKEZ on 5/29/2017.
 */

public class ChannelItem {
    private String imgId;
    private String channelName;
    private String channelId;

    public ChannelItem(String imgId, String channelName, String channelId) {
        this.imgId = imgId;
        this.channelName = channelName;
        this.channelId = channelId;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
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
