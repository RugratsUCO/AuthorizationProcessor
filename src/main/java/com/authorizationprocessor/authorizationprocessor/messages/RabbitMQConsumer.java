package com.authorizationprocessor.authorizationprocessor.messages;

import com.authorizationprocessor.authorizationprocessor.domain.estructura.Estructura;
import com.authorizationprocessor.authorizationprocessor.service.estructura.EstructuraService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RabbitMQConsumer {
    @Autowired
    private EstructuraService service;
    @RabbitListener(queues = "crear_estructura_queue")
    public void consumeCreate(Estructura estructura){
        try{
            service.crearNueva(estructura);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @RabbitListener(queues = "consultar_estructura_queue")
    public List<Estructura> consume(){
        List<Estructura> estructuras = new ArrayList<>();
        try{
            estructuras = service.consultar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return estructuras;
    }
    @RabbitListener(queues = "editar_estructura_queue")
    public void consumeChangeStatus(UUID identificador){
        try{
            service.cambiarEstado(identificador);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @RabbitListener(queues = "editar_estructura_queue")
    public void consumeChangeName(UUID identificador, Estructura estructura){
        try{
            service.cambiarNombre(identificador, estructura);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @RabbitListener(queues = "eliminar_estructura_queue")
    public void consumeDelete(Estructura estructura){
        try{
            service.eliminar(estructura);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
