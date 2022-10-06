package com.amarket.amarketmvc.repository;

import com.amarket.amarketmvc.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findBySlug(String slug);
    List<Category> findAllByVisibility(Boolean visibility);
    List<Category> findAllByVisibilityAndTrending(Boolean visibility, Boolean trending);

    Long countByVisibility(Boolean visibility);
    Long countByTrending(Boolean trending);
}
