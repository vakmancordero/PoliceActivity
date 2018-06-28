package com.bsdenterprise.qbits.policeactivity.persistence.environment;

import com.bsdenterprise.qbits.policeactivity.model.EnvironmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnvironmentRepository extends JpaRepository<EnvironmentEntity, Long> {

    Optional<EnvironmentEntity> findByName(String name);

}
