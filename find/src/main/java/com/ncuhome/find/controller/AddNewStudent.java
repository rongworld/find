package com.ncuhome.find.controller;

import com.ncuhome.find.respository.Student;
import com.ncuhome.find.respository.StudentRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddNewStudent {
    @Autowired
    private StudentRepository studentRepository;
    @PostMapping(value = "/addnew")
    public String add(@RequestBody String json){
        JSONObject jsonObject = new JSONObject(json);
        Student student = new Student();
        student.setName(jsonObject.getString("name"));
        student.setQq("2238114323");
        student.setIdCardNumber(jsonObject.getString("sfz"));
        student.setXuehao(jsonObject.getString("xyk"));
        student.setJianhangCardNumber(jsonObject.getString("jhk"));
        studentRepository.save(student);
        return "ok";
    }
}
