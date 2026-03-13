package com.feet_measurement_equality.repository;

import com.feet_measurement_equality.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementRepository {
    void save(QuantityMeasurementEntity entity);
    List<QuantityMeasurementEntity> getAllMeasurements();
}