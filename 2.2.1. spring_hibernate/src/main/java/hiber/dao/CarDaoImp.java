package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public User getUser(String carModel, int carSeries) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User where car.model = :model and car.series = :series");
        return (User) query
                .setParameter("model", carModel)
                .setParameter("series", carSeries)
                .getSingleResult();

    }


}
