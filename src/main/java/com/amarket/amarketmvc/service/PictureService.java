package com.amarket.amarketmvc.service;

import com.amarket.amarketmvc.model.Picture;
import com.amarket.amarketmvc.model.Product;
import com.amarket.amarketmvc.repository.PictureRepository;
import com.flickr4java.flickr.FlickrException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PictureService {

    private final PictureRepository pictureRepository;
    private final ProductService productService;
    private final FlickrService flickrService;

    public void deletePicture(Long id, String pId, Long productId) throws FlickrException, IOException, ExecutionException, InterruptedException {
        flickrService.connect();
        flickrService.deletePhoto(pId);
        pictureRepository.deleteById(id);
        Product product = productService.findById(productId);
        product.getPictures().removeIf( pic -> pic.getId().equals(id));
        productService.update(product);
    }

    public void savePictures(MultipartFile[] pictures, Long productId) throws FlickrException, IOException, ExecutionException, InterruptedException {

        if (pictures.length == 0)
            throw new RuntimeException("pictures list is empty, it should contain at least one picture");
        flickrService.connect();
        Product product = productService.findById(productId);

        Arrays.stream(pictures)
                .forEach(picture -> {
                    try {
                        Picture pictureTemp = flickrService.savePhoto(picture.getInputStream(), picture.getOriginalFilename());
                        pictureTemp.setProduct(product);
                        if (!StringUtils.hasLength(pictureTemp.getLink())) {
                            throw new RuntimeException("Some problems occurred during saving images");
                        }
                        product.getPictures().add(pictureRepository.save(pictureTemp));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        productService.update(product);
    }

    public List<Picture> findAllByProductId(Long id) throws Exception {
        if (id == null){
            log.error("Product's ID is null");
            throw new Exception("Product's ID is null");
        }
        return pictureRepository.findAllByProductId(id);
    }

    public void setDefaultPicture(Long productId, Long pictureId) {
        Product product = productService.findById(productId);
        product.getPictures().forEach(picture -> {
            if (Objects.equals(picture.getId(), pictureId)){
                picture.setIsDefault(true);
                product.setDefaultPicture(picture.getLink());
            }else picture.setIsDefault(false);
            pictureRepository.save(picture);
        });
        productService.update(product);
    }
}
