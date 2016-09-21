package com.example.simas.fourthexercise;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by simas on 8/16/2016.
 */
public class FragmentLayout extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnClickListener(this);
        ImageView fragmentImage = (ImageView) getActivity().findViewById(R.id.fragment_image);
        String string = getArguments().getString("photo_url", "");
        Picasso.with(getActivity()).load(string).into(fragmentImage);

    }

    public FragmentLayout newInstance(String photo_url) {
        FragmentLayout fragmentLayout = new FragmentLayout();
        Bundle args = new Bundle();
        args.putString("photo_url", photo_url);
        fragmentLayout.setArguments(args);
        return fragmentLayout;
    }

    @Override
    public void onClick(View view) {
        getFragmentManager().popBackStack();
    }
}
