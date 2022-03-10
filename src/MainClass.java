import java.io.IOException;
import java.util.*;

public class MainClass {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in); // citim de la tastatura

		try {
			while (true) {
				Transformare reader = new Transformare(); // cream un obiect de tip Transformare

				boolean imageRead = false;
				do {
					System.out.println("Introduceti calea catre fisierul sursa SAU \"exit\" pentru a iesi");
					String line = scanner.nextLine(); // Se citeste calea catre fisierul de intrare

					if ("exit".equals(line)) { // in cazul in care dam exit de la tastatura, se iese automat din program
						System.out.println("S-a terminat algoritmul.");
						return;
					}
					
					imageRead = reader.readImageFromFile(line); // citim imaginea data de la tastatura
				} while (!imageRead);

				reader.transform(); // se aplica algoritmul de Brightness Modification si se face conversia

				System.out.println("Dati calea catre fisier destinatie");

				String line = scanner.nextLine();
				reader.writeImageToFile(line); // Luam calea spre fisierul destinatie de la tasatura

				Producer t1 = new Producer(reader); // sincronizam cele doua threaduri 
				Consumer t2 = new Consumer(reader);
				t1.start();
				t2.start();

			}
		} finally {
			scanner.close();
		}
	}
}
