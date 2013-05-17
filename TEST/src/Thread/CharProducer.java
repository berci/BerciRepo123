/**
 * 
 */
package Thread;

/**
 * Egy olyan oszt�ly, ymi egy sz�lat val�s�t meg (lesz�rmazottja a
 * <code>Thread</code> oszt�lynak). �szesen csak annyit csin�l, hogy
 * karaktereket �ll�t el�, amit majd a m�sik sz�l felhaszn�l. <br>
 * Minden <code>Thread</code> lesz�rmazott a <code>run</code> met�dus�ban
 * val�s�tja meg a logik�j�t, teh�t abban kell lek�dolni a sz�ks�ges dolgokat. A
 * sz�lat elind�t� <code>start</code> met�dus ezut�n haszn�lni fogja �t.
 * 
 * @author tajti
 * 
 */
public class CharProducer extends Thread {

	/**Ebbe a pufferbe fog a sz�l karaktereket tenni.*/
	private CharBuffer buffer;
	
	public CharProducer(CharBuffer buffer){
		this.buffer = buffer;
	}
	
	/**
	 * A sz�l�ben defini�lt met�dus, amit itt �jraimplement�lunk. A sz�l
	 * indul�sakor fut le. Egy sz�l �let�ben csak egyszer h�v�dik meg. 
	 * <br>
	 * Itt annyit tesz a met�dus, hogy v�letlenszer�en el��ll�t sz�mokat,
	 * azokb�l karaktereket �s ki�rja �ket a szabv�nyos kimenetre. Ezt addig
	 * ism�tli, am�g a sz�l �l.
	 */
	public void run() {
		//ez egy v�lelen ciklus lez, azaz csak akkor �ll le, ha a sz�l "le�ll"
		while (true) {
			//altatjuk a sz�lat. erre az�rt van sz�ks�g, hogy ne "maradjon" le a fogyaszt�
			try{
				Thread.sleep(600);
			}catch(InterruptedException ie){
				interrupt(); //vissza�ll�tjuk az interrupted flaget
			}
			
			// el��ll�tunk egy 0 �s 26 k�z� es� v�letlensz�mot
			int rand = (int) (Math.random() * 26);

			// ebb�l k�sz�t�nk egy karaktert
			char c = (char) ((int) 'a' + rand);

			//elhelyezz�k a pufferbe
			buffer.add(c);
			
			// ki�rjuk a kimenetre
			System.out.println("produced: " + c);
			
			/* Ezzel a met�dussal ellen�rizhetj�k, hogy az aktu�lis sz�lra
			 * (teh�t amib�l a met�dust megh�vjuk) meh�vt�k-e az interrupt 
			 * met�dust. Ha igen, akkor true �rt�ket ad vissza, �s eld�nthetj�k,
			 * hogy mit tesz�nk. �ltal�ban megszak�tjuk a run fut�s�t, mint
			 * ahogy ebben a p�ld�ban  is tessz�k.
			 * Ha az interrupted met�dust k�tszer h�vjuk meg �gy, hogy
			 * a k�t h�v�s k�z�tt senki nem h�vta meg a sz�lra az interruptot,
			 * akkor false �rt�ket ad vissza, teh�t a flage t�rtl�dik.
			 * Mivel az InterruptedException kiv�lt�d�sa is t�rli a flaget, ez�rt
			 * ennek csak akkor van �rtelme, ha a fenti kiv�telkezel� blokk
			 * lefut�sa ut�n megh�vt�k az interrupt met�dust.*/
			if(Thread.interrupted()){
				System.out.println("CharProducer: megszak�tottak");
				return;
			}
		}
	}

}
