
public class BowlingGame {

	//tablica warto�ci rzut�w
	private int[] wholeGame = new int[21];

	//numer rzutu
	private int nRoll = 0;

	//koniec gry
	private int finish = 0;

	//numer kolejki wyliczany na podstawie numeru rzutu
	private int nFrame(int nR) {
		if(nR < 0) return -1;
		if(nR%2 == 0) return (nR + 2)/2;
		else return (nR + 1)/2;
	} 

	//metoda udost�pnia warto�� wskazanego rzutu zewn�trznym obiektom
	public int valueRoll(int i) {
		return wholeGame[i];
	} 

	
	//konstruktor tablicy rzut�w
	public BowlingGame() {
		//inicjalizacja tablicy warto�ciami -1
		for(int i=0; i < wholeGame.length; i++) {
			wholeGame[i] = -1; // -1 oznacza �e rzut nie by� wykonany
		}
	}

    //metoda sprawdza poprawno�� liczby wyrzuconych kr�gli w bie��cej kolejce
	private int checkPins(int pins) {
		int wynik = -1;
		if(nFrame(nRoll) < (wholeGame.length-1)/2 ) { // przed ostatni� kolejk�
			if(nRoll%2 == 0 && (pins>=0 && pins <= 10)) { //pierwszy rzut ramki poprawny 
				wynik = 1;
			}
			else if(nRoll%2 == 0 && (pins < 0 || pins > 10)) {//pierwszy rzut ramki niepoprawny
				System.out.println("Liczba " + pins + " przekracza dozwolony w pierwszym rzucie ramki zakres <0,10>");
				wynik = 0;
			}  
			else if(nRoll%2 == 1 && (pins >= 0 && pins <= 10-wholeGame[nRoll-1])) {//drugi rzut ramki poprawny
				wynik = 1;
			}  
			else if(nRoll%2 == 1 && (pins < 0 || pins > 10-wholeGame[nRoll-1])) {//drugi rzut ramki niepoprawny
				System.out.println("Liczba " + pins + " przekracza dozwolony w drugim rzucie ramki zakres <0, " + (10-wholeGame[nRoll-1]) + ">");
				wynik = 0;
			}  
		}
		else if(nFrame(nRoll) == (wholeGame.length-1)/2 ) { // ostatnia kolejk�
			if(nRoll%2 == 0 && (pins>=0 && pins <= 10)) { //pierwszy rzut ramki poprawny 
				wynik = 1;
			}
			else if(nRoll%2 == 0 && (pins < 0 || pins > 10)) {//pierwszy rzut ramki niepoprawny
				System.out.println("Liczba " + pins + " przekracza dozwolony w pierwszym rzucie ramki zakres");
				wynik = 0;
			}  
			else if(nRoll%2 == 1 && wholeGame[nRoll-1] == 10 && (pins < 0 || pins > 10)) {//drugi rzut ramki + strike w pierwszym i rzut niepoprawny
				System.out.println("Liczba " + pins + " przekracza dozwolony w drugim rzucie ramki zakres");
				wynik = 0;
			}  
			else if(nRoll%2 == 1 && wholeGame[nRoll-1] == 10 && (pins >= 0 && pins <= 10)) {//drugi rzut ramki + strike w pierwszym i rzut poprawny
				wynik = 1;
			}  
			else if(nRoll%2 == 1 && (pins >= 0 && pins <= 10-wholeGame[nRoll-1])) {//drugi rzut ramki poprawny
				wynik = 1;
			}  
			else if(nRoll%2 == 1 && (pins < 0 || pins > 10-wholeGame[nRoll-1])) {//drugi rzut ramki niepoprawny
				System.out.println("Liczba " + pins + " przekracza dozwolony w drugim rzucie ramki zakres <0, " + (10-wholeGame[nRoll-1]) + ">");
				wynik = 0;
			}  
		}
		else if(nRoll == wholeGame.length-1) { // dodatkowy rzut
			if(nRoll%1 == 0 && (pins >= 0 && pins <= 10)) {//dodatkowy rzut poprawny
				wynik = 1;
			}  
			else if(nRoll%1 == 0 && (pins < 0 || pins > 10)) {//dodatkowy rzut niepoprawny
				System.out.println("Liczba " + pins + " przekracza dozwolony w dodatkowym rzucie zakres <0,10>");
				wynik = 0;
			}  
		}
		return wynik;		
	}
	
