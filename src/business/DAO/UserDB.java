package business.DAO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class UserDB  {
    private static String url = "jdbc:mysql://localhost:3306/usersdb?allowPublicKeyRetrieval=true&serverTimezone=Europe/Minsk&useSSL=false";
    private static String username = "root";
    private static String password = "123456";
    private  int rows;

    //methods of database

    public static ArrayList<User>select(int limit,int records ){

        ArrayList<User>users = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn= DriverManager.getConnection(url,username,password)) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * FROM users ORDER BY id LIMIT" + " " + limit + "," + records);
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String login = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    String email = resultSet.getString(4);
                    User user = new User(id, login, password, email);
                    users.add(user);
                }
            }

        }catch (Exception ex){
            System.out.println(ex);
        }
        return users;
    }
    public static User selectOne(int id){
        User user=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String SQL= "Select * FROM users WHERE id=?";
                try (PreparedStatement preparedStatement=conn.prepareStatement(SQL)){
                    preparedStatement.setInt(1,id);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int userId=resultSet.getInt(1);
                        String userLogin=resultSet.getString(2);
                        String userPassword=resultSet.getString(3);
                        String userEmail=resultSet.getString(4);
                        user = new User(userId,userLogin,userPassword,userEmail);
                    }
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return  user;
    }
    public static int insert (User user){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String SQL= "Insert into users(login,password,email) Values (?,?,?)";
                try (PreparedStatement preparedStatement=conn.prepareStatement(SQL)){
                    preparedStatement.setString(1,user.getLogin());
                    preparedStatement.setString(2,user.getPassword());
                    preparedStatement.setString(3,user.getEmail());
                    return preparedStatement.executeUpdate();
                }
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    public static int update (User user){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String SQL= "UPDATE users SET login=?,password=?,email=? where id=?";
                try (PreparedStatement preparedStatement=conn.prepareStatement(SQL)){
                    preparedStatement.setString(1,user.getLogin());
                    preparedStatement.setString(2,user.getPassword());
                    preparedStatement.setString(3,user.getEmail());
                    preparedStatement.setInt(4,user.getId());
                    return preparedStatement.executeUpdate();
                }
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    public static int delete(int id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String SQL= "DELETE FROM users  where id=?";
                try (PreparedStatement preparedStatement=conn.prepareStatement(SQL)){
                    preparedStatement.setInt(1,id);
                    return preparedStatement.executeUpdate();
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public int getRows() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn= DriverManager.getConnection(url,username,password)) {
                Statement statement = conn.createStatement();
                ResultSet rs =statement.executeQuery("SELECT count(*) FROM users");
               if (rs.next())  this.rows=rs.getInt(1);
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return rows;
    }
}
