package com.amarket.amarketmvc.repository;

import com.amarket.amarketmvc.model.Category;
import com.amarket.amarketmvc.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySlug(String slug);
    Page<Product> findAllByVisibility(Boolean visibility, Pageable pageable);
    Page<Product> findAllByVisibilityAndCategory(Boolean visibility, Category category, Pageable pageable);
    Page<Product> findAllByVisibilityAndTitleContaining(Boolean visibility, String keyword, Pageable pageable);
    List<Product> findAllByVisibilityAndTrending(Boolean visibility, Boolean trending);
    List<Product> findAllByVisibilityAndNewArrival(Boolean visibility, Boolean newArrival);

    Integer countByVisibility(Boolean visibility);
    Integer countByTrending(Boolean trending);
    Integer countByNewArrival(Boolean newArrival);
}
