package org.lab.externalInterfacesMicroservice.authentication;

import org.springframework.security.core.Authentication;
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
