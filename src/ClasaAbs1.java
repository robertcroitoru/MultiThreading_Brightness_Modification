
public abstract class ClasaAbs1 //clasa abstracta
{
	
	public void descriere()
	{
		System.out.println("Aceasta este clasa abstracta numarul 1- Primul nivel de mostenire");
	}
	
	abstract public boolean readImageFromFile(String file);  //metoda abstracta
	
	abstract public void fun(String str, int ...x); //metoda abstracta cu varargs
}
