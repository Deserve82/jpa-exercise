package jpabook;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member(id, "kangho", 29);
        em.persist(member);

        member.setAge(19);

        Member foundMember = em.find(Member.class, id);
        System.out.println(foundMember.getAge() +" , " + foundMember.getUsername());

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("member size : " + members.size());

        em.remove(member);
    }
}
