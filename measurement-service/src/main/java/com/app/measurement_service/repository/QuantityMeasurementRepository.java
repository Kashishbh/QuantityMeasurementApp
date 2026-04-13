package com.app.measurement_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.measurement_service.model.OperationType;
import com.app.measurement_service.model.QuantityMeasurementEntity;

public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperation(OperationType operation);

    long countByOperation(OperationType operation);
}