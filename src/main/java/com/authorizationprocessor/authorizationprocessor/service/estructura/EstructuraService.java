package com.authorizationprocessor.authorizationprocessor.service.estructura;

import com.authorizationprocessor.authorizationprocessor.domain.estructura.Estructura;
import com.authorizationprocessor.authorizationprocessor.domain.organizacion.Organizacion;
import com.authorizationprocessor.authorizationprocessor.repository.estructura.EstructuraRepository;
import com.authorizationprocessor.authorizationprocessor.utils.UtilBoolean;
import com.authorizationprocessor.authorizationprocessor.utils.exception.AuthorizationServiceException;
import com.authorizationprocessor.authorizationprocessor.utils.messages.UtilMessagesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class EstructuraService {

    private final EstructuraRepository repository;
    public void crearNueva(List<Estructura> estructuras) {
        repository.saveAll(estructuras);
    }

    public EstructuraService(EstructuraRepository repository){
        this.repository = repository;
    }
    public void cambiarNombre(Estructura nuevaEstructuraNombre) {
        Optional<Estructura> estructuraOptional = repository.findById(nuevaEstructuraNombre.getIdentificador());

        if (estructuraOptional.isPresent()) {
            Estructura estructuraExistente = estructuraOptional.get();
            estructuraExistente.setNombre(nuevaEstructuraNombre.getNombre());

            repository.save(estructuraExistente);
        } else {
            throw AuthorizationServiceException.create(UtilMessagesService.ServiceEstructura.ESTRUCTURA_NO_ENCONTRADA_IDENTIFICADOR + nuevaEstructuraNombre.getIdentificador());
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
    public Estructura consultarId(Estructura estructura) {
        return repository.findById(estructura.getIdentificador()).orElse(null);
    }
    public List<Estructura> consultarPorOrganizacion(Organizacion organizacion) {
        return repository.findAllByOrganizacion(organizacion);
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
