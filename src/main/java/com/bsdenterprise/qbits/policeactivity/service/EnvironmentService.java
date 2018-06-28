package com.bsdenterprise.qbits.policeactivity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsdenterprise.qbits.policeactivity.common.exceptions.ValidationException;
import com.bsdenterprise.qbits.policeactivity.common.service.BaseService;

import com.bsdenterprise.qbits.policeactivity.dto.environment.EnvironmentInDTO;
import com.bsdenterprise.qbits.policeactivity.dto.environment.EnvironmentOutDTO;

import com.bsdenterprise.qbits.policeactivity.model.EnvironmentEntity;
import com.bsdenterprise.qbits.policeactivity.persistence.environment.EnvironmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnvironmentService extends BaseService<EnvironmentRepository, EnvironmentEntity> {

    public EnvironmentOutDTO createEnvironment(EnvironmentInDTO environmentInDTO) throws Exception {

        if (this.repository.findByName(environmentInDTO.getName()).isPresent()) {
            throw new ValidationException("Environment already exists");
        } else {
            return this.save(environmentInDTO, EnvironmentOutDTO.class);
        }

    }

    public EnvironmentOutDTO updateEnvironment(long environmentId, EnvironmentInDTO environmentInDTO) throws Exception {
        return this.update(environmentId, environmentInDTO, EnvironmentOutDTO.class);
    }

}
