package com.ncuhome.find.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostRepository extends JpaRepository<Lost, Integer> {
    List<Lost> findLostByCardNumber(String cardNumber);

    Lost findByCardNumberAndDate(String cardNumber, Long date);
}
