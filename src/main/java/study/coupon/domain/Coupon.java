package study.coupon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Coupon extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private int discount;
    private LocalDate useDate = null;  //사용 날짜
    private LocalDate endDate; //만료 날짜
    private boolean usageStatus;    //사용 현황

    public Coupon(final Long id, final String code, final String name, final int discount, final LocalDate useDate, final LocalDate endDate, final boolean usageStatus) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.discount = discount;
        this.useDate = useDate;
        this.endDate = endDate;
        this.usageStatus = false;
    }

    public void update(String code, LocalDate useDate, LocalDate endDate, boolean usageStatus) {
        this.code = code;
        this.useDate = useDate;
        this.endDate = endDate;
        this.usageStatus = usageStatus;
    }


}
