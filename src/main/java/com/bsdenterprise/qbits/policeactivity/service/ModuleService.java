package com.bsdenterprise.qbits.policeactivity.service;

import com.bsdenterprise.qbits.policeactivity.common.exceptions.ValidationException;
import com.bsdenterprise.qbits.policeactivity.common.service.BaseService;
import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleInDTO;
import com.bsdenterprise.qbits.policeactivity.dto.module.ModuleOutDTO;
import com.bsdenterprise.qbits.policeactivity.model.ModuleEntity;
import com.bsdenterprise.qbits.policeactivity.persistence.module.ModuleRepository;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ModuleService extends BaseService<ModuleRepository, ModuleEntity> {

    public List<ModuleOutDTO> findByQueryContaining(String query) {

        List<ModuleOutDTO> moduleList = new ArrayList<>();

        if (StringUtils.isNumeric(query))
            this.repository.findById(Long.parseLong(query)).ifPresent(
                    m -> moduleList.add(convertUtils.convert(m, ModuleOutDTO.class)));
        else
            moduleList.addAll(convertUtils.convert(this.repository.findByNameContaining(query), ModuleOutDTO.class));

        return moduleList;
    }

    public ModuleOutDTO createModule(ModuleInDTO moduleInDTO) throws Exception {

        if (this.repository.findByName(moduleInDTO.getName()).isPresent()) {
            throw new ValidationException("Module already exists");
        } else {
            return this.save(moduleInDTO, ModuleOutDTO.class);
        }

    }

    public ModuleOutDTO updateModule(long moduleId, ModuleInDTO moduleInDTO) throws Exception {
        return this.update(moduleId, moduleInDTO, ModuleOutDTO.class);
    }

}
