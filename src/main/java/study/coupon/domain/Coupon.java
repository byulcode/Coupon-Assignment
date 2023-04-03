package study.coupon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Coupon(Long id, String code, String name, int discount, LocalDateTime useDate, LocalDateTime endDate, boolean usageStatus) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.discount = discount;
        this.useDate = useDate;
        this.endDate = endDate;
        this.usageStatus = usageStatus;
    }

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
