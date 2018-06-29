package com.bsdenterprise.qbits.policeactivity.persistence.module;

import com.bsdenterprise.qbits.policeactivity.model.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository extends JpaRepository<ModuleEntity, Long> {

    Optional<ModuleEntity> findByName(String name);
    List<ModuleEntity> findByNameContaining(String name);
    Optional<ModuleEntity> findById(Long id);

}
