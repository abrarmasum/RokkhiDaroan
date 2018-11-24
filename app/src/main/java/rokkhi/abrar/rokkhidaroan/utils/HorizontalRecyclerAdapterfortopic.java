package rokkhi.abrar.rokkhidaroan.utils;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


import rokkhi.abrar.rokkhidaroan.R;

import static android.content.ContentValues.TAG;


public class HorizontalRecyclerAdapterfortopic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnButtonClickListener{
        void onButtonClick(String data);
    }
    OnButtonClickListener onButtonClickListener;

    private ArrayList<String> mList;
    private Context mContext;

    public HorizontalRecyclerAdapterfortopic(ArrayList<String> list, Context context, OnButtonClickListener click) {
        this.mList = list;
        mContext=context;
        onButtonClickListener=click;
    }

    private class CellViewHolder extends RecyclerView.ViewHolder  {
        private Button button;

        public CellViewHolder(View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.button);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            default: {
                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_list_item_fortopic, viewGroup, false);
                return new CellViewHolder(v1);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;
        final String data=mList.get(position);

        cellViewHolder.button.setText(mList.get(position));
      //  if(position!=)


        cellViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onBindViewHolder: hiholo1" + data);
                passData(data);

            }
        });


    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }


    private void passData(String data){


        try{
            onButtonClickListener.onButtonClick(data);
        }catch (NullPointerException e){
            Log.e(TAG, "loadMoreData: ClassCastException: " +e.getMessage() );
        }
    }




}