package com.freemanatelier.freemanateliercustomstore.repository;


import com.freemanatelier.freemanateliercustomstore.model.ShoeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends JpaRepository< ShoeEntity, Long> {
}


