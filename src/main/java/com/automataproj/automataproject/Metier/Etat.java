package com.automataproj.automataproject.Metier;

import java.util.*;

public class Etat {
    private String idEtat;

    private TypeEtat type;

    private HashMap<Character, List<Etat>> transitionSortants;

//    private HashMap<>
    public Etat(String idEtat, TypeEtat type) {
        this.idEtat = idEtat;
        this.type = type;
        transitionSortants = new HashMap<>();
    }

    public void addTransition(Character symbol, Etat etatSuivante)
    {
        List<Etat> etats;
        if (transitionSortants.containsKey(symbol)) {
            if (transitionSortants.get(symbol).contains(etatSuivante))
            {
                System.err.println("Même transition déja Existe !");
                return;
            }
            transitionSortants.get(symbol).add(etatSuivante);
            return;
        }
        etats = new ArrayList<>();
        etats.add(etatSuivante);
        transitionSortants.put(symbol, etats);
    }

    public List<Etat> getNextState(Character symbol)
    {
        if (symbol == '\0')
            return null;
        return transitionSortants.get(symbol);
    }

    public String getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(String idEtat) {
        this.idEtat = idEtat;
    }

    public TypeEtat getType() {
        return type;
    }

    public void setType(TypeEtat type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String str = "State {" + '\n' +
                "   idEtat= '" + idEtat + '\'' + ",\n" +
                "   type= " + type + ",\n" +
                "   transitionSortants= { ";
        for (Character c: transitionSortants.keySet()) {
            str += '\n';
            List<Etat> states = transitionSortants.get(c);
            str +=  "      " + '\'' + c + '\'' + ": ";
            for (Etat state: states) {
                str += state.getIdEtat() + ", ";
            }
        }
        if (transitionSortants.isEmpty())
            str += "}\n}";
        else
            str += "\n   }\n}";
        return str;
    }

    public boolean isFinal()
    {
        return (type == TypeEtat.FINAL || type == TypeEtat.INIT_FINAL);
    }

    public boolean isInital() {
        return  (type == TypeEtat.INIT || type == TypeEtat.INIT_FINAL);
    }
}
