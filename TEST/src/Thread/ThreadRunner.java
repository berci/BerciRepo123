package Thread;

/*
 * Ez az  oszt�ly sz�lakat futtat.
 * <br>
 * Egy az�lat futtatni a <code>start</code> met�dussal lehet. Ezt a 
 * met�dust csak egyszer lehet megh�vni egy sz�lra, csak akkor, amikor az
 * m�g <code>Thread.State.NEW</code> �llapotban van. Minden m�s �llapot eset�n
 * ha megk�s�relj�k megh�vni a met�dust, akkor <code>IllegalThreadStateException</code>
 * kiv�telt kapn�nk. 
 * <br>
 * Egy sz�l a k�vetkez� �llapotok valamelyik�ben lehet:
 * <ul>
 * <li><code>Thread.State.NEW</code>: minden olyan sz�l, ami p�ld�nyos�t�s ut�n
 * m�g nem lett elind�tva, ebben az �llpotban van. Az ilyen �llapot� sz�lakra lehet megh�vni
 * a <code>start</code>met�dust.</li>
 * <li><code>Thread.State.RUNNABLE</code>: a fut� sz�lak �llapota</li>
 * <li><code>Thread.State.BLOCKED</code>: azok a sz�lak vannak ebben az �allapotban, amik egy
 * z�r felold�s�ra v�rnak.</li>
 * <li><code>Thread.State.WAITING</code>: ha egy sz�l arra v�r (meg nem hat�rozott ideig), hogy
 * egy m�sik sz�l v�gezzen egy feladat v�grehajt�s�val, akkor ebben az �llapotban van.</li>
 * <li><code>Thread.State.TIMED_WAITNG</code>: ugyanaz, mint az el�z�, csak itt meg
 * van hat�rozva a v�rakoz�si id�.</li>
 * <li><code>Thread.State.TERMINATED</code>: a le�ll�tott sz�lak ebben az �llapotban vannak.</li>
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
		
		/* Ezt nem h�vhatjuk meg �jra:
		 * 
		 * th1.start();
		 * 
		 * Ha megpr�b�ln�nk, akkor IllegalStateException kiv�telt kapn�nk,
		 * mert a th1 sz�l m�r el lett ind�tva. Csak arra a sz�lra
		 * lehet megh�vni a start met�dust, ami m�g NEW �llapotban van.*/
		
		/*th1.interrupt();
		th2.interrupt();*/	
		
	}
	
	/**V�ltoz� param�tersz�m� met�dus. Futtatja a param�ter�l kapott
	 * <code>Thread</code> objektumokat (mindegyiknek megh�vja a <code>start</code>
	 * met�dus�t.
	 * 
	 * @param threads A futtatand� sz�lak*/
	public static void runThreads(Thread... threads){
		for(Thread t: threads){
			t.start();
		}
	}
	
	/**Futtatja a param�ter�l kapott sz�lakat, �s visszaad egy <code>ThreadGroup</code>
	 * objektumot, amihez a sz�lak tartoznak. A csoport neve <code>groupName</code>
	 * lesz.
	 * <br>
	 * A <code>ThreadGroup<code> oszt�ly lehet�v� teszi, hogy t�bb sz�lat egy�tt kezelj�nk
	 * �s az egy csoportba tartoz� sz�lak egym�sr�l is inform�ci�kat nyerhetnek.
	 * <br>
	 * Mivel egy sz�l p�ld�nyos�t�sa ut�n m�r nem lehet sz�lcsoporthoz rendelni,
	 * ez�rt ezt csk �gy tehetj�k meg, hogy l�trehozunk egy �j sz�l objektumot, �s
	 * a konstruktorban megadjuk a <code>ThreadGroup</code>-ot, amihez tartoznia kell,
	 * �s az eredeti <code>Thread</code> objektumot. Ezt megtehetj�k, mert a <code>Thread</code>
	 * oszt�ly implement�lja a <code>Runnable</code> interf�szt, �s van olyan konstruktora,
	 * ami <code>Runnable</code> objektumot v�r param�terk�nt.
	 * 
	 * @param groupName A l�trehozand� sz�lcsoport neve
	 * @param threads A sz�lak, amiket futtatni kell
	 * 
	 * @return Egy <code>ThreadGroup</code> objektum, ami �sszafogja a sz�lakat.*/
	public static ThreadGroup runThreadsWithGroup(String groupName, Thread... threads){
		//ezt fogjuk majd visszaadni
		ThreadGroup tg = new ThreadGroup(groupName);
		
		//megl�togatunk minden sz�lat
		for(Thread t: threads){
			//l�trehozzuk az �j sz�lakat
			Thread th = new Thread(tg, t);
			
			//majd elind�tjuk �ket
			th.start();
		}
		
		//visszaadjuk a sz�lcsoportot
		return tg;
	}
}
