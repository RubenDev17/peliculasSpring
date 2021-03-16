package com.prueba.cev.prueba2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.cev.prueba2.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
