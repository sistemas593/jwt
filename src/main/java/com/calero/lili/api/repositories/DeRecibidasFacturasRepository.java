package com.calero.lili.api.repositories;

import com.calero.lili.api.repositories.entities.DeRecibidasFacturasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeRecibidasFacturasRepository extends JpaRepository<DeRecibidasFacturasEntity, UUID>, JpaSpecificationExecutor<DeRecibidasFacturasEntity> {
/*
    @Query(
            value = "SELECT entity.idData as idData," +
                    "entity.idEmpresa as idEmpresa," +
                    "entity.idRecibida as idRecibida," +
                    "entity.claveAcceso as claveAcceso," +
                    "entity.tipoDocumento as tipoDocumento," +
                    "entity.serie as serie," +
                    "entity.secuencia as secuencia," +
                    "entity.fechaEmision as fechaEmision," +
                    "entity.numeroIdentificacion as numeroIdentificacion," +
                    "entity.baseCero as baseCero," +
                    "entity.baseGravada as baseGravada," +
                    "entity.baseNoObjeto as baseNoObjeto," +
                    "entity.baseExenta as baseExenta," +
                    "entity.iva as iva,"+
                    "entity.impresa as impresa "+
                    "FROM DeRecibidasFacturasEntity entity "+
                    "WHERE ( entity.idData = :idData) AND "+
                    "(:idEmpresa IS NULL OR entity.idEmpresa = :idEmpresa) AND "+
                    "(:tipoDocumento IS NULL OR entity.tipoDocumento = :tipoDocumento) AND "+
                    "(:numeroIdentificacion IS NULL OR entity.numeroIdentificacion LIKE '%' || :numeroIdentificacion || '%' ) AND "+
                    "(:serie IS NULL OR entity.serie LIKE '%' || :serie || '%' ) AND "+
                    "(:secuencia IS NULL OR entity.secuencia LIKE '%' || :secuencia || '%' ) "
                    ,
            countQuery = "SELECT COUNT(1) "+
                    "FROM DeRecibidasFacturasEntity entity "+
                    "WHERE ( entity.idData = :idData) AND "+
                    "(:idEmpresa IS NULL OR entity.idEmpresa = :idEmpresa) AND "+
                    "(:tipoDocumento IS NULL OR entity.tipoDocumento = :tipoDocumento) AND "+
                    "(:numeroIdentificacion IS NULL OR entity.numeroIdentificacion LIKE '%' || :numeroIdentificacion || '%' ) AND "+
                    "(:serie IS NULL OR entity.serie LIKE '%' || :serie || '%' ) AND "+
                    "(:secuencia IS NULL OR entity.secuencia LIKE '%' || :secuencia || '%' ) "

    )
    Page<DeRecibidasFacturasProjection> findAll(String idData, String idEmpresa, String tipoDocumento, String numeroIdentificacion, String serie, String secuencia, Pageable pageable);

 */
}
