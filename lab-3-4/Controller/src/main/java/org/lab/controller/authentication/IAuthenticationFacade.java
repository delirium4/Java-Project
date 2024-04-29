package org.lab.controller.authentication;

import org.springframework.security.core.Authentication;
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
