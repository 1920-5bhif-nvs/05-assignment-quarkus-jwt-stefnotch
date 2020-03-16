package at.htl.rest;

import at.htl.model.Person;
import at.htl.service.LoginService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Path("login")
public class LoginResource {
    @Inject
    @RestClient
    LoginService loginService;

    @ConfigProperty(name = "quarkus.oidc.client-id")
    String oidcClientId;

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    String oidcClientSecret;

    @POST
    @NoCache
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @PermitAll
    public Response login(@FormParam("username") String username, @FormParam("password") String password, @FormParam("grant_type") String grantType) {
        String auth = oidcClientId + ":" + oidcClientSecret;
        auth = Base64.getEncoder().encodeToString(auth.getBytes());
        return loginService.login("Basic " + auth, username, password, grantType);
    }
}


