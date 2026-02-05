package petshop;
import java.io.*;
import java.util.*;

public class GestorClientes {

    private static final String RUTA_ARCHIVO = "clientes.txt";
    private Map<String, Duenio> clientes; // DNI → Duenio

    public GestorClientes() {
        clientes = new HashMap<>();
        cargarClientes();
    }

    public Duenio getClientePorDni(String dni) {
        return clientes.get(dni);
    }

    public void agregarCliente(Duenio d) {
        clientes.put(d.getDni(), d);
        guardarClientes();
    }

    public void registrarCompra(String dni) {
        Duenio d = clientes.get(dni);
        if (d != null) {
            d.incrementarCompras();
            guardarClientes();
        }
    }

    private void cargarClientes() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    String dni = datos[0];
                    String nombre = datos[1];
                    String apellido = datos[2];
                    int numCompras = Integer.parseInt(datos[3]);

                    Duenio d = new Duenio(nombre, apellido, dni);
                    for (int i = 0; i < numCompras; i++) {
                        d.incrementarCompras();
                    }

                    clientes.put(dni, d);
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error al leer el archivo de clientes: " + e.getMessage());
        }
    }

    private void guardarClientes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            for (Duenio d : clientes.values()) {
                bw.write(d.getDni() + ";" + d.getNombre() + ";" + d.getApellido() + ";" + d.getNumCompras());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error al guardar el archivo de clientes: " + e.getMessage());
        }
    }

    public Collection<Duenio> getTodosLosClientes() {
        return clientes.values();
    }
}

