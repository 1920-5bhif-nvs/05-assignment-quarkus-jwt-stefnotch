package at.htl.rest;

import at.htl.model.Person;
import at.htl.service.LoginService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("login")
public class LoginResource {
    @Inject
    @RestClient
    LoginService loginService;

    // Working around CORS issues. This is fun. /s
    @POST
    @NoCache
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @PermitAll
    public Response login(@HeaderParam("Authorization") String authorization, @FormParam("username") String username, @FormParam("password") String password, @FormParam("grant_type") String grantType) {
        return loginService.login(authorization, username, password, grantType);
    }
}


