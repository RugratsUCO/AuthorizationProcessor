package com.authorizationprocessor.authorizationprocessor.service.estructura;

import com.authorizationprocessor.authorizationprocessor.domain.estructura.Estructura;
import com.authorizationprocessor.authorizationprocessor.repository.estructura.EstructuraRepository;
import com.authorizationprocessor.authorizationprocessor.utils.UtilBoolean;
import com.authorizationprocessor.authorizationprocessor.utils.UtilUUID;
import com.authorizationprocessor.authorizationprocessor.utils.exception.AuthorizationServiceException;
import com.authorizationprocessor.authorizationprocessor.utils.messages.UtilMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class EstructuraService {

    @Autowired
    private EstructuraRepository repository;
    public void crearNueva(Estructura estructura) {
        UUID identificador;
        Optional<Estructura> estructuraOptional;
        do {
            identificador = UtilUUID.generateNewUUID();
            estructuraOptional = repository.findById(identificador);
        } while (estructuraOptional.isPresent());
        estructura.setIdentificador(identificador);

        repository.save(estructura);
    }

    public void cambiarNombre(UUID identificador, Estructura nuevaEstructuraNombre) {
        Optional<Estructura> estructuraOptional = repository.findById(identificador);

        if (estructuraOptional.isPresent()) {
            Estructura estructuraExistente = estructuraOptional.get();
            estructuraExistente.setNombre(nuevaEstructuraNombre.getNombre());

            repository.save(estructuraExistente);
        } else {

            throw AuthorizationServiceException.create(UtilMessagesService.ServiceEstructura.ESTRUCTURA_NO_ENCONTRADA_IDENTIFICADOR + identificador);
        }
    }

    public void cambiarEstado(UUID identificador) {
        Optional<Estructura> estructuraOptional = repository.findById(identificador);

        if (estructuraOptional.isPresent()) {
            Estructura estructuraExistente = estructuraOptional.get();
            estructuraExistente.setActivo(UtilBoolean.getOpposite(estructuraExistente.getActivo()));

            repository.save(estructuraExistente);
        } else {

            throw AuthorizationServiceException.create(UtilMessagesService.ServiceEstructura.ESTRUCTURA_NO_ENCONTRADA_IDENTIFICADOR + identificador);
        }
    }

    public List<Estructura> consultar() {
        return repository.findAll();
    }

    public void eliminar(Estructura estructura) {
        Optional<Estructura> estructuraOptional = repository.findById(estructura.getIdentificador());

        if (estructuraOptional.isPresent()) {
            repository.delete(estructura);
        } else {
            throw AuthorizationServiceException.create(UtilMessagesService.ServiceEstructura.ESTRUCTURA_NO_ENCONTRADA);
        }
    }
}
