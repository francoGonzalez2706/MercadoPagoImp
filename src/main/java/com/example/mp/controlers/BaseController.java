package com.example.mp.controlers;

import com.example.mp.Entitis.Base;
import com.example.mp.services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

public abstract class BaseController<E extends Base, S extends BaseService<E,Long>> {
    protected final S service;

    protected BaseController(S service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<E>> findAll() throws Exception {
        List<E> entities = service.findAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public Optional<E> findByID(@PathVariable Long id) throws Exception {
        return Optional.ofNullable(service.findById(id));
    }

    @PostMapping()
    public ResponseEntity<E> create(@RequestBody E entity) throws Exception {
        E newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping()
    public ResponseEntity<E> update(@RequestBody E entity) throws Exception {
        E updateEntity = service.update(entity);
        return ResponseEntity.ok(updateEntity);
    }

    //Borrado Logico
    @PutMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        service.delete(id);
    }

}
