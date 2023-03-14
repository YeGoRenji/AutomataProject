package com.automataproj.automataproject.Metier;

import java.util.List;

public class AFD extends AutomateFini {

    public static List<Etat> delta(String s, Etat q)
    {
        return q.getNextState(s);
    }

    // TODO : I have to fix the fact that alphabet are Strings
//    public static Etat deltaStar(String word, Etat q)
//    {
//        List<Etat> stateList;
//        while (!word.isEmpty())
//        {
//            char c = word.charAt(0);
//            word = word.substring(1);
//
//            stateList = delta(s, q);
//            if (stateList.size() != 1)
//            {
//                System.err.println("Chemin non deterministe ! (Echec de reconnaisance)");
//                return null;
//            }
//            q = stateList.get(0);
//        }
//        return q;
//    }
}
