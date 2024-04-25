package com.authorizationprocessor.authorizationprocessor.repository.estructura;

import com.authorizationprocessor.authorizationprocessor.domain.estructura.Estructura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstructuraRepository extends JpaRepository<Estructura, UUID> {
}
