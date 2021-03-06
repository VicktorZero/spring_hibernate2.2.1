package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user.getUserCars());
      sessionFactory.getCurrentSession().save(user);

   }



   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
    @SuppressWarnings("unchecked")
    @Override
    public List <User> findUsers(String model, int series) {

        Query query = sessionFactory.getCurrentSession().createQuery("select user from User user  where user.car.model = :model and user.car.series = :series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList();
    }
}
