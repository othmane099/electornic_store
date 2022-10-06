package com.amarket.amarketmvc.controller;

import com.amarket.amarketmvc.model.Category;
import com.amarket.amarketmvc.model.IndexPage;
import com.amarket.amarketmvc.model.Product;
import com.amarket.amarketmvc.service.CategoryService;
import com.amarket.amarketmvc.service.IndexPageService;
import com.amarket.amarketmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class IndexController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final IndexPageService indexPageService;

    @GetMapping("/")
    public String index(Model model){
        IndexPage parameters = indexPageService.getParameters();
        List<Category> categories = categoryService.findAllVisibleAndTrending();
        List<Product> products = productService.findAllVisibleAndTrending();
        List<Product> newArrivalProducts = productService.findAllVisibleAndNewArrivals();

        model.addAttribute("parameters", parameters);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("newArrivalProducts", newArrivalProducts);
        return "index";
    }



    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/wishlist")
    public String wishlist(){
        return "wishlist";
    }

    @GetMapping("/empty")
    public String empty(){return "empty-search";}


}
