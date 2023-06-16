package org.example.hellojpa;

import lombok.Data;

@Data
public class MemberInfoResponseDTO {

    private Long id;
    private String username;
    private String city;
    private String street;
    private String zipCode;
    private Long teamId;
    private String teamName;
}
