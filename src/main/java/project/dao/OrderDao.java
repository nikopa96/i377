package project.dao;

import org.springframework.stereotype.Repository;
import project.model.Order;
import project.model.Report;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderDao {

    @PersistenceContext
    private EntityManager em;

    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    public Order findById(Long id) {
        try {
            TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.id = :id", Order.class);
            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Order save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }

        return order;
    }

    @Transactional
    public void deleteAll() {
        em.createQuery("DELETE FROM Order").executeUpdate();
    }

    @Transactional
    public void deleteById(Long id) {
        Query query = em.createQuery("DELETE FROM Order o WHERE o.id = :id");
        query.setParameter("id", id).executeUpdate();
    }

//    public Report createReport() {
//
//        return em.createQuery("SELECT r FROM Report r", Report.class).getSingleResult();
//    }
}
