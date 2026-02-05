package poker;
public class Carta {

    private int valor;          // 1-13 (A=1, J=11, Q=12, K=13)
    private String palo;        // "Corazones", "Tréboles", etc.

    public Carta(int valor, String palo) {
        this.valor = valor;
        this.palo = palo;
    }

    public int getValor() {
        return valor;
    }

    public String getPalo() {
        return palo;
    }

    public void mostrarInfoCarta() {
        String nombreValor;

        switch (valor) {
            case 1:  nombreValor = "As"; break;
            case 11: nombreValor = "Jota"; break;
            case 12: nombreValor = "Reina"; break;
            case 13: nombreValor = "Rey"; break;
            default: nombreValor = String.valueOf(valor); break;
        }
        switch(palo) {
        case "Corazones":
        	palo="\033[31m"+palo+"\033[37m";
        	break;
        case "Picas":
        	palo="\033[34m"+palo+"\033[37m";
        	break;
        case "Tréboles":
        	palo="\033[34m"+palo+"\033[37m";
        	break;
        case "Diamantes":
        	palo="\033[31m"+palo+"\033[37m";
        	break;
        }
        System.out.println("===================");
        System.out.println("CARTA");
        System.out.println("===================");
        System.out.println("Palo: "+palo);
        System.out.println("-------------------");
        System.out.println("Valor: "+nombreValor);
        System.out.println("-------------------");
        System.out.println();
    }
}
