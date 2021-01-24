package me.weekbelt.mreview.repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import me.weekbelt.mreview.entity.Movie;
import me.weekbelt.mreview.entity.MovieImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

//    @Commit
//    @Transactional
//    @Test
//    public void insertMovies() {
//        IntStream.rangeClosed(1, 100).forEach(i -> {
//            Movie movie = Movie.builder()
//                .title("Movie...." + i)
//                .build();
//
//            System.out.println("------------------------");
//
//            movieRepository.save(movie);
//
//            int count = (int) (Math.random() * 5) + 1;   // 1, 2, 3, 4
//
//            for (int j = 0; j < count; j++) {
//                MovieImage movieImage = MovieImage.builder()
//                    .uuid(UUID.randomUUID().toString())
//                    .movie(movie)
//                    .imgName("test" + j + ".jpg")
//                    .build();
//                movieImageRepository.save(movieImage);
//            }
//
//            System.out.println("-----------------------");
//        });
//    }

    @Test
    public void testListPage() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Direction.DESC, "mno"));

        // when
        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        // then
        List<Object[]> content = result.getContent();
        content.forEach(objects -> System.out.println(Arrays.toString(objects)));
    }

    @Test
    public void testGetMovieWithAll() {
        // given
        List<Object[]> result = movieRepository.getMovieWithAll(94L);

        // when
        System.out.println(result);

        // then
        result.forEach(objects -> System.out.println(Arrays.toString(objects)));
    }
}