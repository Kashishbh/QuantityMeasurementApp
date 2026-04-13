package com.app.user_service.repository;

import com.app.user_service.model.ConversionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoryRepository extends JpaRepository<ConversionHistory, Long> {
    List<ConversionHistory> findByUserId(Long userId);
}