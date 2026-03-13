package com.feet_measurement_equality;

import com.feet_measurement_equality.controller.QuantityMeasurementController;
import com.feet_measurement_equality.repository.QuantityMeasurementCacheRepository;
import com.feet_measurement_equality.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        QuantityMeasurementCacheRepository repository=QuantityMeasurementCacheRepository.getInstance();
        QuantityMeasurementServiceImpl service=new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller=new QuantityMeasurementController(service);
        System.out.println("Application Started");
    }
}