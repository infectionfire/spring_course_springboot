package com.ivanovs.spring.springboot.spring_course_springboot.dao;

import com.ivanovs.spring.springboot.spring_course_springboot.entity.Employee;
import org.hibernate.Session;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    @Autowired
    private EntityManager entityManager;//создается автоматически, бин не описываем

    @Override
    public List<Employee> getAllEmployees() {

        Session session = entityManager.unwrap(Session.class);//обертка сессии, разворот обертки


        Query<Employee> query = session.createQuery("from Employee"
                , Employee.class);
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {//сохраняется здесь, т.к. дао имеет связь с бд
        //если новый работник, придет ид==0, если редактируем, то через емп. инфо получим текущий ид
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);//метод хайбернейт сохранение или редактирование
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);

        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query<Employee> query = session.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);//заменяем значение
        query.executeUpdate();//отвечает за апдейт и делет
    }
}
