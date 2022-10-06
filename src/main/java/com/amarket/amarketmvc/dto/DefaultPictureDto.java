package com.amarket.amarketmvc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultPictureDto {
    private Long productId;
    private Long pictureId;
    private Boolean isDefault;

    @Override
    public String toString() {
        return "DefaultPictureDto{" +
                "productId=" + productId +
                ", pictureId=" + pictureId +
                ", isDefault=" + isDefault +
                '}';
    }
}
