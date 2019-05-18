package vu.che.mvcrest.coupon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Repository se ovde bavi imitacijom komunikacije sa bazom
 */
public class CouponRepository {

    private static String[] SHOP_NAME_LIST = {"Shop0", "Shop1", "Shop2", "Shop3", "Shop4", "Shop5", "Shop6", "Shop7", "Shop8", "Shop9"};
    private static String[] PRODUCT_NAME_LIST = {"Product0", "Product1", "Product2", "Product3", "Product4", "Product5", "Product6", "Product7", "Product8", "Product9"};
    private static List<Coupon> couponList;
    private static List<Shop> shopsList;

    static {
        shopsList = generateShops();
        // Just some dummy data to display at init
        couponList = generateCoupons();
    }

    /**
     * Generise 10 korisnika birajuci nasumicno imena i prezimena iz liste
     *
     * @return
     */
    private static List<Coupon> generateCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {

            Coupon coupon = new Coupon();
            coupon.setId((long) i);
            coupon.setShop(shopsList.get(random.nextInt(shopsList.size())));
            coupon.setProductName(PRODUCT_NAME_LIST[random.nextInt(PRODUCT_NAME_LIST.length)]);
            coupon.setDiscountedPrice(random.nextFloat()); // Set bounds for more accurate data
            coupon.setOriginalPrice(random.nextFloat());

            coupons.add(coupon);
        }

        return coupons;
    }

    /**
     * Generise 10 prodavnica birajuci imena iz liste
     *
     * @return
     */
    private static List<Shop> generateShops() {
        List<Shop> shops = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Shop shop = new Shop();
            shop.setId(String.valueOf(i));
            shop.setShopName(SHOP_NAME_LIST[i]);
            shops.add(shop);
        }
        return shops;
    }


    /**
     * Vraca sve Coupon-e u sistemu.
     * Ako je firstName dato onda ce vratiti listu svih korisnika sa tim imenom.
     *
     * @param firstName Opcioni filter za ime
     * @return
     */
    public synchronized static List<Coupon> getCoupons(String firstName) {

        if (firstName != null) {
            return couponList
                    .stream()
                    .filter(u -> u.getProductName().equalsIgnoreCase(firstName)).collect(Collectors.toList());
        }

        return couponList;
    }

    /**
     * Vraca sve Shops u sistemu.
     *
     * @return
     */
    public synchronized static List<Shop> getShops() {
        return shopsList;
    }

    public synchronized static Coupon getCouponsById(Integer id) {
        return couponList.get(id);
    }

    public synchronized static Coupon addCoupon(Coupon coupon) {
        coupon.setId((long) couponList.size());
        couponList.add(coupon);
        return coupon;
    }

    public synchronized static void deleteCoupon(Long id) {
        couponList.removeIf(coupon -> coupon.getId().equals(id));
    }

}
