package org.example.hellojpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    /*
    member에 있는 team과 연결되어있다는 의미 / 손님이라는 의미 / 해당 mappeBy에 값을 넣어도 저장안됨
    일대다의 관계의경우 잘 안쓰는데 다 쪽에 fk가 있는데 일 쪽에 주인을 만들어주는 비정상적인 관계이기 때문
    엔티티가 관리하는 외래키가 다른 테이블에 있음
     */
    @OneToMany
    @JoinColumn(name = "team_id")
    private List<Member> members = new ArrayList<>();



}
