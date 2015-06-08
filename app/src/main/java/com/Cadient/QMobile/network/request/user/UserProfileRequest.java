package com.Cadient.QMobile.network.request.user;

import com.Cadient.QMobile.model.remote.UserProfile;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by Alexey Vereshchaga on 29.10.14.
 */
public class UserProfileRequest extends AbstractNutrioApiRequest<UserProfile> {
    public UserProfileRequest() {
        super(UserProfile.class);
    }

    @Override
    public UserProfile loadDataFromNetwork() throws Exception {
        return getService().readUserProfile();
    }
}
