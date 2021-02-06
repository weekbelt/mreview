package me.weekbelt.mreview.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.weekbelt.mreview.dto.MovieDTO;
import me.weekbelt.mreview.dto.PageRequestDTO;
import me.weekbelt.mreview.dto.PageResultDTO;
import me.weekbelt.mreview.entity.Movie;
import me.weekbelt.mreview.entity.MovieImage;
import me.weekbelt.mreview.repository.MovieImageRepository;
import me.weekbelt.mreview.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mno"));

        Page<Object[]> result = movieImageRepository.getListPage(pageable);

        Function<Object[], MovieDTO> fn = (arr -> entityToDTO(
            (Movie) arr[0],
            (List<MovieImage>) (Collections.singletonList((MovieImage) arr[1])),
            (Double) arr[2],
            (Long) arr[3]
        ));
        return new PageResultDTO<>(result, fn);
    }
}
