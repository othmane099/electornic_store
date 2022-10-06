package com.amarket.amarketmvc;

import com.amarket.amarketmvc.model.*;
import com.amarket.amarketmvc.repository.AppSettingsRepository;
import com.amarket.amarketmvc.repository.IndexPageRepository;
import com.amarket.amarketmvc.repository.ProductRepository;
import com.amarket.amarketmvc.service.AppSettingsService;
import com.amarket.amarketmvc.service.CategoryService;
import com.amarket.amarketmvc.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableAsync
public class AMarketMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(AMarketMvcApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ProductService productService,
//                                               CategoryService categoryService,
//                                               AppSettingsService appSettingsService,
//                                               IndexPageRepository indexPageRepository){
//        return args -> {
//
//            IndexPage indexPage = new IndexPage();
//            indexPage.setCardServicesVisibility(true);
//            indexPage.setMainSlideVisibility(true);
//            indexPage.setTrendingComponentVisibility(true);
//            indexPage.setNewArrivalsComponentVisibility(true);
//            indexPageRepository.save(indexPage);
//
//            AppSettings appSettings = new AppSettings();
//            appSettings.setAgencySrcLocation("https://maps.google.com/maps?q=algiers&t=&z=13&ie=UTF8&iwloc=&output=embed");
//            appSettings.setAgencyAddress("32 Krim Blekacem");
//            appSettings.setAgencyCity("Belouazdad Alger");
//            appSettings.setOpeningTime("De 9 AM - 4 PM ");
//            appSettings.setOpeningDays("5 Jours par semaine");
//            appSettings.setMailReceptionAgency("othmaben99@gmail.com");
//            appSettings.setPhone1("0442 21 11 09");
//            appSettings.setPhone2("0470 93 40 84");
//            appSettingsService.save(appSettings);
//
//            for (int i = 0; i < 20; i++) {
//                Random rd = new Random(); // creating Random object
//
//                Category c = new Category();
//                c.setName("Electronics - "+i);
//                c.setVisibility(rd.nextBoolean());
//                c.setTrending(rd.nextBoolean());
//                Category cat = categoryService.saveOrUpdate(c);
//
//                Discount d = new Discount();
//                d.setOldPrice("250.00");
//                d.setPercentage("20");
//
//
//                d.setDisplay(rd.nextBoolean());
//
//                Quantity q = new Quantity();
//                q.setNumber(10L);
//                q.setDisplay(true);
//                q.setQuantityBadge(QuantityBadge.values()[rd.nextInt(2)]);
//
//                Product p = new Product();
//                p.setTitle("Product - "+i);
//                p.setSlug(UUID.randomUUID().toString());
//                p.setTrending(rd.nextBoolean());
//                p.setNewArrival(rd.nextBoolean());
//
//                p.setDescription("Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. \n" +
//                        " Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis \n les années 1500, quand un imprimeur anonyme assembla ensemble " +
//                        "des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles,\n mais " +
//                        "s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années" +
//                        " 1960 grâce à la vente de feuilles Letraset contenant \ndes passages du Lorem Ipsum, et, plus récemment, par son inclusion dans d" +
//                        "es applications de mise en page de texte, comme Aldus PageMaker.");
//                p.setPrice("155.50");
//                p.setVisibility(true);
//                p.setDiscount(d);
//                p.setQuantity(q);
//                p.setCategory(cat);
//
//
//                d.setProduct(p);
//                q.setProduct(p);
//
//                cat.getProducts().add(productService.saveOrUpdate(p));
//                categoryService.saveOrUpdate(cat);
//
//            }
//        };
//    }

}
