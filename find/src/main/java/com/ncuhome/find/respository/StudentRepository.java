package com.ncuhome.find.respository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByXuehao(String xuehao);

    Student findByJianhangCardNumber(String jianhangCardNumber);

    Student findByIdCardNumber(String idCardNumber);
}
