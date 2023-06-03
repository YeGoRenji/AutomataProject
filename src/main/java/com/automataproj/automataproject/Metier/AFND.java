package com.automataproj.automataproject.Metier;
import java.util.*;

public class AFND extends AutomateFini {

    public AFND()
    {
    }

    @Override
    public String ajouterEtat(String idEtat, TypeEtat type) {
        Etat etat = new Etat(idEtat, type);

        if (findEtat(idEtat) != null)
            return "Etat deja existe !";
        etats.add(etat);
        if (etat.isInital())
            etatsInit.add(etat);
        if (etat.isFinal())
            etatsFinal.add(etat);

        return null;
    }

    @Override
    public String ajouterTransition(String idEtatDepart, Character c, String idEtatArrive) {
        Etat etatDep = findEtat(idEtatDepart);
        Etat etatArr = findEtat(idEtatArrive);

        if (etatArr == null || etatDep == null)
            return "L'un des etat inexsitante !";
        if (!alphabet.contains(c) && c != '\0')
            return "Character n'appartient pas au alphabet d'AFND";
        return etatDep.addTransition(c, etatArr);
    }


    public AFD determiniser() {

        List<Etat> etatsInitiauxList = this.getEtatsInit();
        List<Etat> etatsInitiaux = new ArrayList<>(etatsInitiauxList);



        System.out.println(getCollectionName(etatsInitiaux));


        AFD afd = new AFD();
        afd.setAlphabet(this.getAlphabet());


        HashMap<String , Set<Etat>> idCollection= new HashMap<>();
        Set<Etat> EtatsCollection;
        Set<Etat> nextstates;
        afd.ajouterEtat(getCollectionName(etatsInitiaux), getTypeCollection(etatsInitiaux,TypeEtat.INIT));
        idCollection.put(getCollectionName(etatsInitiaux), new HashSet<>(etatsInitiaux));


        for (Etat etatCourant : this.getEtats()) {

            etatCourant.getTransitionSortants().forEach((symbole, etatsAccessibles) ->{


                Etat nouvelEtat = afd.getEtatParId(getCollectionName(etatsAccessibles));
                if (nouvelEtat == null) {

                    nouvelEtat = new Etat();
                    nouvelEtat.setIdEtat(getCollectionName(etatsAccessibles));
                    afd.ajouterEtat(nouvelEtat.getIdEtat(), getTypeCollection(etatsAccessibles,TypeEtat.MID));
                    idCollection.put(nouvelEtat.getIdEtat(), new HashSet<>(etatsAccessibles));
                }
            });

        }
        for (Etat etatCourant : afd.getEtats())
        {
            EtatsCollection = idCollection.get(etatCourant.getIdEtat());

            for(Character symbol : afd.getAlphabet()){
                nextstates=new HashSet<>();
                for(Etat etatCollectionCourant : EtatsCollection){
                    if (etatCollectionCourant.getNextState(symbol)!=null)
                        nextstates.addAll(etatCollectionCourant.getNextState(symbol));
                }
                afd.ajouterTransition(etatCourant.getIdEtat(), symbol, HashmapReverse(nextstates,idCollection));

            }
        }

            return afd;
    }

    // Cette méthode calcule l'epsilon-fermeture d'un état donné
    /*public Set<Etat> epsilonFermeture(Etat etat) {
        Set<Etat> epsilonFermeture = new HashSet<>();
        epsilonFermeture.add(etat);
        for (Etat etatSuivant : etat.getNextState(AutomateFini.EPSILON)) {
            if (!epsilonFermeture.contains(etatSuivant)) {
                epsilonFermeture.addAll(epsilonFermeture(etatSuivant));
            }
        }
        return epsilonFermeture;
    }*/

    public String getCollectionName(List<Etat> etats){
        String s = new String();
        s += "{";
        for (Etat etat:etats)
        {
            s += etat.getIdEtat() + ",";
        }
        s=s.substring(0,s.length()-1);
        s+="}";

        return s;
    }

    public String HashmapReverse(Set<Etat> valeur , HashMap<String, Set<Etat>> idCollection ){

        for (Map.Entry<String, Set<Etat>> entry : idCollection.entrySet())
        {
            if(valeur.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }

    public TypeEtat getTypeCollection(List<Etat> etats, TypeEtat defaultType)
    {
        boolean isFinal=false;
        for(Etat etat : etats)
        {
            if(etat.isFinal())
                isFinal=true;
        }
        if((defaultType == TypeEtat.INIT) && isFinal)
            return TypeEtat.INIT_FINAL;
        if(isFinal)
            return TypeEtat.FINAL;
        return defaultType;
    }
}
