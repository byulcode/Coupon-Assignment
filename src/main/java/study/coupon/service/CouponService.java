package study.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.coupon.domain.Coupon;
import study.coupon.domain.dto.RequestDto;
import study.coupon.domain.dto.ResponseDto;
import study.coupon.domain.advice.exception.NotFoundIdException;
import study.coupon.repository.CouponRepository;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseDto saveCoupon(RequestDto requestDto) {
        Coupon coupon =  couponRepository.save(requestDto.toEntity());
        coupon.checkUsageStatus();
        return ResponseDto.from(coupon);
    }

    /**
     * 쿠폰 조회
     */
    public ResponseDto getCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(NotFoundIdException::new);
        return ResponseDto.from(coupon);
    }

    /**
     * 전체 쿠폰 조회
     */
    public List<ResponseDto> allCoupons() {
        List<Coupon> list = couponRepository.findAll();
        return list.stream().map(ResponseDto::from).collect(Collectors.toList());
    }

    /**
     * 쿠폰 수정
     */
    @Transactional
    public ResponseDto updateCoupon(Long id, RequestDto requestDto) {
        Coupon coupon = couponRepository
                .findById(id).orElseThrow(NotFoundIdException::new);
        coupon.update(requestDto.getCode(), requestDto.getUseDate(), requestDto.getEndDate(), requestDto.isUsageStatus());
        coupon.checkUsageStatus();
        return ResponseDto.from(coupon);
    }

    /**
     * 쿠폰 삭제
     */
    @Transactional
    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    /**
     * 쿠폰코드에 한글이 포함된 쿠폰만 조회
     */
    public List<ResponseDto> findCouponTypeKor(String codeType) {
        if (codeType.equals("kor")) {
            List<Coupon> list = couponRepository.findCouponsByCodeContaining();
            return list.stream().map(ResponseDto::from).collect(Collectors.toList());
        }
        return null;
    }
}
