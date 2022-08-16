package com.hava.dvtweatherapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.hava.dvtweatherapp.ui.holders.ScreenItem;

import java.util.List;

import dvtweatherapp.R;

public class OnBoardingAdapter extends PagerAdapter {

        public OnBoardingAdapter(Context mContext, List<ScreenItem> screenItemList) {
            this.mContext = mContext;
            this.screenItemList = screenItemList;
        }

        Context mContext;
        List<ScreenItem> screenItemList;
        @Override
        public int getCount() {
            return  screenItemList.size();

        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layoutScreen = inflater.inflate(R
                    .layout.pager_elemnt, null);
            ImageView imageView = layoutScreen.findViewById(R.id.imageIntro);
            TextView title = layoutScreen.findViewById(R.id.title_intro);
          //  TextView heading = layoutScreen.findViewById(R.id.heading_intro);
            TextView desc = layoutScreen.findViewById(R.id.into_desc);
            title.setText(screenItemList.get(position).getTitle());

          //  title.setVisibility(screenItemList.get(position).getTitleVisibility());

         //   heading.setText(screenItemList.get(position).getHeading());
            desc.setText(screenItemList.get(position).getDescription());
            imageView.setImageResource(screenItemList.get(position).getScreenImage());
            container.addView(layoutScreen);
            return layoutScreen;
        }
    }


