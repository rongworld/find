package com.ncuhome.find.respository;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class StudentStaticRepository {
    public static StudentRepository studentRepository;

    @Resource
    private void setStudentRepository(StudentRepository studentRepository) {
        StudentStaticRepository.studentRepository = studentRepository;
    }
}
