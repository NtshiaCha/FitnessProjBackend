package com.ex.dao;

import com.ex.models.entities.Recipes;
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
public class StatTrackingDAO implements  Persistable{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public StatTrackingObj getById(int userId) {
        try {
            System.out.println(userId);
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<StatTrackingObj> cr = cb.createQuery(StatTrackingObj.class);
            Root<StatTrackingObj> root = cr.from(StatTrackingObj.class);
            cr.select(root).where(cb.equal(root.get("userID"), userId));

            Query query = session.createQuery(cr);

            StatTrackingObj results = (StatTrackingObj) query.getSingleResult();
//            results.add(result);
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
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<StatTrackingObj> cr = cb.createQuery(StatTrackingObj.class);
            Root<StatTrackingObj> root = cr.from(StatTrackingObj.class);

            Query query = session.createQuery(cr);
            List<StatTrackingObj> results = query.getResultList();
            return results;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Object save(Object obj) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.save(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Object obj) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(obj);
    }

    @Override
    @Transactional
    public void update(Object obj) {
        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
    }
}
