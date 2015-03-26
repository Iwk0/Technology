package com.technology.repository;

import com.technology.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Iwk0 on 13/03/2015.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
}