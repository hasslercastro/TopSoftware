package patronesSW;

import patronesSW.bd.DAOManagerSQL;
import patronesSW.clases.Cliente;
import patronesSW.clases.Factura;
import patronesSW.clases.tipoItem;
import patronesSW.dao.ClienteDAO;
import patronesSW.dao.DAOException;
import patronesSW.dao.tipoItemDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static DAOManagerSQL manager;
    static Scanner sc;


    public static void funcionCliente() throws DAOException {
        ClienteDAO cd = manager.getClienteDAO();
        System.out.println("1. Añadir cliente" +
                "2. Eliminar cliente" +
                "3. Actualizar Cliente" +
                "4. Listar Clientes" +
                "5. Listar (1) cliente" +
                "6. Salir");

        int opcion = sc.nextInt();

        switch (opcion){

            case 1:
                Cliente clienteAgregar = conseguirCliente();
                cd.insertar(clienteAgregar);
                break;
            case 2:
                System.out.println("Va a eliminar un cliente, inserte id del cliente");
                int idC = sc.nextInt();
                cd.eliminar(idC);
                break;
            case 3:
                System.out.println("Va actualizar el cliente");
                System.out.println("Ingrese el Id del cliente");
                int id = sc.nextInt();
                Cliente c = conseguirCliente();
                c.setId(id);
                cd.modificar(c);
                break;
            case 4:
                System.out.println("listando todos los clientes");
                for(Cliente cliente : cd.obtenerTodos()){
                    System.out.println(cliente.toString());
                }
                break;
            case 5:
                System.out.println("Ingrese el Id del cliente a listar");
                int idCliente = sc.nextInt();
                System.out.println(cd.obtener(idCliente).toString());
                break;
            case 6:
                break;
        }
    }

    public static Cliente conseguirCliente(){
        System.out.println("Va agregar un cliente");
        System.out.println("Ingrese nombres");
        String nombre = sc.nextLine();
        System.out.println("Ingrese Apellidos");
        String apellido = sc.nextLine();
        System.out.println("Ingrese Genero");
        String genero = sc.nextLine();
        System.out.println("Ingrese Fecha de Nacimiento");
        String fechaNac = sc.nextLine();
        System.out.println("Ingrese estado civil");
        String estadoCivil = sc.nextLine();
        Cliente cliente = new Cliente(nombre,apellido,genero, new Date(fechaNac), estadoCivil);
        return cliente;
    }

    public static void functionTipoItem() throws DAOException {
        tipoItemDAO tipi = manager.getTipoItemDAO();
        System.out.println("1. Añadir tipo item" +
                "2. Eliminar tipo item" +
                "3. Actualizar tipo item" +
                "4. Listar tipos de item" +
                "5. Listar (1) tipo de item especificado por id" +
                "6. Salir");

        int opcion = sc.nextInt();

        switch (opcion){

            case 1:
                tipoItem ti = conseguirTipoItem();
                tipi.insertar(ti);
                break;
            case 2:
                System.out.println("Va a eliminar un tipo de item, inserte id del tipo de item");
                int idC = sc.nextInt();
                tipi.eliminar(idC);
                break;
            case 3:
                System.out.println("Va actualizar el tipo de item");
                System.out.println("Ingrese el Id del tipo de item");
                int id = sc.nextInt();
                tipoItem c = conseguirTipoItem();
                c.setId(id);
                tipi.modificar(c);
                break;
            case 4:
                System.out.println("listando todos los tipos de item");
                for(tipoItem t : tipi.obtenerTodos()){
                    System.out.println(t.toString());
                }
                break;
            case 5:
                System.out.println("Ingrese el Id del tipo de item a listar");
                int idTipoItem = sc.nextInt();
                System.out.println(tipi.obtener(idTipoItem).toString());
                break;
            case 6:
                break;
        }



    }

    public static tipoItem conseguirTipoItem(){
        sc.nextLine(); //botar backslash n
        System.out.println("Escriba la descripcion del tipo de item");
        String desc = sc.nextLine();
        tipoItem tipoItem = new tipoItem(desc);
        return tipoItem;
    }

    public static void main(String[] args) throws SQLException, DAOException {

        manager = new DAOManagerSQL("localhost", "root", "1234", "swfacturas");
        sc = new Scanner(System.in);

        /*

        List<Integer> it = new ArrayList<>();
        it.add(1);
        it.add(2);
        Factura f = new Factura(new Date("11/2/1998") , 2, 2000, "Pagada", it);
        f.setNroFactura(1);
        manager.getFacturaDAO().insertar(f);
        */

        System.out.println("Bienvenido al sistema TIENDA, elija una opcion:" +
                "1. Cliente" +
                "2. Tipo de Item" +
                "3. Item" +
                "4. Factura" +
                "5. Salir");
        int opcion = sc.nextInt();
        sc.nextLine(); //botar salto de linea

        switch (opcion){
            case 1:
                funcionCliente();
                break;
            case 2:
                functionTipoItem();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Elija correctamente ");

        }
    }

}
