package at.htl.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@RegisterRestClient(configKey = "keycloak-api")
public interface LoginService {
    @POST
    @Path("/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Response login(@HeaderParam("Authorization") String authorization, @FormParam("username") String username, @FormParam("password") String password, @FormParam("grant_type") String grantType);
}
