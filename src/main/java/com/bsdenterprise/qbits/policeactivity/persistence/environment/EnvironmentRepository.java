package com.bsdenterprise.qbits.policeactivity.persistence.environment;

import com.bsdenterprise.qbits.policeactivity.model.EnvironmentEntity;
import com.bsdenterprise.qbits.policeactivity.model.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnvironmentRepository extends JpaRepository<EnvironmentEntity, Long> {

    Optional<ModuleEntity> findByName(String name);

}
