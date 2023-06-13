package org.example.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 엔티티매니저는 프로그램 실행시점에 딱 하나만 생성되어있어야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = Member.builder().username("c")
//                    .build();
//            em.persist(member);
////            em.flush();
//
//            Member memberTest = em.find(Member.class, 1223L);
//            memberTest.setUsername("이름변경");


//            // Create 코드 - 비영속
//            Member member = Member.builder()
//                    .id(4L)
//                    .name("홍길사")
//                    .build();
//            System.out.println(member);
//
//            System.out.println("========create========");
//            // 영속
//            em.persist(member); // create
////            em.flush(); // flush는 컨텍스트 동기화 -> 비우는게 아님 반영하는것
//                /*
//                flush() 메서드를 호출하더라도 실제로 데이터베이스 트랜잭션은 커밋되지 않습니다.
//                이는 트랜잭션 커밋 시점까지 변경사항을 데이터베이스에 유지하고, 필요한 경우 롤백을 가능하게 하기 위함입니다.
//                 따라서 flush()를 호출한 후에 오류가 발생하면, 트랜잭션은 롤백되고 데이터베이스의 상태는 flush() 호출 이전 상태로 복구됩니다.
//                 */
//            // 영속상태 이후에 쿼리문이 날라가게됨
//            System.out.println("========end========");
//
//            // Read 코드
//            Member findMember = em.find(Member.class, 1L); //read
//            System.out.println(findMember);
////            findMember.setId(3L); //update
//            em.remove(findMember); //delete
            /*
            crud 간단정리
            c: persist()
            r: find()
            u: Object.setId()
            d: remove()
             */

            List<Member> result = em.createQuery("select m from Member as m where m.id > 40", Member.class)
                    .setFirstResult(2)
                    .setMaxResults(10)
                    .getResultList();

            for (Member members : result) {
                System.out.println(members);
            }

            tx.commit();
        } catch (Exception e) {
            // 해당 트랜잭션 안에서 하나라도 오류가 나면 전체롤백되게됨
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

        /*
        엔티티 매니저는 하나만 생성해서 애플리케이션 전체에 공유 하나만!
        쓰레드간에 공유x 사용하고 버려야함
        
         */

    }
}
