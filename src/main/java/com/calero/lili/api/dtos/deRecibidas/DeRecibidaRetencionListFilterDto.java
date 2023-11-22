package com.calero.lili.api.dtos.deRecibidas;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@ToString
public class DeRecibidaRetencionListFilterDto {

    //private String idData;
    //private int idEmpresa;
    private int idRecibida;
    private String claveAcceso;
    private String tipoDocumento;
    private String serie;
    private String secuencia;
    private Timestamp fechaEmision;
    private String numeroIdentificacion;

}
