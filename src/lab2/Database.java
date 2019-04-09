package lab2;

import java.sql.*;

public class Database {

    //Variables used for database connection
    private static Database db = null;
    private Connection connect = null;

    //Singleton of Database
    public static Database getInstance(){
        if (db == null){
            db = new Database();
        }
        return db;
    }

    //Database constructor
    private Database() {

        String url = "jdbc:mysql://localhost:3306/Labb2?user=root&password=root";
        try {
            connect = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Send latest move
    public void sendMove(String move){

        String sendMove = "UPDATE Labb2.User SET move = '"+ move +"'";
        try (Statement prepared = connect.prepareStatement(sendMove)) {
                prepared.executeUpdate(sendMove);

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    //Get latest move
    public String getMove(){

        String returnMove = null;
        String getMove = "SELECT move FROM Labb2.User ";
        try(PreparedStatement preparedStmt = connect.prepareStatement(getMove)){
            ResultSet resultSet = preparedStmt.executeQuery();
            returnMove = resultSet.getString("move");
            

        }catch (SQLException e){
            e.printStackTrace();
        }

       return returnMove;
    }

}
