package com.amarket.amarketmvc.service;

import com.amarket.amarketmvc.model.Category;
import com.amarket.amarketmvc.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category saveOrUpdate(Category category){

        if (category.getId() == null)
            return this.save(category);
        return this.update(category);
    }

    private Category save(Category category) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        category.setDataFilter(generatedString);

        category.setSlug(UUID.randomUUID().toString());
        return categoryRepository.save(category);
    }

    private Category update(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findBySlug(String categorySlug){
        return categoryRepository.findBySlug(categorySlug).get();
    }

    public String getCurrentCategoryNameBySlug(String categorySlug){
        if (categorySlug.equals("all")) return "all";
        return this.findBySlug(categorySlug).getName();
    }

    public List<Category> findAllVisible(){
        return categoryRepository.findAllByVisibility(true);
    }

    public List<Category> findAllVisibleAndTrending(){
        return categoryRepository.findAllByVisibilityAndTrending(true, true);
    }

    public Long countAllCategories(){
        return categoryRepository.count();
    }

    public Long countVisibleCategories(){
        return categoryRepository.countByVisibility(true);
    }

    public Long countTrendingCategories(){
        return categoryRepository.countByTrending(true);
    }
}
