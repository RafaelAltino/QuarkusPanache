package br.com.rafael.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.rafael.model.Post;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post>{  
}
