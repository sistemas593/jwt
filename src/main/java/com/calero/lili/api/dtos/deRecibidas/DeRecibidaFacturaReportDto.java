package com.calero.lili.api.dtos.deRecibidas;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class DeRecibidaFacturaReportDto {

    //private String idData;
    //private String idEmpresa;
    private UUID idRecibida;
    private String claveAcceso;
    private String tipoDocumento;
    private String serie;
    private String secuencia;
    private LocalDate fechaEmision;
    //private LocalDate fechaAutorizacion;
    //private String autorizacionSri;
    private String numeroIdentificacion;
    private float base_cero;
    private float base_gravada;
    private float base_no_objeto;
    private float base_exenta;
    private float iva;

    //private String mensaje;
    //private String xml;
    private Boolean impresa;
}
