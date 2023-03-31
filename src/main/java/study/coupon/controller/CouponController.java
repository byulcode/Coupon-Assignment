package study.coupon.controller;

import org.springframework.web.bind.annotation.*;
import study.coupon.domain.dto.RequestDto;
import study.coupon.domain.dto.ResponseDto;
import study.coupon.service.CouponService;

import java.util.List;

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
    public ResponseDto createCoupon(@RequestBody RequestDto requestDto) {
        return couponService.saveCoupon(requestDto);
    }

    /**
     * 쿠폰 수정
     */
    @PatchMapping("/update/{id}")
    public ResponseDto updateCoupon(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        return couponService.updateCoupon(id, requestDto);
    }

    /**
     * 특정 아이디의 쿠폰 조회
     */
    @GetMapping("/{id}")
    public ResponseDto getCoupon(@PathVariable Long id) {
        return couponService.getCoupon(id);
    }

    /**
     * 모든 쿠폰 조회
     * codeType=kor 전달시 쿠폰코드에 한글이 포함된 쿠폰만 조회
     */
    @GetMapping("/list")
    public List<ResponseDto> allCoupons(@RequestParam(value = "codeType", required = false, defaultValue = "") String codeType) {
        if (codeType.equals("kor")) {
            return couponService.findCouponTypeKor(codeType);
        }
        return couponService.allCoupons();
    }

    /**
     * 쿠폰 삭제
     */
    @DeleteMapping("/delete/{id}")
    public void deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
    }

}
