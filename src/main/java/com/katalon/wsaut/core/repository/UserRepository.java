package com.katalon.wsaut.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.katalon.wsaut.core.entity.Gender;
import com.katalon.wsaut.core.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByGender(Gender gender);

    List<User> findByAgeGreaterThanEqual(int age);

    List<User> findByGenderAndAgeGreaterThanEqual(Gender gender, int age);
}
