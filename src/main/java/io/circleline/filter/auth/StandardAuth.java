package io.circleline.filter.auth;

import io.circleline.filter.error.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class StandardAuth implements Authentication {

    private String headerName;

    public StandardAuth(String headerName){
        this.headerName = headerName;
    }

    @Override
    public void challenge(HttpServletRequest request) throws UnauthorizedException{
        String accessToken = request.getHeader(headerName);
        if(accessToken == null) throw new UnauthorizedException("not found header:" + headerName);

        // TODO 해당 세션에 대한 AccessToken을 비교한다.
    }
}
