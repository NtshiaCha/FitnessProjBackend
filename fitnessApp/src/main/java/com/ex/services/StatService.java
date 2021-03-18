package com.ex.services;

import com.ex.dao.ExerciseDAO;
import com.ex.dao.StatTrackingDAO;
import com.ex.models.entities.StatTrackingObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("statService")
@Scope("prototype")
@Transactional(
        isolation = Isolation.REPEATABLE_READ,
        propagation = Propagation.REQUIRED
)
public class StatService {
    StatTrackingDAO dao;
    //setting up the DAO
    @Autowired
    public void setDao(StatTrackingDAO dao){
        this.dao = dao;
    }

        public StatTrackingObj getByUserId(int i){
            return dao.getById(i);
        }

        public ArrayList<StatTrackingObj> getAll() {
            return (ArrayList<StatTrackingObj>)dao.getAll();
    }

        public int save(StatTrackingObj obj){
            return (int)dao.save(obj);
    }

        void delete(StatTrackingObj obj){
            dao.delete(obj);
        }

        void update(StatTrackingObj obj){
            dao.update(obj);
        }
}
