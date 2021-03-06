package com.example.phase1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GuestGridAdapter extends ArrayAdapter<GuestModel> {
    public GuestGridAdapter(@NonNull Context context, ArrayList<GuestModel> guestModelArrayList) {
        super(context, 0, guestModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.guest_item, parent, false);
        }
        GuestModel guestModel = getItem(position);
        TextView guestFirstNameTxt = listitemView.findViewById(R.id.guest_name);
        ImageView guestAvatar = listitemView.findViewById(R.id.guest_avatar);
        guestFirstNameTxt.setText(guestModel.getFirst_name()+" "+guestModel.getLast_name());

//        new DownloadImageTask(guestAvatar).execute(guestModel.getAvatar());
        Picasso.get().load(guestModel.getAvatar()).into(guestAvatar);
        return listitemView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

//    public void clear() {
//        items.clear();
//        notifyDataSetChanged();
//    }
//
//    // Add a list of items -- change to type used
//    public void addAll(List<Tweet> list) {
//        items.addAll(list);
//        notifyDataSetChanged();
//    }
}
