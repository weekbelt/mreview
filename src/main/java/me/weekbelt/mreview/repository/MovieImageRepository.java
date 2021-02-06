package me.weekbelt.mreview.repository;

import me.weekbelt.mreview.entity.MovieImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {

    Page<Object[]> getListPage(Pageable pageable);
}
