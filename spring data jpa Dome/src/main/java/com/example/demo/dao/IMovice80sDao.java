package com.example.demo.dao;

import com.example.demo.entity.Movie80sInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovice80sDao extends PagingAndSortingRepository<Movie80sInfo,String> {
    Movie80sInfo findMovie80sInfoByMovieNameAndMovieUrl(String moviceName,String movieUrl);
}
