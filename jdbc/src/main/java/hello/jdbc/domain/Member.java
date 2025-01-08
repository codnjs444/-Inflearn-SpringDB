package hello.jdbc.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Member {

    private String memberId;
    private int money;

    public Member(String memberId) {
        this.memberId = memberId;
    }

    public Member(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }
}
