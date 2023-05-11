package com.automataproj.automataproject.Metier;

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
}
