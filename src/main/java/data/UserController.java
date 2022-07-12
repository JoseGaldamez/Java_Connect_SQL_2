package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 * @author jose_galdamez
 */

public class UserController {
    
    private Connection conexion;
    
    public UserController(Connection conexion){
        this.conexion = conexion;
    }
    
    public List<User> getUsersFromDB(){
        
       
        // esta de personas que devolveremos 
        // que por el momento está vacia
        List<User> users = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM users";
        
        // Al ser una consulta externa al programa debemos ponerlo en un try-catch
        // si la base dedatos no está corriendo esto nos causara errores,
        // por eso Java exige que se haga dentro de un manejador de errores.
        try {
            // aquí llamamamos al metodo getConnection de la clase Connexion que está en el paquete datos
            Connection conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            // prepareando la información
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
            // objeto con el resultado de la consulta
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // recorremos las respuestas
            // el ciclo hará tantas iteracones como elementos tenga nuestra tabla
            while (resultSet.next()) {
                
                // creamos un elemento de la clase Person que está en el paquete domain
                User user = new User(
                                    resultSet.getInt("id_user"),
                                    resultSet.getString("name"),
                                    resultSet.getString("last_name"),
                                    resultSet.getString("age"),
                                    resultSet.getString("email"),
                                    resultSet.getString("phone"));
                
                // el objeto creado lo agregamos a la lista de personas                
                users.add(user);
                
                //Si hay más elementos el ciclo se repite y se crea otra persona y se agrega a la lista
            }
            
        } catch (SQLException ex) {
            // Si existe algún error lo mostramos por consola
            System.out.println(ex);
        }
       
        // regresamos la lista de personas a cualquier objeto que llamé este método obtenerClientes()
        return users;
        
    }
    
    public boolean deleteUser(int userIdSelected ){
        
        String SQL_DELETE = "DELETE FROM users WHERE id_user=?";
    
        try {
            Connection conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            
            
            preparedStatement.setInt(1, userIdSelected);
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
     
    }
    
    public boolean updateUser(User userSelected ){
        
        
        String SQL_UPDATE = "UPDATE users SET name=?, last_name=?, age=?, email=?, phone=? WHERE id_user=?";
    
        try {
            Connection conn = Conexion.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
            
            preparedStatement.setString(1, userSelected.getName());
            preparedStatement.setString(2, userSelected.getLast_name());
            preparedStatement.setString(3, userSelected.getAge());
            preparedStatement.setString(4, userSelected.getEmail());
            preparedStatement.setString(5, userSelected.getPhone());
            
            preparedStatement.setInt(6, userSelected.getId_user());
            
            
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
            
        } 
    }
    
    public boolean createNewUser(User user){
        
        String SQL_INSERT = "INSERT INTO users (name, last_name, age, email, phone) VALUES (?, ?, ?, ?, ?)";
    
        try {
            Connection conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            
            
            preparedStatement.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
            
        }
    }
    
    
}
