package test;

import java.util.ArrayList;
import java.util.List;

import com.automataproj.automataproject.Metier.AFD;
import com.automataproj.automataproject.Metier.TypeEtat;

public class Test {

	public static void main(String[] args) {
		AFD auto = new AFD();
		List <Character> alphabet = new ArrayList<Character>();
		alphabet.add('a');
		alphabet.add('b');
		auto.setAlphabet(alphabet);
		auto.ajouterEtat("s0", TypeEtat.INIT_FINAL);
		auto.ajouterEtat("s1", TypeEtat.FINAL);
		auto.ajouterEtat("s2", TypeEtat.FINAL);
		auto.ajouterTransition("s0", 'a', "s0");
		auto.ajouterTransition("s0", 'b', "s2");
		auto.ajouterTransition("s1", 'a', "s0");
		auto.ajouterTransition("s1", 'b', "s1");
		auto.ajouterTransition("s2", 'b', "s1");
		auto.ajouterTransition("s2", 'a', "s1");
		System.out.println(auto.brzozowski());

	}

}
