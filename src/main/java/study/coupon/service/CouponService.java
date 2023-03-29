package study.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.coupon.domain.Coupon;
import study.coupon.domain.dto.RequestDto;
import study.coupon.repository.CouponRepository;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CouponService {

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    /**
     * 쿠폰 등록
     */
    @Transactional
    public Coupon saveCoupon(RequestDto couponDto) {
        return couponRepository.save(couponDto.toEntity());
    }

    /**
     * 쿠폰 조회
     */
    public Coupon getCoupon(Long id) {
        return couponRepository.findById(id).orElseThrow();
    }

    /**
     * 전체 쿠폰 조회
     */
    public List<Coupon> allCoupons() {
        return couponRepository.findAll();
    }

    /**
     * 쿠폰 수정
     */
    @Transactional
    public Coupon updateCoupon(Long id, RequestDto requestDto) {
        Coupon coupon = couponRepository.findById(id).orElseThrow();
        coupon.update(requestDto.getCode(), requestDto.getUseDate(), requestDto.getEndDate(), requestDto.isUsageStatus());
        return coupon;
    }

    /**
     * 쿠폰 삭제
     */
    @Transactional
    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

}
