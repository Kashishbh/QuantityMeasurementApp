package com.app.quantitymeasurement.repository;
import java.util.*;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Integer> {
	List<QuantityMeasurementEntity> findByOperation(String operation);

    long countByOperation(String operation);
}
