package com.ncuhome.find.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostRepository extends JpaRepository<Lost, Integer> {
    List<Lost> findByCardNumber(String cardNumber);
    Lost findByCardNumberAndStatus(String cardNumber,Integer status);
    List<Lost> findByDateBetween(Long dateStart,Long dateEnd);
    List<Lost> findByName(String name);
    List<Lost> findByDateBeforeAndStatus(Long date,Integer status);
}
