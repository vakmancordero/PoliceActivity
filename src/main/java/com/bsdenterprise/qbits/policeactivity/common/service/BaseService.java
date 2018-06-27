package com.bsdenterprise.qbits.policeactivity.common.service;

import com.bsdenterprise.qbits.policeactivity.common.exceptions.EntityNotFoundException;
import com.bsdenterprise.qbits.policeactivity.common.model.BaseEntity;
import com.bsdenterprise.qbits.policeactivity.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends JpaRepository, E extends BaseEntity> {

    @Autowired
    protected T repository;

    @Autowired
    protected ConvertUtils convertUtils;

    private Class<E> clazz;

    public Class<E> clazz() {

        if (this.clazz == null) {

            Class<?> actualClass = this.getClass();

            ParameterizedType pt = (ParameterizedType) actualClass.getGenericSuperclass();

            this.clazz = (Class<E>) pt.getActualTypeArguments()[1];
        }

        return this.clazz;
    }

    public <K> K findById(long id, Class<K> clazz) throws Exception {

        Optional<E> optionalEntity = this.repository.findById(id);

        if (!optionalEntity.isPresent()) {
            throw new EntityNotFoundException(this.clazz(), "id", String.valueOf(id));
        }

        if (ObjectUtils.isEmpty(clazz)) return null;

        E entity = optionalEntity.get();
        K optionalDTO = convertUtils.convert(entity, clazz);

        return optionalDTO;
    }

    public <K> List<K> findAll(Class<K> clazz) {

        List<E> all = repository.findAll();

        List<K> list = convertUtils.convert(all, clazz);

        return list;
    }

    public <K, D> D save(K dto, Class<D> clazz) throws Exception {

        E entity = convertUtils.convert(dto, this.clazz());
        D outDTO = convertUtils.convert(repository.save(entity), clazz);

        return outDTO;
    }

    public <K, D> D update(long id, K dto, Class<D> clazz) throws Exception {

        Optional<E> optionalEntity = this.repository.findById(id);

        if (!optionalEntity.isPresent()) {
            throw new EntityNotFoundException(this.clazz(), "id", String.valueOf(id));
        }

        E entity = optionalEntity.get();

        convertUtils.map(dto, entity);
        repository.save(entity);

        D outDTO = convertUtils.convert(entity, clazz);

        return outDTO;
    }

    public <K> K delete(long id, Class<K> clazz) throws Exception {

        Optional<E> optionalEntity = this.repository.findById(id);

        if (!optionalEntity.isPresent()) {
            throw new EntityNotFoundException(this.clazz(), "id", String.valueOf(id));
        }

        E entity = optionalEntity.get();
        entity.setSoftDelete(true);

        repository.save(entity);

        K dto = clazz != null ?
                convertUtils.convert(entity, clazz) : null;

        return dto;
    }

    public void delete(long id) throws Exception {
        this.delete(id, null);
    }

    public ConvertUtils getConvertUtils() {
        return convertUtils;
    }

}