package io.circleline.filter.auth;

import io.circleline.filter.error.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 1002515 on 2016. 2. 22..
 */
public interface Authentication {
    public void challenge(HttpServletRequest request) throws UnauthorizedException;
}
