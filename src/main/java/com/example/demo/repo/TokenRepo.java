package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Token;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    Token findByValue(String value);
}
