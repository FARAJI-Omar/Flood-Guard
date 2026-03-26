package com.floodguard.repository;

import com.floodguard.entity.FloodEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloodEventRepository extends JpaRepository<FloodEvent, Long> {

        @Query(value = "SELECT * FROM flood_events WHERE " +
                        "(:year IS NULL OR EXTRACT(YEAR FROM event_date) = :year) AND " +
                        "(:severity IS NULL OR severity = :severity)", nativeQuery = true)
        List<FloodEvent> findByFilters(@Param("year") Integer year,
                        @Param("severity") String severity);
}
