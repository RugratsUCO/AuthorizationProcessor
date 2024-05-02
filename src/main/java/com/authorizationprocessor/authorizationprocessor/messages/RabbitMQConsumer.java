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
    public void crearNueva(Estructura estructura){
        try{
            service.crearNueva(estructura);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @RabbitListener(queues = "consultar_estructura_queue")
    public Estructura consultarPorId(Estructura estructura){
        Estructura estructuraConsultar = Estructura.create();
        try{
            estructuraConsultar = service.consultarId(estructura);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return estructuraConsultar;
    }
    @RabbitListener(queues = "consultar_estructuras_queue")
    public List<Estructura> consultarTodas(Estructura estructura){
        List<Estructura> estructurasConsultar = new ArrayList<>();
        try{
            estructurasConsultar = service.consultar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return estructurasConsultar;
    }
    @RabbitListener(queues = "cambiar_estado_queue")
    public void cambiarEstado(UUID identificador){
        try{
            service.cambiarEstado(identificador);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @RabbitListener(queues = "cambiar_nombre_queue")
    public void cambiarNombre(Estructura estructura){
        try{
            service.cambiarNombre(estructura);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @RabbitListener(queues = "eliminar_estructura_queue")
    public void eliminar(Estructura estructura){
        try{
            service.eliminar(estructura);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
