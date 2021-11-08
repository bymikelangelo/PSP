package inicial;

public class UsaHilo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HiloSimple2 hs2 = new HiloSimple2();
		Thread t = new Thread(hs2);
		t.start();

		for (int i = 0; i < 5; i++) {
			System.out.println("Estoy fuera del hilo... " + i);
		}

	}

}
