package postOffice;

import java.util.ArrayList;
import java.util.List;

public class GestionPaquetes {
    private List<Paquete> paquetes;

    public GestionPaquetes() {
        paquetes = new ArrayList<>();
    }

    public boolean agregarPaquete(Paquete paquete) {
        // Verificar que no exista un paquete con el mismo ID
        for (Paquete p : paquetes) {
            if (p.getIdPackage() == paquete.getIdPackage()) {
                return false; // ID duplicado
            }
        }
        paquetes.add(paquete);
        return true;
    }

    public boolean eliminarPaquete(long idPackage) {
        for (Paquete p : paquetes) {
            if (p.getIdPackage() == idPackage) {
                paquetes.remove(p);
                return true;
            }
        }
        return false;
    }

    public Paquete buscarPaquete(long idPackage) {
        for (Paquete p : paquetes) {
            if (p.getIdPackage() == idPackage) {
                return p;
            }
        }
        return null;
    }

    public boolean existePaquete(long idPackage) {
        return buscarPaquete(idPackage) != null;
    }

    public List<Paquete> listarPaquetes() {
        return new ArrayList<>(paquetes); // Devuelve una copia
    }

    public int contarPaquetes() {
        return paquetes.size();
    }

    public void limpiarPaquetes() {
        paquetes.clear();
    }
}