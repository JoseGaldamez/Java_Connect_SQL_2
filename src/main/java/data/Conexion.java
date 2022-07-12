
package data;

/**
 * @author jose_galdamez
 */

// importaciones necesarias, recuerda revisar porque en ocaciones se hacen de forma incorrecta
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


// Esta clase será la encargada de crear la conexión con nuestra base de datos en MY SQL.
public class Conexion {
    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db_uth_programacion_1"; // asumiendo que su base de datos se llama "test"
    private static final String JDBC_USER = "jose_galdamez";
    //private static final String JDBC_USER = "<USUARIO_MYSQL>";
    private static final String JDBC_PASSWORD = "Magodeoz1991";
    //private static final String JDBC_PASSWORD = "<PASSWORD_MYSQL>";
    
    
    // Este método va a devolver una conexión creada con los datos antes dados.
    public static Connection getConnection() throws SQLException{
        // retornar esta conexión nos permite poder usar la conexión en cualquier lugar
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    
    
    // methods to close all the opened objects.
    // esto es necesario ya que no estamos trabajando con un Singleton.
    // cada vez que llamamos el método getConnection() se crea una nueva, 
    // por eso hay que cerrarla al terminar
    public static void closeResultSet(ResultSet resultSet) throws SQLException{
        resultSet.close();
    }
    
    public static void closeStatement(PreparedStatement preparedStatement ) throws SQLException{
        preparedStatement.close();
    }
    
    public static void closeConnection(Connection connection) throws SQLException{
        connection.close();
    }
    
    
}
