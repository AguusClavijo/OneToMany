package com.OneToMany.OneToMany.repositories;

import com.OneToMany.OneToMany.models.Cantante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CantanteRepositories extends CrudRepository<Cantante, Long> {
}
