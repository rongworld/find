package com.ncuhome.find.service;

/*
* 展示信息类
* */

import com.ncuhome.find.respository.LostRepository;
import com.ncuhome.find.respository.LostStaticRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplayLost {
    private LostRepository lostRepository = LostStaticRepository.lostRepository;

    private Pageable getPageable(int pageNumber, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        return new PageRequest(pageNumber, size, sort);
    }

    public List getLostList(int pageNumber, int size) {
        Page page = lostRepository.findAll(getPageable(pageNumber, size));
        return page.getContent();
    }

}
