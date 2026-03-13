package com.feet_measurement_equality.service;

import com.feet_measurement_equality.dto.QuantityDTO;
import com.feet_measurement_equality.entity.QuantityMeasurementEntity;
import com.feet_measurement_equality.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {
    private IQuantityMeasurementRepository repository;
    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository=repository;
    }
    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        double result=q1.getValue()+q2.getValue();
        repository.save(
                new QuantityMeasurementEntity(q1.getValue(),q2.getValue(),"ADD",result));
        return new QuantityDTO(result,q1.getUnit(),q1.getMeasurementType());
    }
    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        double result=q1.getValue()-q2.getValue();
        repository.save( new QuantityMeasurementEntity(q1.getValue(),q2.getValue(),"SUBTRACT",result));
        return new QuantityDTO(result,q1.getUnit(),q1.getMeasurementType());
    }
    @Override
    public boolean compare(QuantityDTO q1,QuantityDTO q2) {
        return q1.getValue()==q2.getValue();
    }
    @Override
    public double divide(QuantityDTO q1,QuantityDTO q2){
        return q1.getValue()/q2.getValue();
    }
    @Override
    public QuantityDTO convert(QuantityDTO source, String targetUnit) {

        return new QuantityDTO(
                source.getValue(),
                targetUnit,
                source.getMeasurementType()
        );
    }
}