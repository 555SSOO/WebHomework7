package vu.che.mvcrest.coupon;

import java.io.Serializable;
import java.util.List;

public class Coupon implements Serializable {

    private Long id;
    private Shop shop;
    private String productName;
    private Float discountedPrice;
    private Float originalPrice;

    private List<Coupon> closeRelatives;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public List<Coupon> getCloseRelatives() {
        return closeRelatives;
    }

    public void setCloseRelatives(List<Coupon> closeRelatives) {
        this.closeRelatives = closeRelatives;
    }
}
