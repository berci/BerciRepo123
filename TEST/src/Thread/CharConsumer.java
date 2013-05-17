/**
 * 
 */
package Thread;

/**
 * Másik módja a "szálosztály" készítésének. Implementáljuk a <code>Runnable</code>
 * interfészt, annak <code>run</code> metódusát. Ha kész az osztály, akkor létrehozhatunk
 * egy <code>Thread<code> objektumot, ami ennek az osztálynak egy példányát fogja futtatni
 * a szál indulásakor.
 * <br>
 * Ez módszer elválasztja a futtatandó logikát az õt futtató száltól, és kevésbé korlátozó,
 * mint a <code>Thread</code>-tõl való származtatás. 
 * 
 * @author tajti
 *
 */
public class CharConsumer implements Runnable {

	/**Ebbõl a pufferbõl veszi majd a szál a karaktereket*/
	CharBuffer buffer;
	
	public CharConsumer(CharBuffer buffer){
		this.buffer = buffer;
	}
	
	/**Karaktereket vesz a pufferbõl és kiírja õket a szabványos
	 * kimenetre.*/
	@Override
	public void run() {
		//ez egy vélelen ciklus lez, azaz csak akkor áll le, ha a szál "leáll"
		while (true) {
			//kiveszünk egy karaktert a pufferbõl, ha van
			char c = buffer.get();
			if(c != 0)
				System.out.println("consumed: " + c);
			
			/* Itt kezelni kell az InterruptedException ellenõrzött kivételt.
			 * Ez akkor váltódik ki, ha a szálra meghívják az interrupt
			 * metódust, miközben az alvó állapotban van. 
			 * Ilyen esetekben mi dönthetünk arról, hogy mit teszünk,
			 * ha meghívódik az interrupt. Általában a szálat
			 * terminálni szokták, és ebben a kódban is ez történik (return
			 * utasítás), de másra is lehetõségünk van.*/
			try {
				//pihentetjük a szálat, hogy követhetõ legyen a kiíratás
				Thread.sleep(600);
			} catch (InterruptedException e) {
				System.out.println("CharConsumer: megszakítottak");
				return;
			}
		}

	}

}
