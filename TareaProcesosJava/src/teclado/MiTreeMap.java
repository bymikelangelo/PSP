package teclado;

import java.util.Comparator;
import java.util.TreeMap;

public class MiTreeMap<K, V> extends TreeMap<K, V>{

	public MiTreeMap() {
		super();
	}
		
	public MiTreeMap(Comparator<? super K> comparator) {
		super(comparator);
	}
		
	@Override
	public String toString() {
		String cadena = "Mostrando " + super.size() + " elementos: \n"; 
		for (K i: super.keySet()) {
			cadena = cadena  + i.toString() + " " + super.get(i) + "\n";
		}
		return cadena;
		
		/*
		Set<Entry<K, V>> datos = super.entrySet();
		for(Entry<K, V> dato: datos) {
			System.out.println(dato.getKey() + " - " dato.getValue());
		}
		*/

	}

		
}

