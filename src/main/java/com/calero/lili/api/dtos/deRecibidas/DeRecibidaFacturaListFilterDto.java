package com.calero.lili.api.dtos.deRecibidas;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@ToString
public class DeRecibidaFacturaListFilterDto {

    private int idRecibida;
    private String claveAcceso;
    private String tipoDocumento;
    private String serie;
    private String secuencia;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private LocalDate fechaEmisionDesde;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private LocalDate fechaEmisionHasta;
    private String numeroIdentificacion;

}