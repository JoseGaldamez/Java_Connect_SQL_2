package main;

import data.UserController;
import java.util.List;
import java.util.Scanner;
import models.User;

/**
 * @author jose_galdamez
 */
public class ActionsUser {
    
    
    public static void selectAllUsers(UserController userController){
        List<User> users = userController.getUsersFromDB();
        
        
        System.out.println("\nLista de usuarios");
        System.out.println("______________________________________________________");
        
        for(User user: users){
            System.out.println(user.toString());
        }
    }
    
    public static void deleteClient( UserController userController, Scanner scanner ){
        
        System.out.println("Seleccione un registro para actualizar");
        System.out.println("________________________________________");
        selectAllUsers(userController);
        
        System.out.print("Id del cliente seleccionado: ");
        int userIdSelected = scanner.nextInt();
    
        System.out.print("\n\n¿Está seguro de querer eliminar el usuario?\n1. Si\n2. No\n\nSu respuesta: ");
        int borrar = scanner.nextInt();
        if (borrar == 1) {
            
        if(userController.deleteUser(userIdSelected)){
        
            System.out.println("Registro borrar exitosamente.");
            
        } else {
            System.out.println("Algo salio mal.");
        }
        } else {
            System.out.println("\nSaliendo...\nVolviendo al menú...");
        }
        
    }
    
    public static void updateClient( UserController userController, Scanner scanner ){
        
        System.out.println("Seleccione un registro para actualizar");
        System.out.println("________________________________________");
        selectAllUsers(userController);
        
        System.out.print("Id del cliente seleccionado: ");
        int userIdSelected = scanner.nextInt();
    
        
        System.out.print("Ingrese el nombre: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        
        System.out.print("Ingrese el apellido: ");
        String lastname = scanner.nextLine();
        
        System.out.print("Ingrese el age: ");
        String age = scanner.nextLine();
        
        System.out.print("Ingrese el email: ");
        String email = scanner.nextLine();
        
        System.out.print("Ingrese el phone: ");
        String phone = scanner.nextLine();
        
        User persona = new User(userIdSelected, name, lastname, age, email, phone);
        
        if(userController.updateUser(persona)){
        
            System.out.println("Registro se actualizó exitosamente.");
            
        } else {
            System.out.println("Algo salio mal.");
        }
    }
    
    
    public static void createUser( UserController userController, Scanner scanner ){
    
        boolean addUser = true;
        
        
        while(addUser){
            
            System.out.print("Ingrese el nombre: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            System.out.print("Ingrese el apellido: ");
            String lastname = scanner.nextLine();

            System.out.print("Ingrese el age: ");
            String age = scanner.nextLine();

            System.out.print("Ingrese el email: ");
            String email = scanner.nextLine();

            System.out.print("Ingrese el phone: ");
            String phone = scanner.nextLine();

            User persona = new User(0, name, lastname, age, email, phone);

            if(userController.createNewUser(persona)){

                System.out.println("\nRegistro ingresado exitosamente.");

            } else {
                System.out.println("\nAlgo salio mal.");
            }
            
            int otherUser = 0;
            
            while(otherUser!=1 && otherUser != 2){
                System.out.print("\nIngresar otro usuario\n1. SI\n2. No\nSu elección: ");
                otherUser = scanner.nextInt();


                if(otherUser == 2){
                    addUser = false;
                    userController.commitData();
                }
            }
            
            
            
        }
        
    }
    
}
