package br.com.rafael.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.rafael.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
    
    
}