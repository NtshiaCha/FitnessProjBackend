package com.ex.dao;

import com.ex.models.entities.StatTrackingObj;
import com.ex.models.users.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAO implements Persistable, UserPersist {
    private SessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional
    public User getById(int userId) {

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            cr.select(root).where(cb.equal(root.get("userId"), userId));

            Query query = session.createQuery(cr);

            User results = (User) query.getSingleResult();

            return results;

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("No user found");
            return null;
        }
    }

    @Override
    @Transactional
    public User getByEmail(String email) {

//        List<User> user = new ArrayList<>();

        // this is the contextual session
        // this session is being managed by the TxManager
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.equal(root.get("email"), email));

        Query query = session.createQuery(cr);
        User results = (User) query.getSingleResult();

        return results;
//        user.add(results);
//        if(!results.isEmpty()) {
//            user = (User)results.get(0);
//        }
//        return user;
    }

    @Override
    @Transactional
    public User getByUsername(String userName) {
        try {
            Session session = sessionFactory.getCurrentSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            cr.select(root).where(cb.equal(root.get("userName"), userName));

            Query query = session.createQuery(cr);
            User results = (User) query.getSingleResult();
//            if(!results.isEmpty()) {
//                user = (User)results.get(0);
//            }
            return results;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("No user found");
            return null;
        }
    }


    @Override
    @Transactional
    public List getAll() {
        try{
            Session session = sessionFactory.getCurrentSession();
//            Criteria cr = session.createCriteria(User.class);
//            List results = cr.list();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            List<User> results = query.getResultList();

            return results;
        } catch (Exception e) {
        e.printStackTrace();
        return null;
        }
    }

    @Override
    @Transactional
    public Object save(Object obj) {
        try{
        Session session = sessionFactory.getCurrentSession();
        session.save(obj);
        return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Transactional
    public void delete(Object obj) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.delete(obj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void update(Object obj) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.update(obj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
