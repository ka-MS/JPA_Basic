package org.example.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 엔티티매니저는 프로그램 실행시점에 딱 하나만 생성되어있어야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // Create 코드 - 비영속
            Member member = new Member();
            member.setId(3L);
            member.setName("홍길이");
            // 영속
            em.persist(member);
            em.flush();
            // 영속상태 이후에 쿼리문이 날라가게됨

            // Read 코드
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println(findMember);

            em.find(Member.class, "홍길동");
            
//            findMember.setName("홍길동");
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

//            for (Member member : result) {
//                System.out.println(member.getName());
//            }

            tx.commit();
        } catch (Exception e) {
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
