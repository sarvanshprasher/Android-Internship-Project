package com.example.shivam.catchup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by Shivam on 12-Jul-16.
 */
public class Galleryimageadapterw  extends  BaseAdapter{




        private Context mContext;

        private Integer[] mImageIdsS = {
                R.drawable.fullname,
                R.drawable.mail,
                R.drawable.phone,
                R.drawable.home,

        };

        public Galleryimageadapterw(Context context)
        {
            mContext = context;
        }

        public int getCount() {
            return mImageIdsS.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }


        // Override this method according to your need
        public View getView(int index, View view, ViewGroup viewGroup)
        {
            // TODO Auto-generated method stub
            ImageView i = new ImageView(mContext);

            i.setImageResource(mImageIdsS[index]);
            i.setLayoutParams(new Gallery.LayoutParams(100, 100));

            i.setScaleType(ImageView.ScaleType.FIT_XY);

            return i;
        }
    }


