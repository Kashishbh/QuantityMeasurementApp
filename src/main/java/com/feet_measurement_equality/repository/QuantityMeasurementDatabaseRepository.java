package com.feet_measurement_equality.repository;

import com.feet_measurement_equality.entity.QuantityMeasurementEntity;
import com.feet_measurement_equality.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String query = "INSERT INTO quantity_measurement (operand1, operand2, operation, result) VALUES (?, ?, ?, ?)";

        try {

            Connection conn = ConnectionUtil.getConnection();

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setDouble(1, entity.getOperand1());
            ps.setDouble(2, entity.getOperand2());
            ps.setString(3, entity.getOperation());
            ps.setDouble(4, entity.getResult());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        String query = "SELECT * FROM quantity_measurement";

        try {

            Connection conn = ConnectionUtil.getConnection();

            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                QuantityMeasurementEntity entity =
                        new QuantityMeasurementEntity(
                                rs.getDouble("operand1"),
                                rs.getDouble("operand2"),
                                rs.getString("operation"),
                                rs.getDouble("result")
                        );

                list.add(entity);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}