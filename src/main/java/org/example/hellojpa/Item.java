package org.example.hellojpa;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@DiscriminatorColumn // d타입 자동으로 만들어주는 어노테이션
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
