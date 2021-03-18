package com.ex.dao;

import com.ex.models.entities.Recipes;
import com.ex.models.entities.StatTrackingObj;
import com.ex.models.users.User;
import lombok.NoArgsConstructor;
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
import java.util.List;

@Repository
@NoArgsConstructor
public class RecipeDAO implements Persistable, RecipePersist{
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Object getById(int recipID) {

        try {
            Session session = sessionFactory.getCurrentSession();
//            Criteria cr = session.createCriteria(Recipes.class);
//            cr.add(Restrictions.eq("recipeID", recipeID));
//  public          List results = cr.list();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Recipes> cr = cb.createQuery(Recipes.class);
            Root<Recipes> root = cr.from(Recipes.class);
            cr.select(root).where(cb.equal(root.get("recipID"), recipID));

            Query query = session.createQuery(cr);

            Object results = query.getSingleResult();
//            Recipes recipes = session.get(Recipes.class, recipID);

            return results;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("No recipes found");
            return null;
        }
    }

    @Override
    @Transactional
    public List<Recipes> getAll() {
        try{
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Recipes> cr = cb.createQuery(Recipes.class);
            Root<Recipes> root = cr.from(Recipes.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            List<Recipes> results = query.getResultList();
            return results;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Object save(Object obj) {
        Session session = sessionFactory.getCurrentSession();
        session.save(obj);
        return obj;
    }

    @Override
    @Transactional
    public void delete(Object obj) {

    }

    @Override
    @Transactional
    public void update(Object obj) {

    }
}
