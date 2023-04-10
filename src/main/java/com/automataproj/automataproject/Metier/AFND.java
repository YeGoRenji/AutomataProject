package com.automataproj.automataproject.Metier;

public class AFND extends AutomateFini {

    public AFND()
    {

    }

    @Override
    public void ajouterEtat(String idEtat, TypeEtat type) {
        Etat etat = new Etat(idEtat, type);

        etats.add(etat);
        if (etat.isInital())
            etatsInit.add(etat);
        if (etat.isFinal())
            etatsFinal.add(etat);
    }

    @Override
    public void ajouterTransition(String idEtatDepart, Character c, String idEtatArrive) {
        Etat etatDep = findEtat(idEtatDepart);
        Etat etatArr = findEtat(idEtatArrive);

        if (etatArr == null || etatDep == null)
        {
            System.err.println("L'un des etat inexsitante !");
            return;
        }
        if (!alphabet.contains(c))
        {
            System.err.println("Character n'appartient pas au alphabet d'AFD");
            return;
        }
        etatDep.addTransition(c, etatArr);
    }
}
