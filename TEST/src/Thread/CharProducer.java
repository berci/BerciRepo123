/**
 * 
 */
package Thread;

/**
 * Egy olyan osztály, ymi egy szálat valósít meg (leszármazottja a
 * <code>Thread</code> osztálynak). Öszesen csak annyit csinál, hogy
 * karaktereket állít elõ, amit majd a másik szál felhasznál. <br>
 * Minden <code>Thread</code> leszármazott a <code>run</code> metódusában
 * valósítja meg a logikáját, tehát abban kell lekódolni a szükséges dolgokat. A
 * szálat elindító <code>start</code> metódus ezután használni fogja õt.
 * 
 * @author tajti
 * 
 */
public class CharProducer extends Thread {

	/**Ebbe a pufferbe fog a szál karaktereket tenni.*/
	private CharBuffer buffer;
	
	public CharProducer(CharBuffer buffer){
		this.buffer = buffer;
	}
	
	/**
	 * A szülõben definiált metódus, amit itt újraimplementálunk. A szál
	 * indulásakor fut le. Egy szál életében csak egyszer hívódik meg. 
	 * <br>
	 * Itt annyit tesz a metódus, hogy véletlenszerûen elõállít számokat,
	 * azokból karaktereket és kiírja õket a szabványos kimenetre. Ezt addig
	 * ismétli, amíg a szál él.
	 */
	public void run() {
		//ez egy vélelen ciklus lez, azaz csak akkor áll le, ha a szál "leáll"
		while (true) {
			//altatjuk a szálat. erre azért van szükség, hogy ne "maradjon" le a fogyasztó
			try{
				Thread.sleep(600);
			}catch(InterruptedException ie){
				interrupt(); //visszaállítjuk az interrupted flaget
			}
			
			// elõállítunk egy 0 és 26 közé esõ véletlenszámot
			int rand = (int) (Math.random() * 26);

			// ebbõl készítünk egy karaktert
			char c = (char) ((int) 'a' + rand);

			//elhelyezzük a pufferbe
			buffer.add(c);
			
			// kiírjuk a kimenetre
			System.out.println("produced: " + c);
			
			/* Ezzel a metódussal ellenõrizhetjük, hogy az aktuális szálra
			 * (tehát amibõl a metódust meghívjuk) mehívták-e az interrupt 
			 * metódust. Ha igen, akkor true értéket ad vissza, és eldönthetjük,
			 * hogy mit teszünk. Általában megszakítjuk a run futását, mint
			 * ahogy ebben a példában  is tesszük.
			 * Ha az interrupted metódust kétszer hívjuk meg úgy, hogy
			 * a két hívás között senki nem hívta meg a szálra az interruptot,
			 * akkor false értéket ad vissza, tehát a flage törtlõdik.
			 * Mivel az InterruptedException kiváltódása is törli a flaget, ezért
			 * ennek csak akkor van értelme, ha a fenti kivételkezelõ blokk
			 * lefutása után meghívták az interrupt metódust.*/
			if(Thread.interrupted()){
				System.out.println("CharProducer: megszakítottak");
				return;
			}
		}
	}

}
