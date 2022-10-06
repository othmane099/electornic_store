package com.amarket.amarketmvc.service;

import com.amarket.amarketmvc.model.IndexPage;
import com.amarket.amarketmvc.repository.IndexPageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class IndexPageService {

    private final IndexPageRepository repository;

    public IndexPage getParameters(){
        return repository.findAll().get(0);
    }

}
