
public class BowlingGameTest {

	static void showRollsTable(BowlingGame gra){ // poka¿ tabelê rzutów
        String wynik="";
		String rowek1 = "";
		for(int i=0; i < 21; i+=2) {
			//rowek1 += gra.valueRoll(i) + ", ";
			wynik = "  " + gra.valueRoll(i);
	        rowek1 += (wynik.substring(wynik.length() - 2, wynik.length())) + ","; //³adniejsze formatowanie wyniku
		}
		System.out.println(rowek1);

		String rowek2 = "";
		for(int i=1; i < 21; i+=2) {
			//rowek2 += gra.valueRoll(i) + ", ";
			wynik = "  " + gra.valueRoll(i);
	        rowek2 += (wynik.substring(wynik.length() - 2, wynik.length())) + ","; //³adniejsze formatowanie wyniku
		}
		System.out.println(rowek2);
	}

	static int calculateFrame(BowlingGame gra, int f){ // wynik ramki f
		int wynik = 0;
		String typ = "";
		
		if (f >=1 && f < 10) {
			if ((gra.valueRoll(2*f-2) + gra.valueRoll(2*f-1) < 10) && gra.valueRoll(2*f-2) != 10) { //zwyk³a ramka
				wynik = gra.valueRoll(2*f-2) + gra.valueRoll(2*f-1);
				typ = "zwyk³a";
			}
			if ((gra.valueRoll(2*f-2) + gra.valueRoll(2*f-1) == 10) && gra.valueRoll(2*f-2) != 10) { //spare
				wynik = gra.valueRoll(2*f-2) + gra.valueRoll(2*f-1);
				if (gra.valueRoll(2*(f+1)-2)!=-1) wynik+=gra.valueRoll(2*(f+1)-2); // bonus za spare, (jeœli jest w tabeli)
				typ = "spare";
			}
			if (gra.valueRoll(2*f-2) == 10) { //strike
				wynik = gra.valueRoll(2*f-2);
				if (gra.valueRoll(2*(f+1)-2)!=-1) {
					wynik+=gra.valueRoll(2*(f+1)-2); // bonus za strike, (jeœli jest w tabeli)
					if (gra.valueRoll(2*(f+1)-1)!=-1) {
						wynik+=gra.valueRoll(2*(f+1)-1);// cd bonus za strike, (jeœli jest w tabeli)
						/*
						if (gra.valueRoll(2*(f+2)-2)!=-1) {
							wynik+=gra.valueRoll(2*(f+2)-2); // cd bonus za strike, (jeœli jest w tabeli i jest kolejny strike)
						}
						*/
					}
					else if (gra.valueRoll(2*(f+1)-1)==-1) wynik+=gra.valueRoll(2*(f+2)-2);
				}
				typ = "strike";
			}
			
		}
		else if (f==10) { // ostatnia ramka
			if ((gra.valueRoll(2*f-2) + gra.valueRoll(2*f-1) < 10) && gra.valueRoll(2*f-2) != 10) { //zwyk³a ramka
				wynik = gra.valueRoll(2*f-2) + gra.valueRoll(2*f-1);
				typ = "zwyk³a";
			}
			if ((gra.valueRoll(2*f-2) + gra.valueRoll(2*f-1) == 10) && gra.valueRoll(2*f-2) != 10) { //spare
				wynik = gra.valueRoll(2*f-2) + gra.valueRoll(2*f-1);
				if (gra.valueRoll(2*(f+1)-2)!=-1) wynik+=gra.valueRoll(2*(f+1)-2); // bonus za spare, (jeœli jest w tabeli)
				typ = "spare";
			}
			if (gra.valueRoll(2*f-2) == 10) { //strike
				wynik = gra.valueRoll(2*f-2) + gra.valueRoll(2*f-1);
				if (gra.valueRoll(2*(f+1)-2)!=-1) wynik+=gra.valueRoll(2*(f+1)-2); // bonus za strike, (jeœli jest w tabeli)
				typ = "strike";
			}
		}
		else System.out.println("Dozwolony jest nr ramki z zakresu <1,10>");

		//System.out.println("ramka: " + f + ", typ: " + typ + ", wynik:" + wynik);
	    return wynik;
	}
}


