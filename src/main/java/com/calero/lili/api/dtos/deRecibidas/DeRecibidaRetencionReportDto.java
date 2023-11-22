package com.calero.lili.api.dtos.deRecibidas;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class DeRecibidaRetencionReportDto {

    //private String idData;
   // private String idEmpresa;
    private UUID idRecibida;
    private String claveAcceso;
    private String tipoDocumento;
    private String serie;
    private String secuencia;
    private LocalDate fechaEmision;
    private String numeroIdentificacion;
    private float retencionRenta;
    private float retencionIva;
    private Boolean impresa;

}
