package com.ex.dao;

import com.ex.models.entities.Exercise;
import com.ex.models.entities.Recipes;
import com.ex.models.entities.StatTrackingObj;
import lombok.NoArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Restrictions;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
public class ExerciseDAO implements Persistable, ExercisePersist {
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Object getById(int id) {
        Session session = sessionFactory.getCurrentSession();
//            Criteria cr = session.createCriteria(Recipes.class);
//            cr.add(Restrictions.eq("recipeID", recipeID));
//            List results = cr.list();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Exercise> cr = cb.createQuery(Exercise.class);
        Root<Exercise> root = cr.from(Exercise.class);
        cr.select(root).where(cb.gt(root.get("exerciseID"), id));

        Query query = session.createQuery(cr);
        List<Exercise> results = query.getResultList();

        return results;

    }

    @Override
    @Transactional
    public List getAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
//            Criteria cr = session.createCriteria(Recipes.class);
//            cr.add(Restrictions.eq("recipeID", recipeID));
//            List results = cr.list();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Exercise> cr = cb.createQuery(Exercise.class);
            Root<Exercise> root = cr.from(Exercise.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            List<Exercise> results = query.getResultList();

            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


        @Override
        @Transactional
        public List getByExerciseType(String exercisetype) {
//            List<Exercise> exercises = new ArrayList<>();
            Session session = sessionFactory.getCurrentSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Exercise> cr = cb.createQuery(Exercise.class);
            Root<Exercise> root = cr.from(Exercise.class);
            cr.select(root).where(cb.equal(root.get("exerciseType"), exercisetype));

//            Query query = session.createQuery(cr);
//
//            StatTrackingObj result = (StatTrackingObj) query.getSingleResult();
//            results.add(result);


            Query query = session.createQuery(cr);

            List<Exercise> results = query.getResultList();
            return results;
            }


    @Transactional
    public Exercise getByExerciseName(String exercisename) {

        try {
            Session session = sessionFactory.getCurrentSession();
            Exercise exercise = (Exercise) session.get(Exercise.class, exercisename);
            return exercise;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("No exercise found");
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
