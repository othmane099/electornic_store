package com.amarket.amarketmvc.repository;

import com.amarket.amarketmvc.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findAllByProductId(Long propertyId);

}
