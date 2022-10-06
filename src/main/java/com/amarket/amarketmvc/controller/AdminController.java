package com.amarket.amarketmvc.controller;

import com.amarket.amarketmvc.model.Category;
import com.amarket.amarketmvc.model.Product;
import com.amarket.amarketmvc.service.CategoryService;
import com.amarket.amarketmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/admin")
    public String dashboard(Model model){
        Long nbrAllProducts = productService.countAllProducts();
        Integer nbrVisibleProducts = productService.countVisibleProducts();
        Integer nbrNewArrivalsProducts = productService.countVisibleNewArrivals();
        Integer nbrTrendingProducts = productService.countVisibleTrending();

        Long nbrVisibleCategories = categoryService.countVisibleCategories();
        Long nbrAllCategories = categoryService.countAllCategories();
        Long nbrTrendingCategories = categoryService.countTrendingCategories();

        model.addAttribute("nbrAllProducts", nbrAllProducts);
        model.addAttribute("nbrVisibleProducts", nbrVisibleProducts);
        model.addAttribute("nbrNewArrivalsProducts", nbrNewArrivalsProducts);
        model.addAttribute("nbrTrendingProducts", nbrTrendingProducts);

        model.addAttribute("nbrVisibleCategories", nbrVisibleCategories);
        model.addAttribute("nbrAllCategories", nbrAllCategories);
        model.addAttribute("nbrTrendingCategories", nbrTrendingCategories);

        return "admin/admin-dashboard";
    }

    @GetMapping("/admin/product/product_form")
    public String productFormPage(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        return "admin/product-form";
    }

    @GetMapping("/admin/products")
    public String products(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/admin-products";
    }

    @PostMapping("/admin/product/save")
    public String saveOrUpdateProduct(Product product){
        productService.saveOrUpdate(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/e")
    public String editProduct(
            @RequestParam("productId") Long id,
            Model model){
        Product product = productService.findById(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);

        return "admin/product-form";
    }

    @GetMapping("/admin/categories")
    public String categories(Model model){
        List<Category> categories = categoryService.findAll();

        model.addAttribute("categoryToAdd", new Category());
        model.addAttribute("categoryToUpdate", new Category());
        model.addAttribute("categories", categories);
        return "admin/admin-categories";
    }

    @PostMapping("/admin/category/save")
    public String saveOrUpdateCategory(Category category){
        Category temp = categoryService.saveOrUpdate(category);
        return "redirect:/admin/categories";
    }

    @PostMapping("/admin/product/d")
    public String delete(@RequestParam("productId") Long id){
        productService.delete(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/product/visibility")
    public String updateVisibility(@RequestParam("productId") Long id){
        productService.changeVisibility(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/product/new_arrival")
    public String updateNewArrival(@RequestParam("productId") Long id){
        productService.changeNewArrival(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/product/trending")
    public String updateTrending(@RequestParam("productId") Long id){
        productService.changeTrending(id);
        return "redirect:/admin/products";
    }

}
