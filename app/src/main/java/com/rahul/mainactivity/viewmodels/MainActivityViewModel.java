package com.rahul.mainactivity.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rahul.mainactivity.models.UserProfile;
import com.rahul.mainactivity.repositories.UserProfileRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<UserProfile>> mUserProfiles;
    private UserProfileRepository userProfileRepository;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();


    public void init(){


        if (mUserProfiles!=null)
        return;

     //   isUpdating.setValue(false);
        userProfileRepository = UserProfileRepository.getInstance();
        mUserProfiles = userProfileRepository.getUserProfiles();
    }

    public MutableLiveData<List<UserProfile>> getUserProfiles(){
        return mUserProfiles;
    }

    public void addNewUserData(UserProfile  userProfile){
        isUpdating.setValue(true);
        List<UserProfile> userProfiles = mUserProfiles.getValue();
        userProfiles.add(userProfile);
        mUserProfiles.postValue(userProfiles);
        isUpdating.setValue(false);

    }

    public LiveData<Boolean> getIsLoading(){
        return isUpdating;
    }

}
