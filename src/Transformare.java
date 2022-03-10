import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Transformare { // clasa transformare - se citeste imaginea din fisier, se face conversia, se scrie imaginea in fisier

	private BufferedImage image = null;

	private int number = -1; // aici am facut Bufferul sincronizat, pentru a sincroniza Producer si Consumer
	private boolean available = false;

	public synchronized int get() {
		while (!available) {
			try {
				wait();// Asteapta producatorul sa puna o valoare
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		available = false;
		notifyAll();
		return number;
	}

	public synchronized void put(int number) {
		while (available) {
			try {
				wait();// Asteapta consumatorul sa preia valoarea
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.number = number;
		available = true;
		notifyAll();
	}


	public boolean readImageFromFile(String file) { // Specificand calea catre imagine, se citeste din fisier
		long time = System.currentTimeMillis(); // // Incepe contorizeaza timpul actual al sistemului
		try (FileInputStream stream = new FileInputStream(file)) { // se asteapta introducerea de la tastatura a imaginii 
			//In 'Image' de tip BufferedImage vom stoca poza, fapt pentru care i-am atribuit la inceput valoarea null
			image = ImageIO.read(stream);
		} catch (FileNotFoundException e) { //In caz ca nu este gasit fisierul sau apare alta eroare, se arunca erori
			System.out.println("Nu am putut gasi fisierul " + file);
			return false;
		} catch (IOException e) {
			System.out.println("A aparut o eroare in timpul citirii din fisier");
			return false;
		}

		time = System.currentTimeMillis() - time; // Timpului de citire al imaginii citita de la tastatura
		System.out.println("Citirea din fisier a durat " + time / 1000.0f + " secunde"); // Afisam timpul de citire al imaginii
		return true;
	}

	// Aplicam algoritmul de conversie propriu zisa.

	public void transform() throws IOException {
		long time = System.currentTimeMillis(); //Incepe contorizeaza timpul actual al sistemului

		int width = image.getWidth(); // se obtine latimea imaginii
		int height = image.getHeight(); // se obtine lunfimea imaginii

		int factorlumin = 100; // pentru a modifica luminozitatea imayinii dupa preferinte, schimbam factorul

		// Se obtine RGB-ul imaginii
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color c = new Color(image.getRGB(x, y));
				// adding factor to rgb values
				int red = c.getRed() + factorlumin;
				int blue = c.getBlue() + factorlumin;
				int green = c.getGreen() + factorlumin;
				if (red >= 256) {
					red = 255;
				} else if (red < 0) {
					red = 0;
				}
				if (green >= 256) {
					green = 255;
				} else if (green < 0) {
					green = 0;
				}
				if (blue >= 256) {
					blue = 255;
				} else if (blue < 0) {
					blue = 0;
				}
				image.setRGB(x, y, new Color(red, green, blue).getRGB());

			}
		}

		// Se calculeaza timpul necesar procesarii imaginii si se afiseaza
		time = System.currentTimeMillis() - time;

		System.out.println("Transformarea imaginii a durat " + time / 1000.0f + " secunde");
	}

	public void writeImageToFile(String file) { // scrierea imaginii in fisierul dat de la tastatura
		long time = System.currentTimeMillis();

		try (FileOutputStream stream = new FileOutputStream(file)) {
			ImageIO.write(image, "BMP", stream); // precizam ca trebuie in format bmp
		} catch (FileNotFoundException e) { // aruncam exceptii in cazul in care nu se gaseste calea sau nu se poate deschide fisierul
			System.out.println("Calea data " + file + " nu este valida ! ");
			return;
		} catch (IOException e) {
			System.out.println("A aparut o eroare in timpul scrierii in fisier");
			return;
		}

	
		time = System.currentTimeMillis() - time; // calculam timpul necesar scrierii in fisierul destinatie
		System.out.println("Scrirea in fisier a durat " + time / 1000.0f + " secunde"); // il afisam
	}

}
