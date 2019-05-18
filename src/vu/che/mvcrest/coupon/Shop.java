package vu.che.mvcrest.coupon;

import java.io.Serializable;

public class Shop implements Serializable {

    private String id;
    private String shopName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
