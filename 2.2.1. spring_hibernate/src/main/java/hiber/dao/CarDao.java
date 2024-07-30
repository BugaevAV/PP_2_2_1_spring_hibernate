package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

public interface CarDao {
    void addCar(Car car);

    User getUser(String carModel, int carSeries);
}
