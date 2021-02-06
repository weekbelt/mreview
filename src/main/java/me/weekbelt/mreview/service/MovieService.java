package me.weekbelt.mreview.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import me.weekbelt.mreview.dto.MovieDTO;
import me.weekbelt.mreview.dto.PageRequestDTO;
import me.weekbelt.mreview.dto.PageResultDTO;
import me.weekbelt.mreview.entity.Movie;
import me.weekbelt.mreview.entity.MovieImage;

public interface MovieService {

    Long register(MovieDTO movieDTO);

    PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO);

    default MovieDTO entityToDTO(Movie movie, List<MovieImage> movieImages, Double avg, Long reviewCnt) {
        MovieDTO movieDTO = MovieDTO.builder()
            .mno(movie.getMno())
            .title(movie.getTitle())
            .regDate(movie.getRegDate())
            .modDate(movie.getModDate())
            .build();

        movieImages.stream().map(movieImage -> {
            MovieIMageDTO.builder().imgName(movieImage.getImgName())
                .path(movieImage.getPath())
                .uuid(movieImage.getBuild())
                .build()).collect(Collectors.toList());

        movieDTO.setImageDTOList(movieImageDTOList);
        movieDTO.setAge(avg);
        movieDTO.setReviewCnt(reviewCnt.intValue());

        return movieDTO;
    }

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) {

        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
            .mno(movieDTO.getMno())
            .title(movieDTO.getTitle())
            .build();

        entityMap.put("movie", movie);

        List<MovieImageDTO> movieImageList = movieDTO.getImageDTOList();

        // MovieImageDTO 처리
        if (movieImageList != null && movieImageList.size() > 0) {
            List<MovieImage> imageList = movieImageList.stream().map(movieImageDTO ->
                MovieImage.builder()
                    .path(movieImageDTO.getPath())
                    .imgName(movieImageDTO.getImgName())
                    .uuid(movieImageDTO.getUuid())
                    .movie(movie)
                    .build()).collect(Collectors.toList());
            entityMap.put("imgList", imageList);
        }
        return entityMap;
    }

}
