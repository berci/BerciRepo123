package Thread;

/**
 * Ez az oszt�ly egy karakterpuffert val�s�t meg, amit egyszerre t�bb sz�l is
 * haszn�lhat, azaz met�dusai szinkroniz�ltak.
 * <br>
 * A szinkroniz�ci�t a <code>synchronized</code> kulcssz�val k�nyszer�thetj�k ki.
 * Ezt haszn�lhatjuk met�dusok megad�s�n�l �s utas�t�sokban is. Ha egy met�dust
 * szinkroniz�ltnak defini�lunk, akkor egyszerre csak egy sz�l haszn�lhatja
 * a met�dust, azaz ha egy sz�l m�r futtatja azt a met�dust, akkor minden m�s sz�l,
 * ami szint�n haszn�lni szeretn�, v�rakoz� �llapotba ker�l, am� az be nem fejezte, 
 * amit elkezdett. A legegyszer�bb strat�gia egy t�bbsz�l� alkalmaz�sban, ha minden
 * adattagot csak szinkroniz�lt met�dusokon kereszt�l tesz�nk el�rhet�v�, ha
 * minden adattagot csak szinroniz�lt met�dusok k�pesek �rni �s olvasni. 
 * <br>
 * Konstruktorok nem lehetnek szinkroniz�ltak, mert nincs �rtelme.
 * <br>
 * A szinkroniz�ci� legfontosabb fogalma Javaban a bels� z�r (intrinsic lock). Minden
 * objektumhoz tartozik egy ilyen z�r. Ha egy sz�l kiz�r�lagos hozz�f�r�st szeretne egy 
 * objektumhoz, akkor nem kell m�st tennie, mint lefoglalni az objektum z�rj�t. Ezut�n
 * hozz�f�rhet az objektum mez�ihez, majd ha befejezte, amit akart, akkor fel kell 
 * szabad�tania a z�rat. Am�g egy sz�l birtokolja egy objektum z�rj�t, addig m�sik
 * sz�l nem tudja lefoglalni azt. Ez biztos�tja a kiz�r�lagos hozz�f�r�st.
 * <br>
 * Ha sz�l megh�v egy szinkroniz�lt met�dust, akkor automatikusan lefoglalja a met�dusobjektum
 * z�rj�t. L�teznek emellett utas�t�sban haszn�lt szinkroniz�ci�k is:
 * <pre>
 * 					...
 * 					synchronized(this){
 * 						//a szinkroniz�lt k�d
 * 					}
 * 					...
 * </pre>
 * Ilyen k�dr�szlet l�that� az <code>add</code> �s a <code>get</code> met�duskban is. Ebben az 
 * esetben ha egy sz�l a szinkroniz�lt k�dot futtatja �ppen, akkor m�ik sz�l nem tudja m�dos�tani
 * az aktu�lis objektum (<code>this</code>) adattagjait. A <code>synchnronized</code>
 * utas�t�sban teh�t egy objektumreferenci�t kell megadnunk, ami arra az objektumra hivatkozik, amit
 * z�rolni szeretn�nk. Ez �ltal�ban a this. * 
 * 
 * @author tajti
 *
 */
public class CharBuffer {

	/**Ebben a t�mbben t�roljuk az el��ll�tott karaktereket.*/
	private char[] array;
	
	/**Ezzel az indexszel k�vetj�k, hogy hanyadik eleme a k�vetkez�
	 * felhaszn�lhat� eleme a t�mbnek.*/
	private int index = 0;
	
	/**A puffer maxim�lis m�rete*/
	public static final int MAX_SIZE = 50;
	
	/**�tm�soljuk ebbe a v�ltz�ba a maxm�retet, hogy ne csak statikus
	 * adattagb�lleessen el�rni*/
	private int maxSize = MAX_SIZE;
	
	public /*itt ez nem �llhat: synchronized*/ CharBuffer(){
		array = new char[maxSize];
	}
	
	/**Hozz�adja a <code>c</code> karaktert a pufferhez.
	 * 
	 * @param c A karakter, amit hozz� kell adni*/
	public synchronized void add(char c){
		if(index < maxSize){
			array[index] = c;
			
			//nem szeretn�nk, ha m�s sz�l is m�dos�tan� az indexet
			synchronized(this){
				index++;
			}
		}
	}
	
	/**Visszaadja a puffer utols� elem�t, vagy 0-t.
	 * 
	 * @return A puffer utols� eleme*/
	public synchronized char get(){
		char c = 0;
		
		if(index > 0){
			c = array[index - 1];
			
			//nem szeretn�nk, ha m�s sz�l is m�dos�tani az indexet
			synchronized(this){
				index--;
			}
		}
		
		return c;
	}	

}
