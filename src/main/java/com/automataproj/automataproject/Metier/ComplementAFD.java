//package com.automataproj.automataproject.Metier;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ComplementAFD {
//
//	 public ComplementAFD(AFD M) {
//	    	// Changer le type des états 
//	    	List<Etat> complementEtats = new ArrayList<>();
//	    	complementEtats = deepCopy(M.etats);
//	    	List<Etat> complementEtatsInit = new ArrayList<>();
//	    	List<Etat> complementEtatsFinal = new ArrayList<>();
//	    	for(Etat etat : complementEtats)
//	    	{
//	    		if(!etat.isFinal())
//	    		{
//	    			if(etat.getType()==TypeEtat.INIT)
//	    			{
//	    				etat.setType(TypeEtat.INIT_FINAL);
//	    				complementEtatsInit.add(etat);
//	    				complementEtatsFinal.add(etat);
//	    			}
//	    			else
//	    			{
//	    				etat.setType(TypeEtat.FINAL);
//		    			complementEtatsFinal.add(etat);
//	    			}
//	    		}
//	    		else
//	    		{
//	    			if(etat.getType()==TypeEtat.INIT_FINAL)
//	    			{
//	    				etat.setType(TypeEtat.INIT);
//	    				complementEtatsInit.add(etat);
//	    			}
//	    			else
//	    				etat.setType(TypeEtat.MID);
//	    		}
//	    	}
//	    	AFD complementM = new AFD(M.alphabet, complementEtats, complementEtatsInit, complementEtatsFinal);
//	    	System.out.println(complementM.etats);
//	    	System.out.println(M.etats);
//	    }
//	 
//	 private List<Etat> deepCopy(List<Etat> originalList) {
//		 List<Etat> copyList = new ArrayList<>();
//		 for (Etat e : originalList) {
//			 Etat etatCopy = new Etat(e.getIdEtat(),e.getType());
//			 etatCopy.setTransitionSortants(e.getTransitionSortants());
//			 copyList.add(etatCopy);
//		 }
//		 return copyList;
//	 }
//
//}
