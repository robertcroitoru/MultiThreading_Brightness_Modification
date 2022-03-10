
class Producer extends Thread{ // Thread producer - am urmat structura din curs
	private Transformare obj;
	
	public Producer( Transformare b) {obj = b;}

	@Override
	public void run() {	
		 for(int i = 0; i< 5; i++) {
	        	
	        	obj.put(i);
	System.out.println("S-a incarcat "+i+"/4 din imagine."); // pentru fiecare parte, afisam mesaj
	 if (i == 4){System.out.println("Imaginea a fost citita in intregime"); }// in momentul in care se proceseaza ultima parte, afisam un mesaj corespunzator
      
	
			try {
				sleep (( int )(Math.random () * 100) );
			} catch (InterruptedException e) { }

	}
	
}
}