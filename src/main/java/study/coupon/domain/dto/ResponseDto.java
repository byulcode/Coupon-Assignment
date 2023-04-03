package study.coupon.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.coupon.domain.Coupon;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
public class ResponseDto {

    private String code;
    private String name;
    private int discount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime useDate;
    private LocalDateTime endDate;
    private boolean usageStatus;

    public ResponseDto(String code, String name, int discount, LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime useDate, LocalDateTime endDate, boolean usageStatus) {
        this.code = code;
        this.name = name;
        this.discount = discount;
        this.createdAt = createDate;
        this.modifiedAt = updateDate;
        this.useDate = useDate;
        this.endDate = endDate;
        this.usageStatus = usageStatus;
    }

    // entity -> dto (정적 팩토리 메서드)
    public static ResponseDto from(Coupon coupon) {
        return ResponseDto.builder()
                .code(coupon.getCode())
                .name(coupon.getName())
                .discount(coupon.getDiscount())
                .createdAt(coupon.getCreatedAt())
                .modifiedAt(coupon.getModifiedAt())
                .useDate(coupon.getUseDate())
                .endDate(coupon.getEndDate())
                .usageStatus(coupon.isUsageStatus())
                .build();
    }

}
