package study.coupon.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.coupon.domain.Coupon;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseDto {

    private Long id;
    private String code;
    private String name;
    private int discount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDate useDate;
    private LocalDate endDate;
    private boolean usageStatus;

    public ResponseDto(Long id,String code, String name, int discount, LocalDateTime createDate, LocalDateTime updateDate, LocalDate useDate, LocalDate endDate, boolean usageStatus) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.discount = discount;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.useDate = useDate;
        this.endDate = endDate;
        this.usageStatus = usageStatus;
    }

    // entity -> dto (정적 팩토리 메서드)
    public static ResponseDto from(Coupon coupon) {
        return new ResponseDto(
                coupon.getId(),
                coupon.getCode(),
                coupon.getName(),
                coupon.getDiscount(),
                coupon.getCreateDate(),
                coupon.getUpdateDate(),
                coupon.getUseDate(),
                coupon.getEndDate(),
                coupon.isUsageStatus()
        );
    }

}
