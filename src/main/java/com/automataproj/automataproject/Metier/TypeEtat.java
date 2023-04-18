package com.automataproj.automataproject.Metier;

public enum TypeEtat {
    INIT,
    MID,
    FINAL, // nous aide encore pour déterminer la réunion des automates
    INIT_FINAL, // nous aide encore pour déterminer la réunion des automates
    
    // Pour l'union et l'intersection des automates
    INIT_FINAL_UNION_INTERSECT,
	FINAL_UNION_INTERSECT
}
