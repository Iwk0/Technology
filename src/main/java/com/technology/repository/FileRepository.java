package com.technology.repository;

import com.technology.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-3-24
 * Time: 16:48
 */
public interface FileRepository extends JpaRepository<File, Long> {
}