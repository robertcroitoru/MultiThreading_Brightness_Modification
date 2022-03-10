import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class ClasaAbs2 extends ClasaAbs1 {
	
	@Override
	public void descriere() //metoda descriere, nu returnam nimic, doar afisam mesajul 
	{
		System.out.println("Aceasta este clasa abstracta numarul 2 - Al doilea nivel de mostenire.");
	}
	
	@Override
	public void fun(String str, int ...x) // clasa care contine numar variabile de argumente (varargs)
    { 
        System.out.println("Sirul este: " + str); //dam sigur
        System.out.println("Numarul de argumente al sirului: "+ x.length); 
        
        for (int i = 0; i < x.length; i++) {
            System.out.print(i + " "); // afisam fiecare argument
        }    
        System.out.println(); 
    }
	
	@Override //subclass or child class to provide a specific implementation of a method that is already provided by one of its super-classes or parent classes.
	public boolean readImageFromFile(String file) { //metoda 
		BufferedImage image = null; //image de tip Buffered image, fara valoare (null)
		try (FileInputStream stream = new FileInputStream(file)) {
			image = ImageIO.read(stream); // citim din fisier in variabila image de tip BuffereadImage
		} catch (FileNotFoundException e) {
			System.out.println("Nu am putut gasi fisierul " + file);// tratam exceptiile in care nu se gaseste fisierul / apare o eroare in fisier
			return false;
		} catch (IOException e) {
			System.out.println("A aparut o eroare in timpul citirii din fisier");
			return false;
		}
		return true;
	}
	
	abstract public void writeImageToFile(String file); // metoda de scriere a imaginii in fisier
}
