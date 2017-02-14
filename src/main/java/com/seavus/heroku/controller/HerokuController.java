package com.seavus.heroku.controller;

import com.seavus.heroku.dataaccess.HibernateSessionFactory;
import com.seavus.heroku.model.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;

@Controller
public class HerokuController {

    @RequestMapping("/")
    @ResponseBody
    String helloWorld() {
        return "Hello World";
    }

    @RequestMapping("/jdbc")
    @ResponseBody
    String herokuJdbc() {
        try (Connection connection = DriverManager.getConnection(System.getenv("JDBC_DATABASE_URL"))) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS superheroes (name VARCHAR(255))");
            statement.executeUpdate("INSERT INTO superheroes VALUES ('Batman')");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM superheroes");
            StringBuilder stringBuilder = new StringBuilder();
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("name")).append(" ");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }

    @RequestMapping("/hibernate")
    @ResponseBody
    String herokuHibernate() {
        try (Session session = HibernateSessionFactory.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new Item("Bat"));
            transaction.commit();
            return session.createQuery("FROM Item").getResultList().toString();
        } catch (Exception e) {
            return e.toString();
        }
    }
}
