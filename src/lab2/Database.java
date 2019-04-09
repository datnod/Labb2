package lab2;

import java.sql.*;

public class Database {


    //Variables used for database connection
    private static Database db = null;
    private Connection connect = null;
    private static int increment=0;
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
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect");
        }

    }

    //Send latest move

    public void sendMove(String move){



       if (increment==0){
            String sendMove = "UPDATE Labb2.User SET move = '"+ move +"' WHERE playerID = '"+ increment + "'";
            try (Statement prepared = connect.prepareStatement(sendMove)) {
                prepared.executeUpdate(sendMove);

            } catch (SQLException e){
                e.printStackTrace();
            }
       increment = 1;
       }
else if (increment==1){
           String sendMove = "UPDATE Labb2.User SET move = '"+ move +"' WHERE playerID = '"+ increment + "'";
           try (Statement prepared = connect.prepareStatement(sendMove)) {
               prepared.executeUpdate(sendMove);

           } catch (SQLException e){
               e.printStackTrace();
           }
           System.out.println(increment);

           increment = 0;
       }


    }

    //Get latest move
    public String getMove(){

        String returnMove = null;
        String getMove = "SELECT move FROM labb2.user";
        try(PreparedStatement preparedStmt = connect.prepareStatement(getMove)){
            ResultSet resultSet = preparedStmt.executeQuery();
            if (increment == 0) {
                resultSet.next();
                resultSet.next();
                returnMove = resultSet.getString("move");
            } else if (increment == 1){
                resultSet.next();
                returnMove = resultSet.getString("move");
            }
            

        }catch (SQLException e){
            e.printStackTrace();
        }

       return returnMove;
    }

}
