package com.floodguard.repository;

import com.floodguard.entity.AlertThreshold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertThresholdRepository extends JpaRepository<AlertThreshold, Long> {
}
