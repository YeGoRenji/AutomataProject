package com.automataproj.automataproject.Metier;

import java.util.ArrayList;
import java.util.List;

public abstract class AutomateFini {
    protected String idAutomate;

    protected List<Character> alphabet;

    protected List<Etat> etats;

    protected List<Etat> etatsInit;

    protected List<Etat> etatsFinal;

    public AutomateFini()
    {
        etats = new ArrayList<>();
        etatsInit = new ArrayList<>();
        etatsFinal = new ArrayList<>();
        alphabet = new ArrayList<>();
    }

    public void setAlphabet(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    protected Etat findEtat(String idEtat)
    {
        for (Etat e: etats) {
            if (e.getIdEtat().equals(idEtat))
                return e;
        }
        return null;
    }

    public abstract void ajouterEtat(String idEtat, TypeEtat type);
    public abstract void ajouterTransition(String idEtatDepart, Character c, String idEtatArrive);
}
