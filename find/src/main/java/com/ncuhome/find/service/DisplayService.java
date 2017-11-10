package com.ncuhome.find.service;

/*
* 展示信息类
* */

import com.ncuhome.find.respository.Lost;
import com.ncuhome.find.respository.LostRepository;
import com.ncuhome.find.respository.LostStaticRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplayService {
    private LostRepository lostRepository = LostStaticRepository.lostRepository;

    public List getPage(int pageNumber, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        Page page = lostRepository.findAll(new PageRequest(pageNumber,size,sort));
        List<Lost> lostList= page.getContent();
        if (lostList != null){
            return lostList;
        }else{
            return null;
        }
    }
}
