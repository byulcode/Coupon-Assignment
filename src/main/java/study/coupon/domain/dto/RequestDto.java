package study.coupon.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.coupon.domain.Coupon;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestDto {

    private String code;
    private String name;
    private int discount;
    private LocalDateTime useDate;
      private LocalDateTime endDate;
    private boolean usageStatus;

    public RequestDto(String code, String name, int discount, LocalDateTime useDate, LocalDateTime endDate, boolean usageStatus) {
        this.code = code;
        this.name = name;
        this.discount = discount;
        this.useDate = useDate;
        this.endDate = endDate;
        this.usageStatus = usageStatus;
    }

    //dto -> entity
    public Coupon toEntity() {
        return Coupon.builder()
                .code(code)
                .name(name)
                .discount(discount)
                .useDate(useDate)
                .endDate(endDate)
                .usageStatus(usageStatus)
                .build();
    }

}