	//pojedynczy rzut
	public void roll(int pins){
		if(finish == 0) {
			int cResult = checkPins(pins);
			if(cResult == 1) { //liczba poprawna w danej kolejce
	
				System.out.println("rzut: " + (nRoll + 1) + ", kolejka: " + nFrame(nRoll));
	
				if(nFrame(nRoll) < (wholeGame.length-1)/2 ) { // nie osi�gni�to ostatniej kolejki
					wholeGame[nRoll] = pins;
					System.out.println("Liczb� " + pins + " zapisano do tabeli wynik�w");
					if(nRoll%2 == 0 && pins == 10) nRoll +=2; //strike w pierwszym rzucie w ramce 
					else if(nRoll%2 == 0 && pins < 10) nRoll +=1; //nie by�o strike w pierwym rzucie w ramce 
					else if(nRoll%2 == 1) nRoll +=1; //drugi rzut w ramce 
				}
				else if(nFrame(nRoll) == (wholeGame.length-1)/2) { //ostatnia kolejka
					wholeGame[nRoll] = pins;
					System.out.println("Liczb� " + pins + " zapisano do tabeli wynik�w");
					if(nRoll%2 == 0 && pins == 10) nRoll +=1; //strike w pierwszym rzucie w ramce 
					else if(nRoll%2 == 0 && pins < 10) nRoll +=1; //nie by�o strike w pierwym rzucie w ramce 
					else if(nRoll%2 == 1 && (wholeGame[nRoll-1] + wholeGame[nRoll]) >= 10) nRoll += 1; //drugi rzut w ramce i spare w ostatniej kolejce, gracz ma prawo do dodatkowego rzutu 
					else if(nRoll%2 == 1 && (wholeGame[nRoll-1] + wholeGame[nRoll]) < 10) {//drugi rzut w ramce i mniej ni� 10 w ostatniej kolejce, koniec gry
						nRoll +=1;
		                finish = 1;//koniec gry
		            }  
				}
				else if(nRoll == wholeGame.length-1) { //dodatkowy rzut
					wholeGame[nRoll] = pins;
					System.out.println("Liczb� " + pins + " zapisano do tabeli wynik�w");
					nRoll +=1; 
	                finish = 1;//koniec gry
				}
			}
			else if (cResult == 0) // liczba niepoprawna w danej kolejce
				System.out.println("Liczba " + pins + " nie wpisana do tabeli wynik�w");
		}
		else if(finish == 1) {
			System.out.println("Koniec gry. Kolejne liczby nie b�d� brane pod uwag�.");
		}
	}

	//aktualny wynik  
	public int calculateScore(){
		int wynik = 0;
		//if(nRoll > wholeGame.length) nRoll=wholeGame.length;  //zabezpieczenie na sytuacj� po ostatnim rzucie
		//pierwszy rzut kolejki i to wholeGame[2*i-2]
		//pierwszy rzut kolejki i to wholeGame[2*i-1]
		for(int i=1; i <= nFrame(nRoll); i++){ //p�tla po numerze kolejki
			if( i < 10){ // w tablicy rzut�w jest nast�pna kolejka
				if((wholeGame[2*i-2] + wholeGame[2*i-1] != 10) && wholeGame[2*i-1] >= 0) { //zwyk�a kolejka
					wynik += (wholeGame[2*i-2] + wholeGame[2*i-1]);
				}
				if((wholeGame[2*i-2] == 10) && wholeGame[2*i-1] == -1) { //strike
					if((wholeGame[2*(i+1)-2] == 10) && wholeGame[2*(i+1)-1] == -1) { // nast�pny r�wnie� jest typu strike
						if(wholeGame[2*(i+2)-2] >= 0) { 
							wynik += (10 + 10 + wholeGame[2*(i+2)-2]); //za drugim strike jest kolejka
						}
						else { //za drugim strike nie ma ju� kolejki
							wynik += (10 + 10);
						}
					}
					else {
						wynik += (10 + wholeGame[2*(i+1)-2] + wholeGame[2*(i+1)-1]); //premia za strike
					} 
				}
				if(wholeGame[2*i-2] + wholeGame[2*i-1] == 10) { //spare
					wynik += (10 + wholeGame[2*(i+1)-2]); //premia za spare
				}
			}
			else if( i == 10) {
				if (wholeGame[2*i-2] != -1) wynik += wholeGame[2*i-2];
				if (wholeGame[2*i-1] != -1) wynik += wholeGame[2*i-1];
				if (wholeGame[2*(i+1)-2] != -1) wynik += wholeGame[2*(i+1)-2];
			}
		}
		return wynik;
	}
}
