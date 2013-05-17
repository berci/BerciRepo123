package Thread;

/**
 * Ez az osztály egy karakterpuffert valósít meg, amit egyszerre több szál is
 * használhat, azaz metódusai szinkronizáltak.
 * <br>
 * A szinkronizációt a <code>synchronized</code> kulcsszóval kényszeríthetjük ki.
 * Ezt használhatjuk metódusok megadásánál és utasításokban is. Ha egy metódust
 * szinkronizáltnak definiálunk, akkor egyszerre csak egy szál használhatja
 * a metódust, azaz ha egy szál már futtatja azt a metódust, akkor minden más szál,
 * ami szintén használni szeretné, várakozó állapotba kerül, amí az be nem fejezte, 
 * amit elkezdett. A legegyszerûbb stratégia egy többszálú alkalmazásban, ha minden
 * adattagot csak szinkronizált metódusokon keresztül teszünk elérhetõvé, ha
 * minden adattagot csak szinronizált metódusok képesek írni és olvasni. 
 * <br>
 * Konstruktorok nem lehetnek szinkronizáltak, mert nincs értelme.
 * <br>
 * A szinkronizáció legfontosabb fogalma Javaban a belsõ zár (intrinsic lock). Minden
 * objektumhoz tartozik egy ilyen zár. Ha egy szál kizárólagos hozzáférést szeretne egy 
 * objektumhoz, akkor nem kell mást tennie, mint lefoglalni az objektum zárját. Ezután
 * hozzáférhet az objektum mezõihez, majd ha befejezte, amit akart, akkor fel kell 
 * szabadítania a zárat. Amíg egy szál birtokolja egy objektum zárját, addig másik
 * szál nem tudja lefoglalni azt. Ez biztosítja a kizárólagos hozzáférést.
 * <br>
 * Ha szál meghív egy szinkronizált metódust, akkor automatikusan lefoglalja a metódusobjektum
 * zárját. Léteznek emellett utasításban használt szinkronizációk is:
 * <pre>
 * 					...
 * 					synchronized(this){
 * 						//a szinkronizált kód
 * 					}
 * 					...
 * </pre>
 * Ilyen kódrészlet látható az <code>add</code> és a <code>get</code> metóduskban is. Ebben az 
 * esetben ha egy szál a szinkronizált kódot futtatja éppen, akkor máik szál nem tudja módosítani
 * az aktuális objektum (<code>this</code>) adattagjait. A <code>synchnronized</code>
 * utasításban tehát egy objektumreferenciát kell megadnunk, ami arra az objektumra hivatkozik, amit
 * zárolni szeretnénk. Ez általában a this. * 
 * 
 * @author tajti
 *
 */
public class CharBuffer {

	/**Ebben a tömbben tároljuk az elõállított karaktereket.*/
	private char[] array;
	
	/**Ezzel az indexszel követjük, hogy hanyadik eleme a következõ
	 * felhasználható eleme a tömbnek.*/
	private int index = 0;
	
	/**A puffer maximális mérete*/
	public static final int MAX_SIZE = 50;
	
	/**Átmásoljuk ebbe a váltzóba a maxméretet, hogy ne csak statikus
	 * adattagbólleessen elérni*/
	private int maxSize = MAX_SIZE;
	
	public /*itt ez nem állhat: synchronized*/ CharBuffer(){
		array = new char[maxSize];
	}
	
	/**Hozzáadja a <code>c</code> karaktert a pufferhez.
	 * 
	 * @param c A karakter, amit hozzá kell adni*/
	public synchronized void add(char c){
		if(index < maxSize){
			array[index] = c;
			
			//nem szeretnénk, ha más szál is módosítaná az indexet
			synchronized(this){
				index++;
			}
		}
	}
	
	/**Visszaadja a puffer utolsó elemét, vagy 0-t.
	 * 
	 * @return A puffer utolsó eleme*/
	public synchronized char get(){
		char c = 0;
		
		if(index > 0){
			c = array[index - 1];
			
			//nem szeretnénk, ha más szál is módosítani az indexet
			synchronized(this){
				index--;
			}
		}
		
		return c;
	}	

}
