package postOffice;

import java.util.HashMap;

public class Autenticacion {
    private HashMap<String, String> usuarios;

    public Autenticacion() {
        usuarios = new HashMap<>();
        inicializarUsuarios();
    }

    private void inicializarUsuarios() {
        usuarios.put("root", "root");
        usuarios.put("admin", "admin123");
        usuarios.put("client", "client");
    }

    public boolean autenticar(String nombre, String contraseña) {
        String passAlmacenada = usuarios.get(nombre);
        return passAlmacenada != null && passAlmacenada.equals(contraseña);
    }

    public boolean esAdmin(String nombre) {
        return nombre.equals("root") || nombre.equals("admin");
    }

    public void agregarUsuario(String nombre, String contraseña) {
        usuarios.put(nombre, contraseña);
    }

    public boolean existeUsuario(String nombre) {
        return usuarios.containsKey(nombre);
    }
}