package study.coupon.domain.exception;

public class InvalidCouponException extends RuntimeException{
    public InvalidCouponException() {
        this("존재하지 않는 쿠폰입니다.");
    }

    public InvalidCouponException(String msg) {
        super(msg);
    }

}
