package com.rahul.mainactivity.repositories;

import androidx.lifecycle.MutableLiveData;
import com.rahul.mainactivity.models.UserProfile;
import java.util.ArrayList;
import java.util.List;

/** Singleton pattern **/

public class UserProfileRepository {
    private static UserProfileRepository instance;
    private ArrayList<UserProfile> dataSet = new ArrayList<>();

    public static UserProfileRepository getInstance(){
        if (instance == null){
            instance = new UserProfileRepository();
        }
        return instance;
    }

    public MutableLiveData<List<UserProfile>> getUserProfiles(){
        setUserProfiles();
        MutableLiveData<List<UserProfile>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setUserProfiles(){
        dataSet.add(new UserProfile("India","https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/1200px-Flag_of_India.svg.png"));
        dataSet.add(new UserProfile("Barack Obama","https://cdn.dribbble.com/users/1322828/screenshots/2904592/obama_icon.png"));
        dataSet.add(new UserProfile("Narendara Modi","https://www.oneindia.com/politicians/image/302x100x402x1/narendra-modi-33275.jpg"));
        dataSet.add(new UserProfile("leonardo-da-vinci.","http://www.leonardodavinci.net/images/leonardo-da-vinci.jpg"));
        dataSet.add(new UserProfile("MS Dhoni","https://english.cdn.zeenews.com/sites/default/files/2019/09/08/817331-dhoni.jpg"));
        dataSet.add(new UserProfile("Priyanka chopra","https://cdn1.thr.com/sites/default/files/imagecache/landscape_928x523/2019/06/gettyimages-1144044827_copy.jpg"));
        dataSet.add(new UserProfile("Sundar pichai","https://pbs.twimg.com/profile_images/864282616597405701/M-FEJMZ0_400x400.jpg"));



    }
}
