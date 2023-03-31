package study.coupon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.coupon.domain.Coupon;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM coupon WHERE code REGEXP '[가-힣]'")
    List<Coupon> findCouponsByCodeContaining();
}
