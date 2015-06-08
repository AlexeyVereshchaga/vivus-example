package com.Cadient.QMobile.network.request.user;

import com.Cadient.QMobile.model.remote.UserProfile;
import com.Cadient.QMobile.network.request.AbstractNutrioApiRequest;

/**
 * Created by ivan on 8/21/14.
 */
public class UpdateUserProfileRequest extends AbstractNutrioApiRequest<UserProfile> {

    public UpdateUserProfileRequest(UserProfile profile) {
        super(UserProfile.class, profile);
    }

    @Override
    public UserProfile loadDataFromNetwork() throws Exception {
        return getService().updateUserProfile(entity.getId(), entity);
    }
}
