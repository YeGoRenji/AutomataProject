package com.automataproj.automataproject.Metier;

import java.util.List;

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
    
    public boolean reconnaissanceMotUnion(String mot)
    {
        for (char c: mot.toCharArray()) {
            if (!alphabet.contains(c))
            {
                System.err.println("Un character n'existe pas dans l'alphabet !");
                return false;
            }
        }
        return (deltaStar(mot, etatsInit.get(0)).isFinalUnion());
    }
    public boolean reconnaissanceMotIntersect(String mot)
    {
        for (char c: mot.toCharArray()) {
            if (!alphabet.contains(c))
            {
                System.err.println("Un character n'existe pas dans l'alphabet !");
                return false;
            }
        }
        return (deltaStar(mot, etatsInit.get(0)).isFinalIntersect());
    }
    
    public AFD ComplementAFD() {   	
    	AFD complementM = new AFD();
    	complementM.setAlphabet(alphabet);
    	for (Etat e : this.etats)
    	{
    		complementM.ajouterEtat(e.getIdEtat(),getTypeComplement(e));
    	}
    	for (Etat e : this.etats)
    	{
    		e.getTransitionSortants().forEach((character, etatSortants) -> {
    			complementM.ajouterTransition(e.getIdEtat(), character, 
    					complementM.findEtat(etatSortants.get(0).getIdEtat()).getIdEtat());
    		});   
    	}
    	return complementM;
    }
 
	 private TypeEtat getTypeComplement(Etat etat) {
		 
		if(!etat.isFinal())
		{
			if(etat.getType()==TypeEtat.INIT)
				return TypeEtat.INIT_FINAL;
			return TypeEtat.FINAL;
		}
		else
		{
			if(etat.getType()==TypeEtat.INIT_FINAL)
				return TypeEtat.INIT;
			return TypeEtat.MID;
		}
	 }
	 
	 public AFD automataProduct(AFD primeM) {
		 AFD prodM = new AFD();
		 prodM.setAlphabet(alphabet);
		 for (Etat e : this.etats)
		 {
			 for (Etat pe : primeM.etats)
			 {
				 prodM.ajouterEtat(e.getIdEtat() + pe.getIdEtat(), getTypeProduct(e,pe));
			 }
		 }
		 for (Etat etat : this.etats)
		 {
			 for (Etat petat : primeM.etats)
			 {			 
				 etat.getTransitionSortants().forEach((character, etatSortants) -> {
					 for (Etat petatSortants : petat.getTransitionSortants().get(character))
		    			prodM.ajouterTransition(etat.getIdEtat() + petat.getIdEtat(), character, 
		    					prodM.findEtat(etatSortants.get(0).getIdEtat() + petatSortants.getIdEtat()).getIdEtat());
		    		});
			 }
			 
		 }
		 return prodM;
	 }
	 
	 private TypeEtat getTypeProduct(Etat e1, Etat e2) {
		 
		 if (e1.isInital() && e2.isInital())
		 {
			 if(e1.getType()==TypeEtat.INIT && e2.getType()==TypeEtat.INIT)
				 return TypeEtat.INIT;
			 if(e1.getType()==TypeEtat.INIT_FINAL && e2.getType()==TypeEtat.INIT_FINAL)
				 return TypeEtat.INIT_FINAL_UNION_INTERSECT;		 
			 return TypeEtat.INIT_FINAL; 
		 }
		 if (e1.isFinal() || e2.isFinal())
		 {
			 if(e1.getType()==TypeEtat.FINAL && e2.getType()==TypeEtat.FINAL)
				 return TypeEtat.FINAL_UNION_INTERSECT;
			 return TypeEtat.FINAL;
		 } 
		 return TypeEtat.MID;
	 }
   
}
