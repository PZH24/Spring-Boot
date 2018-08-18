package com.example.demo.dao;

import com.example.demo.entity.MovieInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieDao extends PagingAndSortingRepository<MovieInfo,String>{
    //通过电影名查询
    @Query("select m from MovieInfo m where m.movieName like ?1")
    List<MovieInfo> gdByMoviceName(String moviceName);
    List<MovieInfo>  findByMovieNameLikeOrderByIdAsc(String moviceName);
    List<MovieInfo> findMovieInfoByMovieNameAndMovieUrl(String moviceName,String movieUrl);
    List<MovieInfo> findMovieInfoByMovieName(String moviceName);

}
