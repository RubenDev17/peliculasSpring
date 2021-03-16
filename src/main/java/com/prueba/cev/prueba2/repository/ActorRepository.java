package com.prueba.cev.prueba2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.cev.prueba2.domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

}
