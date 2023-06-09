package org.example.hellojpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Member") // 저장될 테이블 이름 설정 관생적으로 Member애 저장하지만 User등으로 바꿀 수도 있음
public class Member {

    @Id
    private Long id;
    private String name;

}
