package study.coupon.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "coupon")
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private int discount;
    private LocalDateTime useDate;  //사용 날짜
    private LocalDateTime endDate; //만료 날짜
    private boolean usageStatus;    //사용 현황

    public void update(String code, LocalDateTime useDate, LocalDateTime endDate, boolean usageStatus) {
        this.code = code;
        this.useDate = useDate;
        this.endDate = endDate;
        this.usageStatus = usageStatus;
    }

    public void checkUsageStatus() {
        if (this.useDate != null) {
            this.usageStatus = true;
        }
    }


}
