package com.authorizationprocessor.authorizationprocessor.domain.organizacion;

import com.authorizationprocessor.authorizationprocessor.utils.UtilBoolean;
import com.authorizationprocessor.authorizationprocessor.utils.UtilObject;
import com.authorizationprocessor.authorizationprocessor.utils.UtilText;
import com.authorizationprocessor.authorizationprocessor.utils.UtilUUID;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Organizacion")
public final class Organizacion {
    @Id
    @Column(name = "identificador",nullable = false)
    private UUID identificador;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;

    @Column(name = "tipo",nullable = false)
    private String tipo;

    @Column(name = "activo",nullable = false)
    private boolean activo;


    public Organizacion() {
        setIdentificador(UtilUUID.getDefaultValue());
        setNombre(UtilText.getDefaultValue());
        setDescripcion(UtilText.getDefaultValue());
        setTipo(UtilText.getDefaultValue());
        setActivo(UtilBoolean.getDefaultValue());
    }

    public Organizacion(final UUID identificador, final String nombre, final String descripcion,
                        final String tipo, final boolean estaActivo) {
        setIdentificador(identificador);
        setNombre(nombre);
        setDescripcion(descripcion);
        setTipo(tipo);
        setActivo(estaActivo);
    }

    public Organizacion setIdentificador(final UUID identificador) {
        this.identificador = UtilUUID.getDefault(identificador);
        return this;
    }

    public Organizacion setNombre(final String nombre) {
        this.nombre = UtilText.applyTrim(nombre);
        return this;
    }

    public Organizacion setDescripcion(final String descripcion) {
        this.descripcion = UtilText.applyTrim(descripcion);
        return this;
    }

    public Organizacion setTipo(final String tipo) {
        this.tipo = tipo;
        return this;
    }

    public Organizacion setActivo(final boolean estaActivo) {
        this.activo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
        return this;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean getActivo() {
        return activo;
    }

    public static Organizacion create() {
        return new Organizacion();
    }
}
