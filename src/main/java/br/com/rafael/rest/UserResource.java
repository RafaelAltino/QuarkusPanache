package br.com.rafael.rest;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.rafael.dto.CreateUserRequest;
import br.com.rafael.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    
    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest){
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());
        user.persist();
        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers(){
        PanacheQuery<User> query = User.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id){ // Esse parametro do PathParam é o que vc está recebendo da URL, o nome deve se o mesmo
        User user = User.findById(id);
        if(user != null){
            user.delete();
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
        
        
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData){
        User user = User.findById(id);
        if(user != null){
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
