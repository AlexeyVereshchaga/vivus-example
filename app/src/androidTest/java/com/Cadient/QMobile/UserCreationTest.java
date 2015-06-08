package com.Cadient.QMobile;

import com.Cadient.QMobile.model.remote.SessionHandOff;
import com.Cadient.QMobile.model.remote.UserRemote;
import com.Cadient.QMobile.network.request.authentication.CreateUserRequest;
import com.Cadient.QMobile.network.request.authentication.GenerateTokenRequest;
import com.Cadient.QMobile.network.request.authentication.ReadUserRequest;
import com.Cadient.QMobile.network.request.authentication.SearchUserRequest;
import com.Cadient.QMobile.network.request.authentication.UpdateUserRequest;
import com.Cadient.QMobile.network.request.authentication.ValidateTokenRequest;

import java.util.UUID;

/**
 * Created by alexey on 25.08.14.
 */
public class UserCreationTest extends ApplicationTest {

    private String email = "ted.davis1@nutrio.com";
    private Integer internalUserId = 7393058;
    private String externalUserId = "xyzzysdfgsdfgs";

    private Integer internalUserIdRead = 7394633; //don't use for any other test
    private String userEmailRead = "fe1r6i6e@mail.com"; //don't use for any other test

    public void testCreateUser() throws Exception {
        UserRemote userRemote = new UserRemote();
        userRemote.setEmail(email);
        String name = UUID.randomUUID().toString();
        userRemote.setExternalUserId(name);
        CreateUserRequest createUserRequest = new CreateUserRequest(userRemote);

        createUserRequest.setService(service);

        UserRemote userRemoteReturned = createUserRequest.loadDataFromNetwork();

        assertEquals(userRemoteReturned.getEmail(), email);
        assertEquals(userRemoteReturned.getUsername(), name);
    }

    public void testReadUser() throws Exception {
        UserRemote userRemote = new UserRemote(internalUserIdRead);
        ReadUserRequest readUserRequest = new ReadUserRequest(userRemote);
        readUserRequest.setService(service);
        UserRemote userRemoteReturned = readUserRequest.loadDataFromNetwork();

        assertEquals(userEmailRead, userRemoteReturned.getEmail());
        assertEquals(internalUserIdRead, userRemoteReturned.getInternalUserId());
    }

    public void testUpdateUser() throws Exception {
        String email = createRandomEmail();
        UserRemote userRemote = new UserRemote(internalUserId);
        userRemote.setExternalUserId(externalUserId);
        userRemote.setEmail(email);
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(userRemote);
        updateUserRequest.setService(service);
        UserRemote userRemoteReturned = updateUserRequest.loadDataFromNetwork();

        assertEquals(email, userRemoteReturned.getEmail());
        assertEquals(internalUserId, userRemoteReturned.getInternalUserId());
        assertEquals(externalUserId, userRemoteReturned.getExternalUserId());
    }

    public void testSearchUser() throws Exception {
        UserRemote userRemote = new UserRemote();
        userRemote.setExternalUserId(externalUserId);
        SearchUserRequest searchUserRequest = new SearchUserRequest(userRemote);
        searchUserRequest.setService(service);
        UserRemote userRemoteReturned = searchUserRequest.loadDataFromNetwork();
        assertEquals(userRemoteReturned.getInternalUserId(), internalUserId);
    }

    public void testGenerateToken() throws Exception {

        GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest(internalUserId);
        generateTokenRequest.setService(service);

        SessionHandOff sessionHandOffReturned = generateTokenRequest.loadDataFromNetwork();

        assertNotNull(sessionHandOffReturned.getGuid());
        assertEquals(sessionHandOffReturned.getUserId(), internalUserId);
    }

    public void testValidateToken() throws Exception {
        GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest(internalUserId);
        generateTokenRequest.setService(service);
        SessionHandOff sessionHandOffReturned = generateTokenRequest.loadDataFromNetwork();
        String token = sessionHandOffReturned.getGuid();

        SessionHandOff sessionHandOffValidate = new SessionHandOff();
        sessionHandOffValidate.setGuid(token);
        ValidateTokenRequest validateTokenRequest = new ValidateTokenRequest(sessionHandOffValidate);
        validateTokenRequest.setService(service);
        SessionHandOff sessionHandOffReturnedValidate = validateTokenRequest.loadDataFromNetwork();

        assertEquals(sessionHandOffReturnedValidate.getGuid(), token);
    }
}
