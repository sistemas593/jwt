package com.calero.lili.api.services;

import com.calero.lili.api.dtos.deRecibidas.*;
import com.calero.lili.api.dtos.deRecibidas.DeRecibidaRetencionListFilterDto;
import com.calero.lili.api.dtos.deRecibidas.DeRecibidaRetencionReportDto;
import com.calero.lili.api.errors.exceptions.AlreadyExistsException;
import com.calero.lili.api.errors.exceptions.NotFoundException;
import com.calero.lili.api.errors.exceptions.ReadOnlyException;
import com.calero.lili.api.repositories.entities.*;
import com.calero.lili.api.repositories.projections.DeRecibidasFacturasProjection;
import com.calero.lili.api.repositories.DeRecibidasFacturasRepository;
import com.calero.lili.api.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class DeRecibidasServiceImpl implements DeRecibidasService {

    private final DeRecibidasFacturasRepository deRecibidasFacturasRepository ;
    private static final Logger logger = LoggerFactory.getLogger(DeRecibidasServiceImpl.class);

    public void delete(String idData, String idEmpresa, UUID id) {
        Optional<DeRecibidasFacturasEntity> existing = deRecibidasFacturasRepository.findById(id);
        if  (!existing.isPresent()) {
            throw new AlreadyExistsException(format("id number {0} no exists", id ));
        }
        deRecibidasFacturasRepository.findById(id).ifPresent(deRecibidasFacturasRepository::delete);
    }

    public Page<DeRecibidaFacturaReportDto> findAll(
            String idData, String idEmpresa, DeRecibidaFacturaListFilterDto filters, Pageable pageable) {
        Specification<DeRecibidasFacturasEntity> spec =
                Specification.where(idDataSpec(idData)).and(idEmpresaSpec(idEmpresa));

        if (filters.getFechaEmisionDesde() != null && filters.getFechaEmisionHasta() != null) {
            spec =
                    spec.and(
                            fechaEmisionDesdeAndHastaSpec(
                                    filters.getFechaEmisionDesde(), filters.getFechaEmisionHasta()));
        }

        if (filters.getTipoDocumento() != null) {
            spec = spec.and(tipoDocumentoSpec(filters.getTipoDocumento()));
        }

        if (filters.getNumeroIdentificacion() != null) {
            spec = spec.and(numeroIdentificacionSpec(filters.getNumeroIdentificacion()));
        }

        if (filters.getSerie() != null) {
            spec = spec.and(serieSpec(filters.getSerie()));
        }

        if (filters.getSecuencia() != null) {
            spec = spec.and(secuenciaSpec(filters.getSecuencia()));
        }

        Page<DeRecibidasFacturasEntity> page = deRecibidasFacturasRepository.findAll(spec, pageable);
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::projectionFacturasToDtoReport)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements());
    }

    private Specification<DeRecibidasFacturasEntity> idDataSpec(String idData) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("idData"), idData);
    }

    private Specification<DeRecibidasFacturasEntity> idEmpresaSpec(String idEmpresa) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("idEmpresa"), idEmpresa);
    }

    private Specification<DeRecibidasFacturasEntity> tipoDocumentoSpec(String tipoDocumento) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoDocumento"), tipoDocumento);
    }

    private Specification<DeRecibidasFacturasEntity> numeroIdentificacionSpec(
            String numeroIdentificacion) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("numeroIdentificacion"), numeroIdentificacion);
    }

    private Specification<DeRecibidasFacturasEntity> serieSpec(String serie) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("serie"), serie);
    }

    private Specification<DeRecibidasFacturasEntity> secuenciaSpec(String secuencia) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("secuencia"), secuencia);
    }

    private Specification<DeRecibidasFacturasEntity> fechaEmisionDesdeAndHastaSpec(
            LocalDate fechaEmisionDesde, LocalDate fechaEmisionHasta) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("fechaEmision"), fechaEmisionDesde, fechaEmisionHasta);
    }

    public DeRecibidasCreationResponseDto findFirstByUuid(String idData, String idEmpresa, UUID id) {
        DeRecibidasFacturasEntity entidad = deRecibidasFacturasRepository.findById(id).orElseThrow(() -> new NotFoundException(format("Id {0} no exists",  id )));
        if (!entidad.getIdData().equals(idData) || !entidad.getIdEmpresa().equals(idEmpresa)){
            throw new ReadOnlyException(format("Data: {0} / Id {1} no exists para la data", idData, id));
        }
        return toDto(entidad);
    }

    private DeRecibidaFacturaReportDto projectionFacturasToDtoReport(
            DeRecibidasFacturasEntity projection) {
        DeRecibidaFacturaReportDto dto = new DeRecibidaFacturaReportDto();
        dto.setIdRecibida(projection.getIdRecibida());
        dto.setClaveAcceso(projection.getClaveAcceso());
        dto.setTipoDocumento(projection.getTipoDocumento());
        dto.setSerie(projection.getSerie());
        dto.setSecuencia(projection.getSecuencia());
        dto.setFechaEmision(projection.getFechaEmision());
        dto.setNumeroIdentificacion(projection.getNumeroIdentificacion());
        dto.setBase_cero(projection.getBaseCero() != null ? projection.getBaseCero().floatValue() : 0);
        dto.setBase_gravada(
                projection.getBaseGravada() != null ? projection.getBaseGravada().floatValue() : 0);
        dto.setBase_no_objeto(
                projection.getBaseNoObjeto() != null ? projection.getBaseNoObjeto().floatValue() : 0);
        dto.setBase_exenta(
                projection.getBaseExenta() != null ? projection.getBaseExenta().floatValue() : 0);
        dto.setIva(projection.getIva() != null ? projection.getIva().floatValue() : 0);
        dto.setImpresa(projection.getImpresa());
        return dto;
    }

    private DeRecibidaFacturaReportDto projectionFacturasToDtoReport(DeRecibidasFacturasProjection projection) {
        DeRecibidaFacturaReportDto dto = new DeRecibidaFacturaReportDto();
        //dto.setIdData(projection.getIdData());
        //dto.setIdEmpresa(projection.getIdEmpresa());
        dto.setIdRecibida(projection.getIdRecibida());
        dto.setClaveAcceso(projection.getClaveAcceso());
        dto.setTipoDocumento(projection.getTipoDocumento());
        dto.setSerie(projection.getSerie());
        dto.setSecuencia(projection.getSecuencia());
        dto.setFechaEmision(projection.getFechaEmision());
        dto.setNumeroIdentificacion(projection.getNumeroIdentificacion());

        dto.setBase_cero(projection.getBaseCero());

        dto.setBase_gravada(projection.getBaseGravada());
        dto.setBase_no_objeto(projection.getBaseNoObjeto());
        dto.setBase_exenta(projection.getBaseExenta());
        dto.setIva(projection.getIva());


        dto.setImpresa(projection.getImpresa());

        return dto;
    }
    private DeRecibidasCreationResponseDto toDto(DeRecibidasFacturasEntity entity) {
        DeRecibidasCreationResponseDto dto = new DeRecibidasCreationResponseDto();
        dto.setClaveAcceso(entity.getClaveAcceso());
        return dto;
    }
}
