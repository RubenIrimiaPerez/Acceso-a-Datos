package main;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Bienvenido");
		int i = 0;
		int j = 1;
		int [] arrayInts = {1,5,10,-2,13,88};
		ArrayList<String> listStrings = new ArrayList<String>();
		
		Persona pers1 = new Persona ("A",27,1.8);
		Persona pers2 = new Persona ("B",47,1.65);
		pers1=pers2;
		
		
		if(pers1==pers2) {
			System.out.println("Las personas son iguales");
		}
		
		for (int x = 1 ; x<=10;x++){
			System.out.println(x);
			
		}
		
		for (int x = 0 ; x<arrayInts.length;x++){
			System.out.println(arrayInts[x]);
			
		}
		
		for( int elemento:arrayInts) {
			System.out.println(elemento);
		}
		
		
		

	
		
	}

}
