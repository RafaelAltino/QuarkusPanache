package br.com.rafael.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.rafael.domain.repository.PostRepository;
import br.com.rafael.domain.repository.UserRepository;
import br.com.rafael.dto.CreatePostRequest;
import br.com.rafael.dto.PostResponse;
import br.com.rafael.model.Post;
import br.com.rafael.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private UserRepository userRepository;
    private PostRepository repository;
    
    @Inject
    public PostResource(UserRepository userRepository, PostRepository repository){
        this.userRepository = userRepository;
        this.repository = repository;
    }
    
    @POST
    @Transactional
    public Response savePost( 
        @PathParam("userId") Long userId, CreatePostRequest request ){  

        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(request.getText());
        post.setUser(user);
        repository.persist(post);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPosts( @PathParam("userId") Long userId ){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Aqui eu poderia passar uma query SQL: "SELECT * FROM POSTS WHERE user_id IS 1"
        // No caso de 2 parâmetros: primeiro = coluna na tabela, segundo = valor que tem que ser igual
        // No caso de 3 parâmetros: o segundo é ordenação da lista
        PanacheQuery<Post> query = repository.find(
            "user",                                       //Primeiro parâmetro
            Sort.by("dateTime", Direction.Descending),   //Segundo parâmetro
            user);                                              //Terceiro parâmetro
        List<Post> list = query.list();
        var postResponseList = list.stream()
        .map( post -> PostResponse.fromEntity(post))
//        .map(PostResponse::fromEntity)        // Como só passa 1 parametro e so recebe 1
        .collect(Collectors.toList());
        
        return Response.ok(postResponseList).build();
    }

}
