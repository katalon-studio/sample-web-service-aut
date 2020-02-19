package com.katalon.wsaut.core.repository;

import com.katalon.wsaut.core.entity.User;

import org.example.webservice.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByGender(Gender gender);

    List<User> findByAgeGreaterThanEqual(int age);

    List<User> findByGenderAndAgeGreaterThanEqual(Gender gender, int age);
}
