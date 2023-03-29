package study.coupon.controller;

import org.springframework.web.bind.annotation.*;
import study.coupon.domain.Coupon;
import study.coupon.domain.dto.RequestDto;
import study.coupon.domain.dto.ResponseDto;
import study.coupon.service.CouponService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }
    /**
     * 쿠폰 등록
     */
    @PostMapping("/create")
    public ResponseDto createCoupon(@RequestBody RequestDto couponDto) {
        Coupon coupon = couponService.saveCoupon(couponDto);
        return ResponseDto.from(coupon);
    }

    /**
     * 쿠폰 수정
     */
    @PatchMapping("/update/{id}")
    public ResponseDto updateCoupon(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        Coupon coupon = couponService.updateCoupon(id, requestDto);
        return ResponseDto.from(coupon);
    }

    /**
     * 특정 아이디의 쿠폰 조회
     */
    @GetMapping("/coupon/{id}")
    public ResponseDto getCoupon(@PathVariable Long id) {
        Coupon coupon = couponService.getCoupon(id);
        return ResponseDto.from(coupon);
    }

    /**
     * 모든 쿠폰 조회
     */
    @GetMapping("/allCoupons")
    public List<ResponseDto> allCoupons() {
        List<Coupon> list = couponService.allCoupons();
        return list.stream().map(ResponseDto::from).collect(Collectors.toList());
    }

    /**
     * 쿠폰 삭제
     */
    @DeleteMapping("/delete/{id}")
    public void deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
    }
}
