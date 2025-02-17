package com.Farmacia.ProyectoDesarrollo.repository;

import com.Farmacia.ProyectoDesarrollo.entity.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {
}
