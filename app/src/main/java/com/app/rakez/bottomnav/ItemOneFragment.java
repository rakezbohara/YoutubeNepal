/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.app.rakez.bottomnav;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

public class ItemOneFragment extends Fragment {

    RecyclerView channelRV;
    ArrayList<ChannelItem> channelList=new ArrayList<>();
    ChannelAdapter channelAdapter;
    List<String> id = new ArrayList<>();
    List<String> channelIcon = new ArrayList<>();
    List<String> channelName = new ArrayList<>();
    List<String> channelId = new ArrayList<>();

    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        channelRV = (RecyclerView) getActivity().findViewById(R.id.home_rv);
        makeChannelRequest();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        channelRV.setLayoutManager(mLayoutManager);
        channelRV.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        channelRV.setItemAnimator(new DefaultItemAnimator());
        channelAdapter = new ChannelAdapter(getActivity().getApplicationContext(),channelList,getActivity());
        channelRV.setAdapter(channelAdapter);


    }
    private void makeChannelRequest(){
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading");
        pDialog.show();




        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, "http://192.168.1.10/nepalyt/channellistJson.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                channelId.clear();
                channelName.clear();
                channelIcon.clear();
                id.clear();
                Log.d("size of the","Sizw is rakjsdifns response "+response.length());
                for(int i = 0;i<response.length();i++){
                    try {
                        JSONObject table = (JSONObject) response.get(i);
                        id.add(table.getString("id"));
                        channelIcon.add(table.getString("icon_src"));
                        channelName.add(table.getString("channel_name"));
                        channelId.add(table.getString("channel_id"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                pDialog.hide();
                pDialog.dismiss();
                prepareData();
                //swipeRefreshLayout.setRefreshing(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                pDialog.dismiss();
                /*swipeRefreshLayout.setRefreshing(false);
                View view = findViewById(R.id.drawer_layout);
                sb = Snackbar.make(view, "Cannot connect to network", Snackbar.LENGTH_INDEFINITE);
                sb.setAction("Action", null);
                sb.show();*/

            }
        });
        AppController.getInstance().addToRequestQueue(req);


    }

    public void prepareData(){

        for(int i = 0 ; i < id.size() ; i++){
            channelList.add(new ChannelItem(channelIcon.get(i),channelName.get(i),channelId.get(i)));
        }
        channelAdapter.notifyDataSetChanged();

    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
