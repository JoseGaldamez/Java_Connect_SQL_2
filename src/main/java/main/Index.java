
package main;

import data.Conexion;
import data.UserController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author jose_galdamez
 */

public class Index {

    public static void main(String[] args) throws SQLException {
        
        Connection connection = Conexion.getConnection();
        UserController userController = new UserController(connection);
        
        Scanner scanner = new Scanner(System.in);
        int optionSelected = 1;
        
        
        while (optionSelected != 0) { 
            System.out.println("\n\n_____________________________________");
            System.out.println("\nSeleccione una acción a realizar\n");
            System.out.println("1. Listar clientes");
            System.out.println("2. Ingresar nuevo cliente.");
            System.out.println("3. Actualizar un cliente existente.");
            System.out.println("4. Borrar un cliente.");
            System.out.println("0. Salir del sistema.");
            
            System.out.print("Su selección: ");
            optionSelected = scanner.nextInt();
            
            switch (optionSelected) {
                case 1 -> ActionsUser.selectAllUsers(userController);
                case 2 -> ActionsUser.createUser(userController, scanner);
                case 3 -> ActionsUser.updateClient(userController, scanner);
                case 4 -> ActionsUser.deleteClient(userController, scanner);
                case 0 -> System.out.println("[!] Saliendo del sistema...");
                default -> System.out.println("\n[!] Ingrese una opción del menu válida...\n\n");
            }
        }
        
    }
    
}
