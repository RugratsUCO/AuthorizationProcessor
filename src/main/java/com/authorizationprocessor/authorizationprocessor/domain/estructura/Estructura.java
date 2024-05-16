package com.authorizationprocessor.authorizationprocessor.domain.estructura;


import com.authorizationprocessor.authorizationprocessor.domain.organizacion.Organizacion;
import com.authorizationprocessor.authorizationprocessor.utils.UtilBoolean;
import com.authorizationprocessor.authorizationprocessor.utils.UtilObject;
import com.authorizationprocessor.authorizationprocessor.utils.UtilText;
import com.authorizationprocessor.authorizationprocessor.utils.UtilUUID;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "Estructura")
public final class Estructura {
    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "identificador", nullable = false)
    private UUID identificador;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organizacion")
    private Organizacion organizacion;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "\"estructuraPadre\"")
    private Estructura estructuraPadre;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "\"tienePadre\"", nullable = false)
    private boolean tienePadre;

    private static final String UUID_PADRE = "";

    private static final Estructura PADRE = new Estructura(UtilUUID.generateUUIDFromString(UUID_PADRE),
            Organizacion.create(), null, UtilText.getDefaultValue(), UtilBoolean.getDefaultValue(),
            UtilBoolean.getDefaultValue());

    public Estructura(final UUID identificador, final Organizacion organizacion,
                      final Estructura estructuraPadre, final String nombre, final boolean estaActivo,
                      boolean tienePadre) {
        super();
        setIdentificador(identificador);
        setOrganizacion(organizacion);
        setEstructuraPadre(estructuraPadre);
        setNombre(nombre);
        setActivo(estaActivo);
        setTienePadre(tienePadre);
    }

    public Estructura() {
        super();
        setIdentificador(UtilUUID.getDefaultValue());
        setOrganizacion(Organizacion.create());
        setEstructuraPadre(PADRE);
        setNombre(UtilText.getDefaultValue());
        setActivo(UtilBoolean.getDefaultValue());
        setTienePadre(UtilBoolean.getDefaultValue());
    }

    public final boolean isTienePadre() {
        return tienePadre;
    }

    public Estructura setTienePadre(boolean tienePadre) {
        this.tienePadre = UtilBoolean.getDefault(tienePadre);
        return this;
    }

    public Estructura setIdentificador(final UUID identificador) {
        this.identificador = UtilUUID.getDefault(identificador);
        return this;
    }

    public Estructura setOrganizacion(final Organizacion organizacion) {
        this.organizacion = UtilObject.getDefault(organizacion, Organizacion.create());
        return this;
    }

    public Estructura setEstructuraPadre(final Estructura estructuraPadre) {
        if (isTienePadre()) {
            this.estructuraPadre = UtilObject.getDefault(estructuraPadre, Estructura.create());
        } else {
            this.estructuraPadre = PADRE;
        }
        return this;
    }

    public Estructura setNombre(final String nombre) {
        this.nombre = UtilText.applyTrim(nombre);
        return this;
    }

    public Estructura setActivo(final boolean estaActivo) {
        this.activo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
        return this;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public Estructura getEstructuraPadre() {
        return estructuraPadre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getActivo() {
        return activo;
    }

    public static Estructura create() {
        return new Estructura();
    }

}

