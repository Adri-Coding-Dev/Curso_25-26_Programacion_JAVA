package main;

import connection.ConfigDB;
import connection.ConnexionBBDD;
import dao.BusDAO;
import dao.ConductorDAO;
import dao.LugarDAO;
import model.Bus;
import model.Conductor;
import model.Lugar;
import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase MAIN donde se orquesta el programa
 */
public class Main {
    public static void main (String[]args){
        //Flujo principal del programa
        try {
            //Intentamos cargar el conector(necesario para la conexion) y tramitamos la conexion a la BBDD
            ConnexionBBDD conector = new ConnexionBBDD();
            Connection con = conector.getConnection();
            //Cartel de Bienvenida con el nombre de la BBDD (Con expresiones regulares)
            Utils.mostrarOpcionElegida("CONEXION A LA BASE DE DATOS: "+ Utils.sacarNombreBBDD());
            boolean activo = true;
            int registrosAlterados = 0;
            //Bucle while para trabajar en bucle
            while(activo){
                switch(Utils.menu()){
                    //Usuario se sale
                    case 0:
                        System.out.println("Saliendo....");
                        activo = false;
                        break;

                        //Mostrar los Buses
                    case 1:
                        Utils.mostrarOpcionElegida("MOSTRAR TODOS LOS BUSES");
                        List<Bus> buses = BusDAO.mostrarTodosLosBuses(con);
                        //Mostrar informacion de todos los buses
                        if(!buses.isEmpty()){
                            for(Bus b : buses){
                                System.out.println(b.toString());
                                System.out.println("_".repeat(100));
                            }
                        }else{
                            System.out.println("Registros vacios, nada que mostrar");
                        }
                        break;

                        //Insertar BUS
                    case 2:
                        Utils.mostrarOpcionElegida("INSERTAR BUS");
                        if(BusDAO.insertarBus(con)){
                            System.out.println("Consulta realizada. Bus Insertado correctamente");
                        }else{
                            System.out.println("Error al insertar el Bus");
                        }
                        break;

                        //Borrar Bus
                    case 3:
                        Utils.mostrarOpcionElegida("BORRAR BUS (ID)");
                        registrosAlterados = BusDAO.borrarBus(con, Utils.pedirCadenaAlUsuario("Introduce el ID del Bus que quieras borrar: "));
                        System.out.println("Consulta realizada. Registros alterados: "+registrosAlterados);
                        break;

                        //Editar Bus
                    case 4:
                        Utils.mostrarOpcionElegida("EDITAR BUS (ID)");
                        registrosAlterados = BusDAO.editarBus(con,Utils.pedirCadenaAlUsuario("Introduce el ID del Bus que quieras modificar: "));
                        System.out.println("Consulta realiza. Registros alterados: "+ registrosAlterados);
                        break;

                        //Buscar Bus
                    case 5:
                        Utils.mostrarOpcionElegida("BUSCAR BUS (ID)");
                        Bus bus = BusDAO.buscarBus(con,Utils.pedirCadenaAlUsuario("Introduce el ID del Bus que quieras buscar: "));
                        if(bus ==null){
                            System.out.println("No hay nada para mostrar");
                        }else{
                            System.out.println(bus.toString());
                        }
                        break;

                        //Mostrar todos los conductores
                    case 6:
                        Utils.mostrarOpcionElegida("MOSTRAR TODOS LOS CONDUCTORES");
                        List<Conductor> conductores = ConductorDAO.mostrarTodosLosConductores(con);
                        //Mostrar la informacion de todos los conductores
                        if(!conductores.isEmpty()) {
                            for (Conductor c : conductores) {
                                System.out.println(c.toString());
                                System.out.println("_".repeat(100));
                            }
                        }else{
                            System.out.println("Registros Vacios. Nada que mostrar");
                        }
                        break;

                        //Insertar Conductor
                    case 7:
                        Utils.mostrarOpcionElegida("INSERTAR CONDUCTOR");
                        if(ConductorDAO.insertarConductor(con)){
                            System.out.println("Consulta realizada. Registros alterados: 1");
                        }else{
                            System.out.println("Error al insertar el Conductor");
                        }
                        break;

                        //Borra Conductor
                    case 8:
                        Utils.mostrarOpcionElegida("BORRAR CONDUCTOR (ID)");
                        registrosAlterados = ConductorDAO.borrarConductor(con, Utils.pedirNumeroAlUsuario("Introduce el ID del Bus que quieras borrar: "));
                        if(registrosAlterados>0){
                            System.out.println("Consulta realizada. Registros alterados: "+registrosAlterados);
                        }else{
                            System.out.println("ID no identificado. Error al ejecutar la consulta");
                        }
                        break;

                        //Editar Conductor
                    case 9:
                        Utils.mostrarOpcionElegida("EDITAR CONDUCTOR (ID)");
                        registrosAlterados = ConductorDAO.editarConductor(con,Utils.pedirNumeroAlUsuario("Introduce el ID del Bus que quieras modificar: "));
                        System.out.println("Consulta realiza. Registros alterados: "+ registrosAlterados);
                        break;

                        //Buscar Conductor
                    case 10:
                        Utils.mostrarOpcionElegida("BUSCAR UN CONDUCTOR (ID)");
                        Conductor cond = ConductorDAO.buscarConductor(con,Utils.pedirNumeroAlUsuario("Introduce el ID del Bus que quieras buscar: "));
                        System.out.println(cond.toString());
                        break;

                        //Mostrar todos los lugares
                    case 11:
                        Utils.mostrarOpcionElegida("MOSTRAR TODOS LOS LUGARES");
                        List<Lugar> lugares = LugarDAO.mostrarTodosLosLugares(con);
                        //Mostrar la informacion de todos los lugares
                        for(Lugar lugar : lugares){
                            System.out.println(lugar.toString());
                            System.out.println("_".repeat(100));
                        }
                        break;

                        //Insertar Lugar
                    case 12:
                        Utils.mostrarOpcionElegida("INSERTAR UN LUGAR");
                        registrosAlterados = LugarDAO.insertarLugar(con);
                        System.out.println("Consulta realizada. Registros Modificados: "+registrosAlterados);
                        break;

                        //Borrar Lugar
                    case 13:
                        Utils.mostrarOpcionElegida("BORRAR UN LUGAR (ID)");
                        registrosAlterados = LugarDAO.borrarLugar(con,Utils.pedirNumeroAlUsuario("Introduce el ID del lugar que quieras borrar: "));
                        System.out.println("Consulta realizada. Registros Modificados: "+registrosAlterados);
                        break;

                        //Editar Lugar
                    case 14:
                        Utils.mostrarOpcionElegida("EDITAR UN LUGAR (ID)");
                        registrosAlterados = LugarDAO.editarLugar(con,Utils.pedirNumeroAlUsuario("Introduce el ID del Lugar que quieras modificar: "));
                        System.out.println("Consulta realiza. Registros alterados: "+ registrosAlterados);
                        break;

                        //Buscar Lugar
                    case 15:
                        Utils.mostrarOpcionElegida("BUSCAR UN LUGAR (ID)");
                        Lugar lugar = LugarDAO.buscarLugar(con,Utils.pedirNumeroAlUsuario("Introduce el ID del Lugar que quieras buscars"));
                        System.out.println(lugar.toString());
                        break;

                    default:
                        System.out.println("Opcion no valida...");
                }
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
