package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long> {
}
