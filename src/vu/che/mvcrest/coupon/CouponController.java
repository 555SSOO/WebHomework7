package vu.che.mvcrest.coupon;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Controller ili Endpoint ce se baviti povezivanjem HTTP METODE/URL do biznis logike
 */
@Path("/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController() {
        this.couponService = new CouponService();
    }

    /**
     * Dohvatanje svih Coupon-a
     * Putanja je rest/coupons
     *
     * Takodje je moguce isfiltrirati korisnike po imenu.
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON) //Bitno je da navedemo sta je rezultujuci content type ove metode
    public List<Coupon> getCoupons(@QueryParam("firstName") String firstName) {
        return getCouponService().getCoupons(firstName);
    }

    /**
     * Dohvatanje svih Coupon-a
     * Putanja je rest/coupons
     *
     * Takodje je moguce isfiltrirati korisnike po imenu.
     * @return
     */
    @GET
    @Path("/shops")
    @Produces(MediaType.APPLICATION_JSON) //Bitno je da navedemo sta je rezultujuci content type ove metode
    public List<Shop> getShops() {
        return getCouponService().getShops();
    }

    /**
     * Dohvatanje jednog resursa po ID-u.
     * Putanja bi bila npr. rest/users/4
     * @param id
     * @return
     */
//    @GET
//    @Path("/{id}") // {id} navodi da se radi o promenljivoj id koja ce se proslediti kao argument metode
//    @Produces(MediaType.APPLICATION_JSON)
//    public Coupon getCouponsById(@PathParam("id") int id) { // Da bi se u tacan argument prosledio id mora da se oznaci anotacijom
//        return getCouponService().getCouponsById(id);
//    }

    /**
     * Cuvanje jednog resursa. Rezultat je taj resurs sa ID-em.
     * Putanja je rest/users
     * @param
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON) // Prilikom POST-a nam je bitno sta je content type onoga sto ova metoda prima zbog deserijalizacije
    @Produces(MediaType.APPLICATION_JSON)
    public Coupon addCoupon(Coupon coupon){
        getCouponService().addCoupon(coupon);
        return coupon;
    }

    /**
     * Brisanje jednog Coupon
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    public void deleteCoupon(@PathParam("id") long id){
        getCouponService().deleteCoupon(id);
    }

    public CouponService getCouponService() {
        return couponService;
    }
}
