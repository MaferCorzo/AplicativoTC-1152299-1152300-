/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.*;

/**
 *
 * @author Alison Martinez
 */
public class Lenguaje {

    private List<String> noTerminales = new ArrayList<>();
    private List<String> terminales = new ArrayList<>();
    private String inicial;
    private HashMap<String, ArrayList<String>> tr = new HashMap<>();
    private Set<String> palabras = new HashSet<>();
    private int cantidad;
    private int max;

    public Lenguaje() {

    }

    public Lenguaje(List<String> noTerminales, List<String> terminales, HashMap<String, ArrayList<String>> tr, String inicial) {
        this.noTerminales = noTerminales;
        this.terminales = terminales;
        this.tr = tr;
        this.inicial = inicial;
        this.cantidad = 0;
        this.max = 0;
    }

    public ArrayList<String> generadorDePalabras(int maxDepth, int cantidad) {
        Set<String> processed = new HashSet<>();
        while (getPalabras().size() < cantidad) {
            getPalabras().add(recursiv(getTr(), getNoTerminales(), getInicial(), maxDepth, processed));
        }
        return new ArrayList<>(getPalabras());
    }

    public String recursiv(HashMap<String, ArrayList<String>> produc1, List<String> noTerminales, String variable, int maxDepth, Set<String> processed) {
        // Verificar si hemos alcanzado la profundidad máxima permitida
        if (maxDepth <= 0) {
            return "";
        }

        if (!produc1.containsKey(variable)) {
            return variable;
        }

        List<String> produccionesVariable = produc1.get(variable);
        String random = produccionesVariable.get(new Random().nextInt(produccionesVariable.size()));
        StringBuilder gramatica = new StringBuilder();
        for (char f : random.toCharArray()) {
            if (esTerminal(Character.toString(f))) { // Verificar si el carácter es un terminal
                gramatica.append(f);
            } else if (noTerminales.contains(Character.toString(f))) {
                // Llamada recursiva con profundidad reducida
                String result = recursiv(produc1, noTerminales, Character.toString(f), maxDepth - 1, processed);
                gramatica.append(result);
            }
        }
        return gramatica.toString();
    }

    public boolean esTerminal(String letra) {
        for (String elemento : getTerminales()) {
            if (elemento.equals(letra)) {
                return true;
            }
        }
        return false;
    }

    public boolean esNoTerminal(String letra) {
        for (String elemento : getNoTerminales()) {
            if (elemento.equals(letra)) {
                return true;
            }
        }
        return false;
    }

    // debe tener un terminal
    public boolean reglasConTerminal(ArrayList<String> producciones) {
        boolean hayTerminal = false;

        for (String select : producciones) {
            if (esTerminal(select)) {
                //System.out.println("Hay un terminal");
                hayTerminal = true;
            }
        }
        return hayTerminal;
    }

    //inutiles
    /*public ArrayList<String> variableInutil(){
    ArrayList<String> inutiles = new ArrayList<>();

    for (String select : getNoTerminales()) {
        boolean todasReglasInutiles = true;

        ArrayList<String> producciones = getTr().get(select);

        if (producciones.isEmpty()) {
            inutiles.add(select);
            //System.out.println("El noTerminal '" + select + "' no tiene producciones.");
            continue; 
        }
        
        for (String regla : producciones) {
            boolean reglaValida = false;

            for (char c : regla.toCharArray()) {
                String letra = Character.toString(c);
                if (esNoTerminal(letra) || esTerminal(letra)) {
                    reglaValida = true; 
                    break;
                }
            }

            if (reglaValida) {
                todasReglasInutiles = false; 
                break;
            }
        }

        if (todasReglasInutiles) {
            inutiles.add(select);
            //System.out.println("El noTerminal '" + select + "' tiene reglas inútiles.");
        }
    }

    return inutiles; //este metodo retorna las variables que son completamente inutiles.
}*/
    public ArrayList<String> variableInutil() {
        ArrayList<String> inutiles = new ArrayList<>();

        for (String select : getNoTerminales()) {
            boolean valido = false; // Reiniciamos la bandera para cada noTerminal

            ArrayList<String> producciones = getTr().get(select);

            if (producciones.isEmpty()) {
                inutiles.add(select);
                continue; // Pasamos al siguiente noTerminal
            }

            for (String regla : producciones) {
                boolean reglaValida = false;

                for (char c : regla.toCharArray()) {
                    String letra = Character.toString(c);
                    if (esNoTerminal(letra) || esTerminal(letra)) {
                        reglaValida = true; // La regla contiene al menos un terminal o noTerminal, es válida
                        break;
                    }
                }

                if (!reglaValida) {
                    inutiles.add(select);
                    System.out.println("El noTerminal '" + select + "' tiene reglas inútiles.");
                    valido = true;
                    break; // No necesitamos seguir verificando las producciones de este noTerminal
                }
            }
        }

        return inutiles;
    }

    //--------------------------------------------------------------------------
    // inalcanzables
    public ArrayList<String> inalcanzable() {
        ArrayList<String> inalcanzables = new ArrayList<>();
        Set<String> noTerminalesAlcanzados = new HashSet<>();

        noTerminalesAlcanzados.add(getInicial());

        for (String select : getNoTerminales()) {

            ArrayList<String> producciones = getTr().get(select);
            if (producciones != null) {

                for (String regla : producciones) {

                    for (char c : regla.toCharArray()) {
                        //System.out.println("variables--" + c);
                        String letra = Character.toString(c);
                        if (esNoTerminal(letra)) {
                            noTerminalesAlcanzados.add(letra);
                        }

                    }
                }
            }
        }
        for (String noAlcanzada : getNoTerminales()) {
            if (!noTerminalesAlcanzados.contains(noAlcanzada)) {
                inalcanzables.add(noAlcanzada);
            }
        }
        return inalcanzables;
    }

    //--------------------------------------------------------------------------
    /**
     * @return the noTerminales
     */
    public List<String> getNoTerminales() {
        return noTerminales;
    }

    /**
     * @param noTerminales the noTerminales to set
     */
    public void setNoTerminales(List<String> noTerminales) {
        this.noTerminales = noTerminales;
    }

    /**
     * @return the terminales
     */
    public List<String> getTerminales() {
        return terminales;
    }

    /**
     * @param terminales the terminales to set
     */
    public void setTerminales(List<String> terminales) {
        this.terminales = terminales;
    }

    /**
     * @return the inicial
     */
    public String getInicial() {
        return inicial;
    }

    /**
     * @param inicial the inicial to set
     */
    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    /**
     * @return the tr
     */
    public HashMap<String, ArrayList<String>> getTr() {
        return tr;
    }

    /**
     * @param tr the tr to set
     */
    public void setTr(HashMap<String, ArrayList<String>> tr) {
        this.tr = tr;
    }

    /**
     * @return the palabras
     */
    public Set<String> getPalabras() {
        return palabras;
    }

    /**
     * @param palabras the palabras to set
     */
    public void setPalabras(Set<String> palabras) {
        this.palabras = palabras;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

}
