package com.technology.repository;

import com.technology.model.File;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-3-24
 * Time: 16:48
 */
public interface FileRepository extends PagingAndSortingRepository<File, Long> {
}