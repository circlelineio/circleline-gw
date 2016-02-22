package io.circleline.filter.auth;

import io.circleline.filter.error.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * Created by 1002515 on 2016. 2. 22..
 */
public class BasicAuth implements Authentication {

    private static final String HEADER_NAME = "Authentication";
    private static final String AUTH_SCHEME = "Basic";

    @Override
    public void challenge(HttpServletRequest request) throws UnauthorizedException {

        String credentials = request.getHeader(HEADER_NAME);
        if(credentials == null) throw new UnauthorizedException("'Authentication' header must not be null");

        String[] basicCredentials = credentials.split(" ");
        if(basicCredentials.length != 2){
            throw new UnauthorizedException("invalid 'Authentication' header value");
        }

        if(!AUTH_SCHEME.equals(basicCredentials[0])){
            throw new UnauthorizedException("authorization method must be 'Basic'");
        }

        String[] userPassword = getUserPassword(basicCredentials[1]);
        String userId = userPassword[0];
        String password = userPassword[1];
        //TODO check if valid user with username and password
    }

    private String[] getUserPassword(String basicCredentials){
        String userPassword = new String(Base64.getDecoder().decode(basicCredentials));
        int index = userPassword.lastIndexOf(":");

        return new String[]{
                userPassword.substring(0,index),
                userPassword.substring(index)};
    }




}
