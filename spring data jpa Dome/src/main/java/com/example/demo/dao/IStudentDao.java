package com.example.demo.dao;

import com.example.demo.entity.StudentInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentDao extends PagingAndSortingRepository<StudentInfo,String> {
}
