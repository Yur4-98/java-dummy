package com.example.dummy.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.*;



@Component
public class DataBaseWorker {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public User getUserByLogin(String login) {
        String sql = "SELECT users.login, password, email, date " +
                "FROM users " +
                "JOIN emails ON users.login=emails.login " +
                "WHERE users.login = (?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User(resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getTimestamp("date"));

                return user;
            }else
                throw new UserNotFoundException("Invalid login");//выброс исключения



        } catch (SQLException e) {
            System.err.println("Ошибка при выполнении запроса: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public int postUser(User user){                 //посмотреть как при двух statement
        String sql = "INSERT INTO emails (login, email) VALUES (?,?);" +
                "INSERT INTO users (login, password, date) VALUES (?,?,?);";

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            user.setCurrentDate();
            preparedStatement.setTimestamp(5, user.getDate());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при вставке пользователя: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}
