package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

    private final String immatriculation;
    private final List<Stationnement> myStationnements = new LinkedList<>();
    private final List<Garage> garages;

    public Voiture(String i) {
        this.garages = new LinkedList<>();
        if (null == i) {
            throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
        }

        immatriculation = i;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Fait rentrer la voiture au garage Précondition : la voiture ne doit pas
     * être déjà dans un garage
     *
     * @param g le garage où la voiture va stationner
     * @throws java.lang.Exception Si déjà dans un garage
     */
    public void entreAuGarage(Garage g) throws Exception {
        if (this.estDansUnGarage()) {
            throw new IllegalArgumentException("la voiture est deja garée quelque part");
        } // Et si la voiture est déjà dans un garage ?
        else {
            Stationnement s = new Stationnement(this, g);
            this.myStationnements.add(s);
            this.garages.add(g);

        }

    }

    /**
     * Fait sortir la voiture du garage Précondition : la voiture doit être dans
     * un garage
     *
     * @throws java.lang.Exception si la voiture n'est pas dans un garage
     */
    public void sortDuGarage() throws Exception {
        if (!this.estDansUnGarage()) {
            throw new IllegalArgumentException("la voiture n'est pas encore garée dans un garage");
        } else {
            Stationnement s = myStationnements.get(this.myStationnements.size() - 1);
            s.terminer();
        }

    }

    /**
     * @return l'ensemble des garages visités par cette voiture
     */
    public List<Garage> garagesVisites() {
        return garages;
    }

    /**
     * @return vrai si la voiture est dans un garage, faux sinon
     */
    public boolean estDansUnGarage() {
        if (!this.myStationnements.isEmpty()) {
            if (myStationnements.get(myStationnements.size() - 1).estEnCours()) {
                return true;
            }
        }
        return false;

    }

    /**
     * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste
     * des dates d'entrée / sortie dans ce garage
     * <br>Exemple :
     * <pre>
     * Garage Castres:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     *		Stationnement{ entree=28/01/2019, en cours }
     *  Garage Albi:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     * </pre>
     *
     * @param out l'endroit où imprimer (ex: System.out)
     */
    public void imprimeStationnements(PrintStream out) {
        for (Garage g : garages) {
            out.println(g.toString() + ":");
            for (Stationnement s : myStationnements) {
                if (g.equals(s.getGarage())) {
                    out.println("\t" + s.toString()); //\t = retour à la ligne
                }
            }
        }

    }

}
