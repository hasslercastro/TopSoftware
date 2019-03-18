package patronesSW;

import patronesSW.bd.DAOManagerSQL;
import patronesSW.clases.Cliente;
import patronesSW.clases.Factura;
import patronesSW.clases.Items;
import patronesSW.clases.tipoItem;
import patronesSW.dao.*;

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

    public static void funcionItem() throws DAOException {
        ItemsDAO itemsD = manager.getItemsDAO();
        System.out.println("1. Añadir item" +
                "2. Eliminar item" +
                "3. Actualizar item" +
                "4. Listar items" +
                "5. Listar (1) item especificado por id" +
                "6. Salir");

        int opcion = sc.nextInt();

        switch (opcion){

            case 1:
                Items ti = conseguirItem();
                itemsD.insertar(ti);
                break;
            case 2:
                System.out.println("Va a eliminar un item, inserte id del item");
                int idC = sc.nextInt();
                itemsD.eliminar(idC);
                break;
            case 3:
                System.out.println("Va actualizar un item");
                System.out.println("Ingrese el Id del item");
                int id = sc.nextInt();
                Items c = conseguirItem();
                c.setIdItem(id);
                itemsD.modificar(c);
                break;
            case 4:
                System.out.println("listando todos los tipos de item");
                for(Items t : itemsD.obtenerTodos()){
                    System.out.println(t.toString());
                }
                break;
            case 5:
                System.out.println("Ingrese el Id del tipo de item a listar");
                int idItem = sc.nextInt();
                System.out.println(itemsD.obtener(idItem).toString());
                break;
            case 6:
                break;
        }

    }

    public static Items conseguirItem(){

        System.out.println("Ingrese id del tipo de item");
        int idTipoItem = sc.nextInt();
        sc.nextLine();
        System.out.println("Inserte descripcion del item");
        String desc = sc.nextLine();
        System.out.println("Inserte valor de la unidad x item");
        int valor = sc.nextInt();
        sc.nextLine();
        Items items = new Items(idTipoItem, desc, valor);
        return items;

    }

    public static void funcionFactura() throws DAOException {
        FacturaDAO facturaDAO = manager.getFacturaDAO();
        System.out.println("1. Añadir Factura" +
                "2. Eliminar Factura" +
                "3. Actualizar Factura" +
                "4. Listar Factura" +
                "5. Listar (1) Factura especificada por id" +
                "6. Salir");

        int opcion = sc.nextInt();

        switch (opcion){

            case 1:
                Factura f = conseguirFactura();
                facturaDAO.insertar(f);
                break;
            case 2:
                System.out.println("Va a eliminar una factura, inserte id de la factura");
                int idC = sc.nextInt();
                facturaDAO.eliminar(idC);
                break;
            case 3:
                System.out.println("Va actualizar una factura");
                System.out.println("Ingrese el Id de la factura");
                int id = sc.nextInt();
                Factura c = conseguirFactura();
                c.setNroFactura(id);
                facturaDAO.modificar(c);
                break;
            case 4:
                System.out.println("listando todas las facturas");
                for(Factura t : facturaDAO.obtenerTodos()){
                    System.out.println(t.toString());
                }
                break;
            case 5:
                System.out.println("Ingrese el ID de la factura a listar");
                int idFact = sc.nextInt();
                System.out.println(facturaDAO.obtener(idFact).toString());
                break;
            case 6:
                break;
        }


    }

    public static Factura conseguirFactura(){
        sc.nextLine();
        System.out.println("Ingrese fecha");
        String date = sc.nextLine();
        System.out.println("Ingrese id del cliente");
        int idcliente = sc.nextInt();
        System.out.println("Ingrese el valor total factura");
        sc.nextLine();
        int valorFactura = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese el estado de factura");
        String estado = sc.nextLine();
        System.out.println("Ingrese el numero de items");
        int nItems = sc.nextInt();
        sc.nextLine();
        List<Integer> li = new ArrayList<>();
        int d ;
        for(int i = 1 ; i <= nItems ; i++){
            System.out.println("Ingrese id del item numero " + i );
             d  = sc.nextInt();
             li.add(d);
        }

        Factura factura = new Factura(new Date(date), idcliente, valorFactura, estado, li);
        return factura;

    }

    public static void main(String[] args) throws SQLException, DAOException {

        manager = new DAOManagerSQL("localhost", "root", "1234", "swfacturas");
        sc = new Scanner(System.in);
/*

        List<Integer> it = new ArrayList<>();
        it.add(1);
        it.add(2);
        Factura f = new Factura(new Date("11/2/1998"), 2, 2000, "Pagada", it);

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
                funcionItem();
                break;
            case 4:
                funcionFactura();
            case 5:
                break;
            default:
                System.out.println("Elija correctamente ");

        }
    }
}


