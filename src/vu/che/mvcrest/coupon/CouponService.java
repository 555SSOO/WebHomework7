package vu.che.mvcrest.coupon;

import java.util.List;

/**
 * Servisni sloj se bavi svom "biznis logikom"
 */
public class CouponService {

    public List<Coupon> getCoupons(String firstName){
        return CouponRepository.getCoupons(firstName);
    }

    public Coupon getCouponsById(Integer id){
        return CouponRepository.getCouponsById(id);
    }

    public List<Shop> getShops(){
        return CouponRepository.getShops();
    }

    public Coupon addCoupon(Coupon coupon){
        return CouponRepository.addCoupon(coupon);
    }

    public void deleteCoupon(Long id){
        CouponRepository.deleteCoupon(id);
    }
}
