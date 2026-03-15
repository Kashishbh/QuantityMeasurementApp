package com.feet_measurement_equality.service;

import com.feet_measurement_equality.dto.QuantityDTO;
import com.feet_measurement_equality.entity.QuantityMeasurementEntity;
import com.feet_measurement_equality.model.QuantityModel;
import com.feet_measurement_equality.repository.IQuantityMeasurementRepository;
import com.feet_measurement_equality.unit.LengthUnit;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    private QuantityModel<LengthUnit> createModel(QuantityDTO dto) {

        LengthUnit unit = LengthUnit.valueOf(dto.getUnit().toUpperCase());

        return new QuantityModel<>(dto.getValue(), unit);
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        QuantityModel<LengthUnit> m1 = createModel(q1);
        QuantityModel<LengthUnit> m2 = createModel(q2);

        boolean result = m1.getBaseValue() == m2.getBaseValue();

        repository.save(new QuantityMeasurementEntity(
                q1.getValue(),
                q2.getValue(),
                "COMPARE",
                result ? 1 : 0
        ));

        return result;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        QuantityModel<LengthUnit> m1 = createModel(q1);
        QuantityModel<LengthUnit> m2 = createModel(q2);
        

        double resultBase = m1.getBaseValue() + m2.getBaseValue();

        double result = LengthUnit.valueOf(q1.getUnit()).convertFromBaseUnit(resultBase);

        repository.save(new QuantityMeasurementEntity(
                q1.getValue(),
                q2.getValue(),
                "ADD",
                result
        ));

        return new QuantityDTO(result, q1.getUnit());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        QuantityModel<LengthUnit> m1 = createModel(q1);
        QuantityModel<LengthUnit> m2 = createModel(q2);

        double resultBase = m1.getBaseValue() - m2.getBaseValue();

        double result = LengthUnit.valueOf(q1.getUnit()).convertFromBaseUnit(resultBase);

        repository.save(new QuantityMeasurementEntity(
                q1.getValue(),
                q2.getValue(),
                "SUBTRACT",
                result
        ));
        

        return new QuantityDTO(result, q1.getUnit());
        
    }
    

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        QuantityModel<LengthUnit> m1 = createModel(q1);
        QuantityModel<LengthUnit> m2 = createModel(q2);
        if (m2.getBaseValue() == 0) {
            throw new RuntimeException("Cannot divide by zero");
        }

        double result = m1.getBaseValue() / m2.getBaseValue();

        repository.save(new QuantityMeasurementEntity(
                q1.getValue(),
                q2.getValue(),
                "DIVIDE",
                result
        ));

        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO source, String targetUnit) {

        QuantityModel<LengthUnit> model = createModel(source);

        double baseValue = model.getBaseValue();

        LengthUnit target = LengthUnit.valueOf(targetUnit.toUpperCase());

        double converted = target.convertFromBaseUnit(baseValue);

        repository.save(new QuantityMeasurementEntity(
                source.getValue(),
                0,
                "CONVERT",
                converted
        ));

        return new QuantityDTO(converted, targetUnit);
    }
    
}