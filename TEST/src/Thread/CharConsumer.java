/**
 * 
 */
package Thread;

/**
 * M�sik m�dja a "sz�loszt�ly" k�sz�t�s�nek. Implement�ljuk a <code>Runnable</code>
 * interf�szt, annak <code>run</code> met�dus�t. Ha k�sz az oszt�ly, akkor l�trehozhatunk
 * egy <code>Thread<code> objektumot, ami ennek az oszt�lynak egy p�ld�ny�t fogja futtatni
 * a sz�l indul�sakor.
 * <br>
 * Ez m�dszer elv�lasztja a futtatand� logik�t az �t futtat� sz�lt�l, �s kev�sb� korl�toz�,
 * mint a <code>Thread</code>-t�l val� sz�rmaztat�s. 
 * 
 * @author tajti
 *
 */
public class CharConsumer implements Runnable {

	/**Ebb�l a pufferb�l veszi majd a sz�l a karaktereket*/
	CharBuffer buffer;
	
	public CharConsumer(CharBuffer buffer){
		this.buffer = buffer;
	}
	
	/**Karaktereket vesz a pufferb�l �s ki�rja �ket a szabv�nyos
	 * kimenetre.*/
	@Override
	public void run() {
		//ez egy v�lelen ciklus lez, azaz csak akkor �ll le, ha a sz�l "le�ll"
		while (true) {
			//kivesz�nk egy karaktert a pufferb�l, ha van
			char c = buffer.get();
			if(c != 0)
				System.out.println("consumed: " + c);
			
			/* Itt kezelni kell az InterruptedException ellen�rz�tt kiv�telt.
			 * Ez akkor v�lt�dik ki, ha a sz�lra megh�vj�k az interrupt
			 * met�dust, mik�zben az alv� �llapotban van. 
			 * Ilyen esetekben mi d�nthet�nk arr�l, hogy mit tesz�nk,
			 * ha megh�v�dik az interrupt. �ltal�ban a sz�lat
			 * termin�lni szokt�k, �s ebben a k�dban is ez t�rt�nik (return
			 * utas�t�s), de m�sra is lehet�s�g�nk van.*/
			try {
				//pihentetj�k a sz�lat, hogy k�vethet� legyen a ki�rat�s
				Thread.sleep(600);
			} catch (InterruptedException e) {
				System.out.println("CharConsumer: megszak�tottak");
				return;
			}
		}

	}

}
