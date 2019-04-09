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
    public void sendMove(String user, String move){
        String selectUser = "SELECT move FROM Labb2.table WHERE user = "+ user;
        try(PreparedStatement preparedStatement = connect.prepareStatement(selectUser)) {
            preparedStatement.setString(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();
            String latestMove = resultSet.getString("move");

            String sendMove = "UPDATE databe.table SET move WHERE user = '" + user + "' ";
            try (Statement prepared = connect.prepareStatement(sendMove)) {
                if (!sendMove.equals(latestMove)) {
                    prepared.executeUpdate(sendMove);
                }else{
                    System.out.println("Cannot send two moves in a row");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    //Get latest move
    public String getMove(String user){
        String returnMove = null;
        String getMove = "SELECT move, user FROM database.table WHERE user = ?";
        try(PreparedStatement preparedStmt = connect.prepareStatement(getMove)){
            preparedStmt.setString(1, user);
            ResultSet resultSet = preparedStmt.executeQuery();
            returnMove = resultSet.getString("move");
            

        }catch (SQLException e){
            e.printStackTrace();
        }

       return returnMove;
    }

}
