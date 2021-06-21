package com.hgstudy.catalogservice.service;

import com.hgstudy.catalogservice.entity.CatalogEntity;
import com.hgstudy.catalogservice.entity.CatalogRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService{

    CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
