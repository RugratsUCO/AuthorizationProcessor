package com.authorizationprocessor.authorizationprocessor.messages;

import com.authorizationprocessor.authorizationprocessor.domain.estructura.Estructura;
import com.authorizationprocessor.authorizationprocessor.domain.organizacion.Organizacion;
import com.authorizationprocessor.authorizationprocessor.dto.EstructuraDTO;
import com.authorizationprocessor.authorizationprocessor.service.estructura.EstructuraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RabbitMQConsumer {
    private final EstructuraService service;
    private final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);
    public RabbitMQConsumer(EstructuraService service){
        this.service = service;
    }

    @RabbitListener(queues = "crear_estructura_queue")
    public HttpStatus crearNueva(List<Estructura> estructuras){
        try{
            service.crearNueva(estructuras);
            return HttpStatus.OK;
        }catch (Exception e){
            log.error(e.getMessage());
            return HttpStatus.CONFLICT;
        }
    }

    @RabbitListener(queues = "consultar_estructura_queue")
    public EstructuraDTO consultarPorId(Estructura estructura){
        Estructura estructuraConsultar;
        try{
            estructuraConsultar = service.consultarId(estructura);
            if(estructuraConsultar != null){
                return EstructuraDTO.builder().estructura(estructuraConsultar).estado(HttpStatus.ACCEPTED).build();
            }else {
                return EstructuraDTO.builder().estructura(Estructura.create()).estado(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return EstructuraDTO.builder().estructura(estructura).estado(HttpStatus.BAD_REQUEST).build();
        }
    }
    @RabbitListener(queues = "consultar_estructuras_organizacion_queue")
    public List<Estructura> consultarPorOrganizacion(Organizacion organizacion){
        try{
            return service.consultarPorOrganizacion(organizacion);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @RabbitListener(queues = "consultar_estructuras_queue")
    public List<Estructura> consultarTodas(Estructura estructura){
        List<Estructura> estructurasConsultar = new ArrayList<>();
        try{
            estructurasConsultar = service.consultar();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return estructurasConsultar;
    }
    @RabbitListener(queues = "cambiar_estado_queue")
    public HttpStatus cambiarEstado(UUID identificador){
        try{
            service.cambiarEstado(identificador);
            return HttpStatus.OK;
        }catch (Exception e){
            log.error(e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    @RabbitListener(queues = "cambiar_nombre_queue")
    public HttpStatus cambiarNombre(Estructura estructura){
        try{
            service.cambiarNombre(estructura);
            return HttpStatus.OK;
        }catch (Exception e){
            log.error(e.getMessage());
            return HttpStatus.CONFLICT;
        }
    }
    @RabbitListener(queues = "eliminar_estructura_queue")
    public HttpStatus eliminar(Estructura estructura){
        try{
            service.eliminar(estructura);
            return HttpStatus.OK;
        }catch (Exception e){
            log.error(e.getMessage());
            return HttpStatus.CONFLICT;
        }
    }
}
