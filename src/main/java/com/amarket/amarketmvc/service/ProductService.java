package com.amarket.amarketmvc.service;

import com.amarket.amarketmvc.model.Category;
import com.amarket.amarketmvc.model.Product;
import com.amarket.amarketmvc.repository.CategoryRepository;
import com.amarket.amarketmvc.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Product saveOrUpdate(Product product){
        if (product.getId() == null)
            return this.save(product);
        return this.update(product);
    }

    public Page<Product> mainFindAllVisible(String searchKeyword, String categorySlug, int page, int size) {
        if (categorySlug.equals("all") && searchKeyword.equals("")){
            return this.findAllVisible(page, size);
        }else if (!searchKeyword.equals("")){
            return this.findAllVisibleByKeyword(searchKeyword, page, size);
        }
        return this.findAllVisibleByCategory(categorySlug, page, size);
    }

    private Product save(Product product) {
        product.setViews(0L);
        product.setSlug(UUID.randomUUID().toString());
        product.setVisibility(false);
        product.setTrending(false);
        product.setNewArrival(false);
        if (product.getQuantity().getDisplay() == null) product.getQuantity().setDisplay(false);
        if (product.getQuantity().getNumber() == null) product.getQuantity().setNumber(0L);
        if (product.getDiscount().getDisplay() == null) product.getDiscount().setDisplay(false);
        return productRepository.save(product);
    }

    public Product update(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findBySlug(String productSlug){
        return productRepository.findBySlug(productSlug ).get();
    }

    public Page<Product> findAllVisible(int page, int size) {
        return productRepository.findAllByVisibility(true, PageRequest.of(page, size));
    }

    public Page<Product> findAllVisibleByCategory(String categorySlug, int page, int size) {
        Category category = categoryService.findBySlug(categorySlug);
        return productRepository.findAllByVisibilityAndCategory(true, category, PageRequest.of(page, size));
    }

    public Page<Product> findAllVisibleByKeyword(String keyword, int page, int size) {
        return productRepository.findAllByVisibilityAndTitleContaining(true, keyword, PageRequest.of(page, size));
    }

    public List<Product> findAllVisibleAndTrending(){
        return productRepository.findAllByVisibilityAndTrending(true, true);
    }

    public List<Product> findAllVisibleAndNewArrivals(){
        return productRepository.findAllByVisibilityAndNewArrival(true, true);
    }

    public void delete(Long productId){
        productRepository.deleteById(productId);
    }

    public void changeVisibility(Long productId) {
        Product product = productRepository.findById(productId).get();
        product.setVisibility(!product.getVisibility());
        this.update(product);
    }

    public void changeNewArrival(Long id) {
        Product product = productRepository.findById(id).get();
        product.setNewArrival(!product.getNewArrival());
        this.update(product);
    }

    public void changeTrending(Long id) {
        Product product = productRepository.findById(id).get();
        product.setTrending(!product.getTrending());
        this.update(product);
    }


    public Product findById(Long id){
        return productRepository.findById(id).get();
    }

    public Long countAllProducts(){
        return productRepository.count();
    }


    public Integer countVisibleProducts(){
        return productRepository.countByVisibility(true);
    }

    public Integer countVisibleTrending(){
        return productRepository.countByTrending(true);
    }

    public Integer countVisibleNewArrivals(){
        return productRepository.countByNewArrival(true);
    }

    @Async
    public void updateViews(Product product){
        Long views = product.getViews();
        product.setViews((views+1));
        this.update(product);
    }


}
