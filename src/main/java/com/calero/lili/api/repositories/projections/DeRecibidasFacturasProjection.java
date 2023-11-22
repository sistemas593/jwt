package com.calero.lili.api.repositories.projections;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface DeRecibidasFacturasProjection {

    String getIdData();
    void setIdData(String idData);

    String getIdEmpresa();
    void setIdEmpresa(String idEmpresa);

    UUID getIdRecibida();
    void setIdRecibida(UUID recibida);

    String getClaveAcceso();
    void setClaveAcceso(String claveAcceso);

    String getAmbiente();
    void setAmbiente(String ambiente);

    String getEstadoDocumento();
    void setEstadoDocumento(String estadoDocumento);

    String getTipoEmision();
    void setTipoEmision(String tipoEmision);

    String getTipoDocumento();
    void setTipoDocumento(String tipoDocumento);

    String getSerie();
    void setSerie(String serie);

    String getSecuencia();
    void setSecuencia(String secuencia);

    LocalDate getFechaEmision();
    void setFechaEmision(LocalDate fechaEmision);

    LocalDate getFechaAutorizacion();
    void setFechaAutorizacion(LocalDate fechaAutorizacion);

    String getAutorizacionSri();
    void setAutorizacionSri(String autorizacionSri);

    String getNumeroIdentificacion();
    void setNumeroIdentificacion(String numeroIdentificacion);

    float getBaseCero();
    void setBaseCero(float baseCero);

    float getBaseGravada();
    void setBaseGravada(float baseGravada);

    float getBaseNoObjeto();
    void setBaseNoObjeto(float baseNoObjeto);
    float getBaseExenta();
    void setBaseExenta(float baseExenta);

    float getIva();
    void seIva(float iva);

    String getMensaje();
    void setMensaje(float mensaje);

    String getXml();
    void setXml(String xml);

    Boolean getImpresa();
    void setImpresa(Boolean impresa);


}
