package study.coupon.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.coupon.domain.Coupon;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RequestDto {

    private String code;
    private String name;
    private int discount;
    private LocalDate useDate;
    private LocalDate endDate;
    private boolean usageStatus;

    public RequestDto(String code, String name, int discount, LocalDate useDate, LocalDate endDate, boolean usageStatus) {
        this.code = code;
        this.name = name;
        this.discount = discount;
        this.useDate = useDate;
        this.endDate = endDate;
        this.usageStatus = usageStatus;
    }

    //dto -> entity
    public Coupon toEntity() {
        return new Coupon(
                null,
                this.code,
                this.name,
                this.discount,
                this.useDate,
                this.endDate,
                this.isUsageStatus()
        );
    }

}
