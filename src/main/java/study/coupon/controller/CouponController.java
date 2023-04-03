package study.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.coupon.domain.dto.RequestDto;
import study.coupon.domain.dto.ResponseDto;
import study.coupon.service.CouponService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    /**
     * 쿠폰 등록
     */
    @PostMapping
    public ResponseEntity<?> createCoupon(@RequestBody RequestDto requestDto) {
        ResponseDto responseDto = couponService.saveCoupon(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 쿠폰 수정
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCoupon(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        ResponseDto responseDto = couponService.updateCoupon(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 특정 아이디의 쿠폰 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCoupon(@PathVariable Long id) {
        ResponseDto responseDto = couponService.getCoupon(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 모든 쿠폰 조회
     * codeType=kor 전달시 쿠폰코드에 한글이 포함된 쿠폰만 조회
     */
    @GetMapping
    public ResponseEntity<List<?>> allCoupons(@RequestParam(value = "codeType", required = false, defaultValue = "") String codeType) {
        List<ResponseDto> list = couponService.allCoupons();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 쿠폰 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
