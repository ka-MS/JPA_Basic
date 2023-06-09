package org.example.test;

public class check {
    public static void main(String[] args) {

        Test build = Test.builder()
                .id(1)
                .age(20)
                .name("홍길동")
                .child(
                        Child.builder()
                                .grade("1학년")
                                .subject("수학")
                                .build()
                )
                .build();

        System.out.println(build.getChild().getGrade());

    }
}
