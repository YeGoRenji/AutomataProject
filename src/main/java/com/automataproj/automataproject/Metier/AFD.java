package com.automataproj.automataproject.Metier;

import java.lang.reflect.Type;
import java.util.*;

public class AFD extends AutomateFini {

    public AFD()
    {

    }

    public static List<Etat> delta(Character c, Etat q)
    {
        return q.getNextState(c);
    }

    public static Etat deltaStar(String word, Etat q)
    {
        List<Etat> stateList;
        while (!word.isEmpty())
        {
            char c = word.charAt(0);
            word = word.substring(1);

            stateList = delta(c, q);
            if (stateList.size() != 1)
            {
                System.err.println("Chemin non deterministe ! (Echec de reconnaisance)");
                return null;
            }
            q = stateList.get(0);
        }
        return q;
    }

    @Override
    public void ajouterEtat(String idEtat, TypeEtat type) {
        Etat etat = new Etat(idEtat, type);

        if (findEtat(idEtat) != null)
        {
            System.err.println("Etat deja existe !");
            return;
        }
        if (etat.isInital() && etatsInit.size() > 0)
        {
            System.err.println("Impossible d'avoir plus qu'une etat initiale pour AFD");
            return;
        }
        etats.add(etat);
        if (etat.isInital())
            etatsInit.add(etat);
        if (etat.isFinal())
            etatsFinal.add(etat);
    }

    @Override
    public void ajouterTransition(String idEtatDepart, Character c, String idEtatArrive)
    {
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
        if (etatDep.getNextState(c) != null)
        {
            System.err.println("AFD : Transition deja exist avec le symbole (" + c + ")  !");
            return;
        }
        etatDep.addTransition(c, etatArr);
    }

    public boolean reconnaissanceMot(String mot)
    {
        for (char c: mot.toCharArray()) {
            if (!alphabet.contains(c))
            {
                System.err.println("Un character n'existe pas dans l'alphabet !");
                return false;
            }
        }

        return (deltaStar(mot, etatsInit.get(0)).isFinal());
    }

    /*public int compterMotsAcceptes(AFD afd, int limite, Etat etatInitial) {
        int compteur = 0;
        Stack<Etat> etatsCourants = new Stack<>();
        etatsCourants.push(etatInitial);
        while (!etatsCourants.empty()) {
            Etat etatCourant = etatsCourants.pop();
            if (etatCourant.isFinal()) {
                compteur++;
            }
            if (limite > 0) {
                for (char symbole : afd.getAlphabet()) {
                    Etat etatSuivant = etatCourant.getNextState(symbole).get(0);
                        etatsCourants.push(etatSuivant);
                }
            }
                limite--;
        }
        return compteur;
    }
    */

    public List<String> generateAcceptedWords(AFD afd, int maxLength) {
        List<String> acceptedWords = new ArrayList<>();
        generateAcceptedWordsHelper(afd, afd.getEtatsInit().get(0), "", maxLength, acceptedWords);
        return acceptedWords;
    }

    private void generateAcceptedWordsHelper(AFD afd, Etat currentState, String currentWord, int maxLength, List<String> acceptedWords) {
        if (reconnaissanceMot(currentWord) && currentWord.length() <= maxLength) {
            acceptedWords.add(currentWord);
        }
        if (currentWord.length() < maxLength) {
            /*for (char c : afd.getAlphabet()) {
                Etat nextState = currentState.getNextState(c).get(0);
                generateAcceptedWordsHelper(afd, nextState, currentWord + c, maxLength, acceptedWords);}
                */
            currentState.getTransitionSortants().forEach((c, etatsSortants) -> {
                generateAcceptedWordsHelper(afd, etatsSortants.get(0) , currentWord + c, maxLength , acceptedWords);
            });

        }



    }




}
