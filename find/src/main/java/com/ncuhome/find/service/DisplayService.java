package com.ncuhome.find.service;

/*
* 展示信息类
* */

import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class DisplayService {
    @Autowired
    private LostRepository lostRepository;

    public List getPage(int pageNumber, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        Pageable pageable = new PageRequest(pageNumber, size, sort);
        Page page = lostRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path<Integer> path = root.get("status");
            Predicate predicate = criteriaBuilder.equal(path, 0);
            return predicate;
        }, pageable);
        List<Lost> lostList = page.getContent();
        if (lostList != null) {
            return lostList;
        } else {
            return null;
        }
    }
}
