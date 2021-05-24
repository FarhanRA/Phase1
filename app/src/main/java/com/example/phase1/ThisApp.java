package com.example.phase1;

import android.app.Application;

import java.util.ArrayList;

public class ThisApp extends Application {
    ArrayList<GuestModel> guestModels = new ArrayList<>();

    public ArrayList<GuestModel> getGuestModels() {
        return guestModels;
    }

    public void setGuestModels(ArrayList<GuestModel> guestModels) {
        this.guestModels = guestModels;
    }
}
