package com.calero.lili.api.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "de_recibidas_facturas")
public class DeRecibidasFacturasEntity {
    @Column(name = "id_data")
    private String idData;
    @Column(name = "id_empresa")
    private String idEmpresa;

    @Id
    @Column(name = "id_recibida")
    private UUID idRecibida;

    @Column(name = "clave_acceso")
    private String claveAcceso;

    //@Column(name = "ambiente")
    //private String ambiente;
    //@Column(name = "estado_documento")
    //private String estadoDocumento;
    //@Column(name = "tipo_emision")
    //private String tipoEmision;

    @NotNull
    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "serie")
    private String serie;
    @Column(name = "secuencia")
    private String secuencia;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @Column(name = "baseCero")
    private BigDecimal baseCero;

    @Column(name = "base_gravada")
    private BigDecimal baseGravada;

    @Column(name = "base_no_objeto")
    private BigDecimal baseNoObjeto;

    @Column(name = "base_exenta")
    private BigDecimal baseExenta;

    @Column(name = "iva")
    private BigDecimal iva;

    @Column(name = "mensaje")
    @JsonIgnore
    private String mensaje;

    @Column(name = "xml")
    @JsonIgnore
    private String xml;

    @Column(name = "impresa")
    @JsonIgnore
    private Boolean impresa;

}
