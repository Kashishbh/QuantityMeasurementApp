package com.feet_measurement_equality;

import com.feet_measurement_equality.controller.QuantityMeasurementController;
import com.feet_measurement_equality.dto.QuantityDTO;
import com.feet_measurement_equality.repository.QuantityMeasurementDatabaseRepository;
import com.feet_measurement_equality.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

    	QuantityMeasurementDatabaseRepository repo = new QuantityMeasurementDatabaseRepository();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(1, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCH");

        controller.performComparison(q1, q2);
        controller.performAddition(q1, q2);
        controller.performConversion(q1, "INCH");
        controller.performSubtraction(q1, q2);
        controller.performDivision(q1, q2);

        System.out.println("Operations executed successfully.");
    }
}