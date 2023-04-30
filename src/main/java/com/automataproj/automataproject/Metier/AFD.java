package com.automataproj.automataproject.Metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    
    
    public AFD minimiser()
    {
    	AFD af = new AFD();
    	af.setAlphabet(this.alphabet);
    	//Elimination des Etat Inaccessibles:
    	Stack <Etat> q = new  Stack<Etat>();
    	q.addAll(this.etats);
    	//System.out.println(q.size());
    	for(Etat etat: this.etats)
    	{
    		for(Character symbole: this.alphabet)
    		{
    			List<Etat> suivant = etat.getNextState(symbole);
    			if(suivant!=null&&q.containsAll(suivant)) q.removeAll(suivant);
    		}
    	}
    	List <Etat> etats=this.differennce(this.etats, q);
    	af.setEtats(etats);
    	
    	
    	//division:
    	List <Etat> etatsFinaux = af.etatsFinal();
    	List <Etat> etatNonFinaux = af.differennce(af.etats, etatsFinaux);
    	List <List<Etat>> partition = new ArrayList <List<Etat>>();
    	partition.add(etatNonFinaux);
    	partition.add(etatsFinaux);
    	List <List<Etat>> partition1 = new ArrayList <List<Etat>>();
    	for(Character symbole: af.alphabet)
    	{
    		for(List<Etat> part: partition) {
    			List<Etat> precedent = trouverPartition(partition, part.get(0).getNextState(symbole).get(0));
    			List<Etat> lista = new ArrayList<Etat>();
    			for(Etat etat : part)
    			{
    				if(trouverPartition(partition, etat.getNextState(symbole).get(0))!= precedent&& etat!=null)
    				{
    					lista.add(etat);
    				}
    			}
    			if(!lista.isEmpty())
    				partition1.add(lista);
    			
    		}
    	}
    	for(List<Etat> part:partition1)
    	{
    		for(Etat etat: part)
    		{
    			
    			List<Etat>	partiti = trouverPartition(partition, etat);
    			partiti.remove(etat);
    		}
    		partition.add(part);
    	}
    	//Reassemblage:
    	//Creation des etat:
    	af.etats.clear();
    	for (List<Etat> part : partition) {
            Etat nouvelEtat = new Etat(genererId(),TypeEtat.MID);
            int init=0;
            int fin=0;
            for(Etat etat : part)
            {
            	if(etat.isInital()) {
            		init++;
            	}
            	if(etat.isFinal())
            	{
            		fin++;
            	}
            	
            }
            if(init==1)
            {
            	if(fin>1) nouvelEtat.setType(TypeEtat.INIT_FINAL);
            	else nouvelEtat.setType(TypeEtat.INIT);
            }
            else {
            	if(fin>1) nouvelEtat.setType(TypeEtat.FINAL);
                
            }
            af.etats.add(nouvelEtat);
            System.out.println(init+" "+fin);
            
        }
    	
    	//creation des transition:
    	int i=0;
    	for(Etat etat:af.etats)
    	{
    		for(Character symbole: af.alphabet)
    		{
    			int index = partition.indexOf(trouverPartition(partition, partition.get(i).get(0).getNextState(symbole).get(0)))+1;
    			af.ajouterTransition(etat.getIdEtat(), symbole,"q"+index );
    		}
    		i++;
    	}
    	
    	
        
    	
    	return af;
    }

    
    private List<Etat> trouverPartition(List <List<Etat>> partitions,Etat etat)
    {
    	for (List<Etat> partition : partitions) {
            if (partition.contains(etat)) {
                return partition;
            }
        }
        return null;
    }
    
   
    
   



}
