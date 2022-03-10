class Consumer extends Thread { //Thread Consumer - am urmat structura din curs
	private Transformare obj; // Obiect de tip Transformare

	public Consumer(Transformare b) {
		obj = b;
	}

	@Override
	public void run() { 
		int value = 0;
		for (int i = 0; i < 5; i++) { // i-ul merge de la 0-4, pentru a imparti procesarea in 4 parti.
			value = obj.get();
			System.out.println("Parte din imagine " + value + "/4  procesata si adaugata cu succes!");//afisam mesjaul imediat dupa ce producerul pune valoarea sa
		}

	}

}
