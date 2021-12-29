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
   public void add(User user,String model, int series) {
    Car car = new Car(model,series);
     Session session = sessionFactory.getCurrentSession();
     user.setUserCars(car);
     session.save(user);
   }



   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

    @Override
    @SuppressWarnings("unchecked")
    public User findUsers(String model, int series) {

        User user = new User();
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Car  WHERE model=:model AND series=:series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        List<Car> carList  = query.getResultList();
        for (Car car:carList) {
             user = car.getUser();
        }
        return user;

    }
}
