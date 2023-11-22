package com.calero.lili.api.controllers;


import com.calero.lili.api.dtos.deRecibidas.DeRecibidasCreationResponseDto;
import com.calero.lili.api.dtos.deRecibidas.DeRecibidaFacturaListFilterDto;
import com.calero.lili.api.services.DeRecibidasServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1.0/recibidos")
@CrossOrigin(originPatterns = "*")

public class DeRecibidasController {

    private final DeRecibidasServiceImpl deRecibidasService;

    @DeleteMapping("facturas/{idData}/{idEmpresa}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        String idData = "ABCD";
        String idEmpresa = "AB01";
        deRecibidasService.delete(idData, idEmpresa, id);
    }

    @GetMapping("facturas/{idData}/{idEmpresa}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> findAll(
            @PathVariable("idData") String idData,
            @PathVariable("idEmpresa") String idEmpresa,
            DeRecibidaFacturaListFilterDto filters,
            Pageable pageable) {
        if (filters.getFechaEmisionDesde() != null && filters.getFechaEmisionHasta() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Debe ingresar la fecha hasta"));
        } else if (filters.getFechaEmisionDesde() == null && filters.getFechaEmisionHasta() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Debe ingresar la fecha desde"));
        } else if (filters.getFechaEmisionDesde() != null) {
            if (filters.getFechaEmisionDesde().isAfter(filters.getFechaEmisionHasta())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "La fecha desde debe ser menor a la fecha hasta"));
            }
        }
        return ResponseEntity.ok(deRecibidasService.findAll(idData, idEmpresa, filters, pageable));
    }

    @GetMapping("facturas/{idData}/{idEmpresa}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeRecibidasCreationResponseDto findById(@PathVariable("idData") String idData,
                                                   @PathVariable("idEmpresa") String idEmpresa,
                                                   @PathVariable("id") UUID id) {
        return deRecibidasService.findFirstByUuid(idData, idEmpresa, id);
    }

}
