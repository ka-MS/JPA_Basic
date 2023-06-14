package org.example.hellojpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Member") // 저장될 테이블 이름 설정 관생적으로 Member애 저장하지만 User등으로 바꿀 수도 있음
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 1) // db에 시퀀스를 만들어서 사용
public class Member {
    //allocationSize 는 미리 x개를 땡겨서 올려놓고 메모리에서 사용하는 방식 여러 웹서버가 있어도 동시성 이슈없이 사용가능

//    @Id
//    private Long id;
//    @Column(unique = true, length = 20) // ddl시에 적용되는것으로 유니크 설정 길이지정해주는것
//    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, // 시퀀스를 받아서 영속성에 보관했다가 커밋시점에 사용
            generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "member_id")
    private Long id;
    // nullable을 사용하면 null가능 사용 unique제약조건을 설정해 줄 수 있는데 이름을 랜덤으로 정해줘서 잘 사용하지 않음
    // columndefinitions를 사용하면 컬럼정보를 직접 입력가능
    @Column(name = "username") // 엔티티명은 유저네임이지만 컬럼명은 name사용
    private String username;


//    @Column(name = "team_id")
//    private String teamId;

    // insertable = false, updatable = false 옵션주면 읽기전용매핑가능하여 일대다 양방향 매핑도 가능 하지만 다대일 양방향을 사용하시길
    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team; // 테이블이 생성될 때 fk를 자동생성한다.

    @OneToOne
    @JoinColumn(name="locker_id")
    private Locker locker;

//    private Integer age;
//    @Enumerated(EnumType.STRING) // 해당 어노테이션을 사용하면 enum 사용 사능 ordinal사용금지
//    private RoleType roleType;
//    @Temporal(TemporalType.TIMESTAMP) // 로컬데이트 사용하면 안써줘도됨
//    private Date createdDate;
//
//    private LocalDate testLocalDate; // 날따만
//    private LocalDateTime testLocalDateTime; // 시간까지
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//    @Lob
//    private String description;
//    @Transient
//    private int temp;

//    public void changeTeam(Team team) { // 로직이 들어가면 set으로 쓰지 않고 changTeam 등의 형식으로 씀
//        this.team = team;
//        team.getMembers().add(this);
//    }
    // structure 단축키 알트 7
}
