package inicial;

public class UsaHilo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HiloSimple hs = new HiloSimple();
		hs.start();

		for (int i = 0; i < 5; i++) {
			System.out.println("Estoy fuera del hilo... " + i);
		}

	}

}
