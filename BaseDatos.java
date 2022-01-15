package ejercicio1juan;


import java.sql.*;
import javax.swing.JOptionPane;
 

public class BaseDatos 
{
    String driver="com.mysql.cj.jdbc.Driver"; 
    String prefix="jdbc:"+"mysql:"; 
    String hostName="//localhost:3306/"; 
    String urlFolder=""; 
    String dbName="usuario"; 
    String url=prefix+hostName+urlFolder+dbName; 
    String user="root"; 
    String password="";
    String query;
    Connection connection;


    public void Open() 
    {
        try {
            Class.forName(driver);
            connection =  DriverManager.getConnection(url,user,password);
            
               
           } catch (SQLException ex) { 
               
           System.out.print(ex);
           
           } catch (ClassNotFoundException e){
               System.out.print(e);
           }
      

    }
    
    public void Close(){
        
        try{
            
            connection.close();

        }
        catch(Exception e){
            
        }
        
    }

    public Object OrdenesSQL(String query) 
    {
        Object o = new Object();
        try 
        {
            PreparedStatement statment = connection.prepareStatement(query);

            if (query.startsWith("SELECT") || query.startsWith("select")) 
            {
                if(query.contains("?"))
                {
                    statment.setInt(1, NewJFrame.posicionClick);

                }
                ResultSet result = statment.executeQuery(query);
                return result;
            }
            else if (query.startsWith("INSERT")|| query.startsWith("insert")) 
            {
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                Integer i = preparedStatement.executeUpdate(query);
                preparedStatement.close();
                return i;
            }
            
            else if (query.startsWith("DELETE")|| query.startsWith("delete"))
            {
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                Integer i = preparedStatement.executeUpdate(query);
                preparedStatement.close();
                return i;
            }
            else if(query.startsWith("UPDATE") || query.startsWith("update"))
            {
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                Integer i = preparedStatement.executeUpdate(query);
                preparedStatement.close();
                return i;
            }



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null ,e);
        }
        return o;
    }


}
