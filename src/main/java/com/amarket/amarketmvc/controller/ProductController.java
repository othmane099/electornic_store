package com.amarket.amarketmvc.controller;

import com.amarket.amarketmvc.model.Category;
import com.amarket.amarketmvc.model.Product;
import com.amarket.amarketmvc.service.CategoryService;
import com.amarket.amarketmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public String visibleProducts(
            @RequestParam(value = "k", defaultValue = "") String searchKeyword,
            @RequestParam(value = "c", defaultValue = "all") String categorySlug,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "6") int size,
            Model model){


        Page<Product> products = productService.mainFindAllVisible(searchKeyword, categorySlug, page, size);
        if (products.isEmpty() && !searchKeyword.equals("")) return "redirect:/empty";
        List<Category> categories = categoryService.findAllVisible();
        String currentCategoryName = categoryService.getCurrentCategoryNameBySlug(categorySlug);
        model.addAttribute("products", products.getContent());
        model.addAttribute("categories", categories);
        model.addAttribute("totalPages", new int[products.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("currentCategorySlug", categorySlug);
        model.addAttribute("currentCategoryName", currentCategoryName);
        model.addAttribute("currentKeyword", searchKeyword);

        return "products-list";

    }

    @GetMapping("/product")
    public String product(@RequestParam("s") String productSlug,
                          Model model){
        Product product = productService.findBySlug(productSlug);
        productService.updateViews(product);
        model.addAttribute("product", product);
        return "product-detail";
    }


}
