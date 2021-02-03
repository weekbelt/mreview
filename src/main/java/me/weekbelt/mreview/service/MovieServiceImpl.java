package me.weekbelt.mreview.service;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.weekbelt.mreview.dto.MovieDTO;
import me.weekbelt.mreview.entity.Movie;
import me.weekbelt.mreview.entity.MovieImage;
import me.weekbelt.mreview.repository.MovieImageRepository;
import me.weekbelt.mreview.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {

        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);
        movieImageList.forEach(movieImageRepository::save);
        return movie.getMno();
    }
}
