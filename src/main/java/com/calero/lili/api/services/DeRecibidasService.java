package com.calero.lili.api.services;

import com.calero.lili.api.dtos.deRecibidas.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DeRecibidasService {

    void delete(String idData, String idEmpresa, UUID id);
    DeRecibidasCreationResponseDto findFirstByUuid(String idData, String idEmpresa, UUID id);

    Page<DeRecibidaFacturaReportDto> findAll(String idData, String idEmpresa, DeRecibidaFacturaListFilterDto filters, Pageable pageable);
}
