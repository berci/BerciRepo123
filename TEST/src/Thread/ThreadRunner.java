package Thread;

/*
 * Ez az  osztály szálakat futtat.
 * <br>
 * Egy azálat futtatni a <code>start</code> metódussal lehet. Ezt a 
 * metódust csak egyszer lehet meghívni egy szálra, csak akkor, amikor az
 * még <code>Thread.State.NEW</code> állapotban van. Minden más állapot esetén
 * ha megkíséreljük meghívni a metódust, akkor <code>IllegalThreadStateException</code>
 * kivételt kapnánk. 
 * <br>
 * Egy szál a következõ állapotok valamelyikében lehet:
 * <ul>
 * <li><code>Thread.State.NEW</code>: minden olyan szál, ami példányosítás után
 * még nem lett elindítva, ebben az állpotban van. Az ilyen állapotú szálakra lehet meghívni
 * a <code>start</code>metódust.</li>
 * <li><code>Thread.State.RUNNABLE</code>: a futó szálak állapota</li>
 * <li><code>Thread.State.BLOCKED</code>: azok a szálak vannak ebben az áallapotban, amik egy
 * zár feloldására várnak.</li>
 * <li><code>Thread.State.WAITING</code>: ha egy szál arra vár (meg nem határozott ideig), hogy
 * egy másik szál végezzen egy feladat végrehajtásával, akkor ebben az állapotban van.</li>
 * <li><code>Thread.State.TIMED_WAITNG</code>: ugyanaz, mint az elõzõ, csak itt meg
 * van határozva a várakozási idõ.</li>
 * <li><code>Thread.State.TERMINATED</code>: a leállított szálak ebben az állapotban vannak.</li>
 * </ul>
 * 
 * @author tajti
 *
 */
public class ThreadRunner {

	public static void main(String[] args){
		CharBuffer b = new CharBuffer();
		CharProducer th1 = new CharProducer(b);
		Thread th2 = new Thread(new CharConsumer(b));
		th1.start();
		th2.start();
		//runThreads(th1, th2);
		
		/* Ezt nem hívhatjuk meg újra:
		 * 
		 * th1.start();
		 * 
		 * Ha megpróbálnánk, akkor IllegalStateException kivételt kapnánk,
		 * mert a th1 szál már el lett indítva. Csak arra a szálra
		 * lehet meghívni a start metódust, ami még NEW állapotban van.*/
		
		/*th1.interrupt();
		th2.interrupt();*/	
		
	}
	
	/**Változó paraméterszámú metódus. Futtatja a paraméterül kapott
	 * <code>Thread</code> objektumokat (mindegyiknek meghívja a <code>start</code>
	 * metódusát.
	 * 
	 * @param threads A futtatandó szálak*/
	public static void runThreads(Thread... threads){
		for(Thread t: threads){
			t.start();
		}
	}
	
	/**Futtatja a paraméterül kapott szálakat, és visszaad egy <code>ThreadGroup</code>
	 * objektumot, amihez a szálak tartoznak. A csoport neve <code>groupName</code>
	 * lesz.
	 * <br>
	 * A <code>ThreadGroup<code> osztály lehetõvé teszi, hogy több szálat együtt kezeljünk
	 * és az egy csoportba tartozó szálak egymásról is információkat nyerhetnek.
	 * <br>
	 * Mivel egy szál példányosítása után már nem lehet szálcsoporthoz rendelni,
	 * ezért ezt csk úgy tehetjük meg, hogy létrehozunk egy új szál objektumot, és
	 * a konstruktorban megadjuk a <code>ThreadGroup</code>-ot, amihez tartoznia kell,
	 * és az eredeti <code>Thread</code> objektumot. Ezt megtehetjük, mert a <code>Thread</code>
	 * osztály implementálja a <code>Runnable</code> interfészt, és van olyan konstruktora,
	 * ami <code>Runnable</code> objektumot vár paraméterként.
	 * 
	 * @param groupName A létrehozandó szálcsoport neve
	 * @param threads A szálak, amiket futtatni kell
	 * 
	 * @return Egy <code>ThreadGroup</code> objektum, ami összafogja a szálakat.*/
	public static ThreadGroup runThreadsWithGroup(String groupName, Thread... threads){
		//ezt fogjuk majd visszaadni
		ThreadGroup tg = new ThreadGroup(groupName);
		
		//meglátogatunk minden szálat
		for(Thread t: threads){
			//létrehozzuk az új szálakat
			Thread th = new Thread(tg, t);
			
			//majd elindítjuk õket
			th.start();
		}
		
		//visszaadjuk a szálcsoportot
		return tg;
	}
}
