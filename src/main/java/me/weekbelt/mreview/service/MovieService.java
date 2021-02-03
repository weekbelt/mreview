package me.weekbelt.mreview.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import me.weekbelt.mreview.dto.MovieDTO;
import me.weekbelt.mreview.dto.MovieImageDTO;
import me.weekbelt.mreview.entity.Movie;
import me.weekbelt.mreview.entity.MovieImage;

public interface MovieService {

    Long register(MovieDTO movieDTO);

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
