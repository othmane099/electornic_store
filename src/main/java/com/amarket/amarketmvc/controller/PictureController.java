package com.amarket.amarketmvc.controller;

import com.amarket.amarketmvc.dto.DefaultPictureDto;
import com.amarket.amarketmvc.model.Picture;
import com.amarket.amarketmvc.service.PictureService;
import com.flickr4java.flickr.FlickrException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@AllArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    @PostMapping("/admin/product/uploadPictures")
    String uploadPictures(@RequestPart("files") MultipartFile[] files,
                          @RequestParam("productId") Long productId) throws FlickrException, IOException, ExecutionException, InterruptedException {

        pictureService.savePictures(files, productId);
        return "redirect:/admin/product/pictures?productId="+productId;
    }

    @GetMapping("/admin/product/pictures")
    public String productPictures(@RequestParam("productId") Long id, Model model) throws Exception {
        List<Picture> pictures = pictureService.findAllByProductId(id);
        model.addAttribute("pictures", pictures);
        model.addAttribute("productId", id);
        model.addAttribute("DefaultPictureDto", new DefaultPictureDto());


        return "admin/admin-pictures";
    }

    @GetMapping("/admin/product/picture-form")
    public String productPictureForm(@RequestParam("productId") Long id, Model model){
        model.addAttribute("productId", id);
        return "admin/picture-form";
    }

    @PostMapping("/admin/product/pictures/d")
    public String deletePicture(@RequestParam("pictureId") Long id,
                                @RequestParam("photoId") String pId,
                                @RequestParam("productId") Long productId,
                                Model model) throws FlickrException, IOException, ExecutionException, InterruptedException {
        pictureService.deletePicture(id, pId, productId);
        model.addAttribute("productId", productId);
        return "redirect:/admin/product/pictures?productId="+productId;
    }

    @PostMapping("/admin/product/set_default_picture")
    public String setDefaultPicture(@RequestParam("pictureId") Long pictureId,
                                    @RequestParam("productId") Long productId){

        pictureService.setDefaultPicture(productId, pictureId);
        return "redirect:/admin/product/pictures?productId="+productId;
    }
}
