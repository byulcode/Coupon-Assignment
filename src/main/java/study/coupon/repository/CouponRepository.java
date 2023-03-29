package study.coupon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import study.coupon.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
