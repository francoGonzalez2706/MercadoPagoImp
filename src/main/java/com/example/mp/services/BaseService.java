package com.example.mp.services;


import com.example.mp.Entitis.Base;
import com.example.mp.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseService<T extends Base, ID extends Serializable>  {

    protected BaseRepository<T,ID> baseRepository;

    public BaseService(BaseRepository<T, ID> baseRepository)
    {
        this.baseRepository = baseRepository;
    }


    @Transactional
    public List<T> findAll() throws Exception {
        try {
            return baseRepository.findAll();
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

  
    @Transactional
    public List<T> findAllActive() throws Exception {
        try {
            return baseRepository.findAll().stream().filter(entity -> !entity.getEstado()).collect(Collectors.toList());
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

   
    @Transactional
    public T findById(ID id) throws Exception {
        try {
            Optional<T> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public T save(T entity)throws Exception {
        try {
            entity = baseRepository.save(entity);
            return entity;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public T update(T entity) throws Exception {
        try {
            if (entity.getId() == null) {
                throw new Exception("La entidad a modificar debe contener un Id.");
            }
            return baseRepository.save(entity);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

 
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            Optional<T> entityOptional = baseRepository.findById(id);
            if (entityOptional.isPresent()) {
                entityOptional
                        .get()
                        .setEstado(false);
                baseRepository.save(entityOptional.get());
                return true;
            }else {
                throw new Exception("No existe la entidad");
            }
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public Page<T> findAll(Pageable pageable) throws Exception {
        try {
            Page<T> entities = baseRepository.findAll(pageable);
            return entities;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}