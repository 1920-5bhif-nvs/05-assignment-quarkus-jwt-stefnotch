package at.htl.rest;

import at.htl.model.Person;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public Response getAll() {
        Random random = new Random(253245L);

        // Returns a few random people. It always uses the same seed, so the people will always be the same
        List<Person> people = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            people.add(Person.randomPerson(random));
        }
        return Response.ok().build();
    }
}
