package org.example.hellojpa;

import javax.persistence.*;
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
//            for(int i = 0; i < 51; i++) {
//                Member member = Member.builder()
//                        .username("c" + i)
//                        .build();
//                em.persist(member);
//            }

            Team team = Team.builder()
                    .name("TeamA")
                    .build();

            em.persist(team);

            Member member = Member.builder()
                    .username("userA")
                    .team(team)
                    .build();

            em.persist(member);

//            em.flush();
//            em.clear(); // db 쿼리문을 확인하고 싶다면

            Member findMember = em.find(Member.class, member.getId());
//            System.out.println(findMember);
            Team findTeam = findMember.getTeam();

            System.out.println("=============================" + findTeam.getName());

            List<Member> members = findMember.getTeam().getMembers();

            for(Member m : members) {
                System.out.println("====================================="+m.getUsername());
            }


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
//            Query query = em.createQuery("select m from Member as m");
//            List resultList = query.getResultList();
//            for (Object object : resultList) {
//                System.out.println(object);
//                MemberMapper.INSTANCE.toMember(object);
//            }
            // 반환 타입을 지정하지 않으면 object타입으로 반환한다.
            // MapStruct 라이브러리는 컴파일 시점에 구체적인 타입을 알아야 하므로 Object타입을 직접 매핑하는건 허용되지 않는다
            // 따라서 위와같은 문장은 ExceptionInInitializerError 정적변수 초기화 예외가 발생한다.

            // paging
//            List<Member> result = em.createQuery("select m from Member as m where m.id > 40", Member.class)
//                    .setFirstResult(2)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            for (Member members : result) {
//                System.out.println(members);
//            }
//
//            // 파라미터 바인딩
//            String usernameParam = "c0"; // = :속성명 을 사용하면 파라미터를 이름으로 구분할 수 있다.
//            em.createQuery("select m from Member m where m.username = :username", Member.class)
//                    .setParameter("username", usernameParam) // 파라미터 바인딩
//                    .getResultStream()
//                    .forEach(System.out::println);
//
//            em.createQuery("select m from Member m where m.username = '" + usernameParam + "'", Member.class)
//                    .getResultStream() // 이런식으로 직접 문자를 더해 만들면 sql인젝션 공격을 당할 수 있다.
//                    .forEach(System.out::println);


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
