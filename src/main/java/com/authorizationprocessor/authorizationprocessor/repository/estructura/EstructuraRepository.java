package com.authorizationprocessor.authorizationprocessor.repository.estructura;

import com.authorizationprocessor.authorizationprocessor.domain.estructura.Estructura;
import com.authorizationprocessor.authorizationprocessor.domain.organizacion.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EstructuraRepository extends JpaRepository<Estructura, UUID> {
    List<Estructura> findAllByOrganizacion(Organizacion organizacion);
}
