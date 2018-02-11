
public class BowlingGameMain {
	public static void main(String[] args) {

		BowlingGame gra1 = new BowlingGame();

		gra1.roll(0); //pierwszy rzut
		gra1.roll(3);
		gra1.roll(4);
		gra1.roll(3);
		gra1.roll(2);
		gra1.roll(4);
		gra1.roll(10);
		gra1.roll(10);
		gra1.roll(10);
		gra1.roll(4);
		gra1.roll(6);
		gra1.roll(5);
		gra1.roll(4);
		gra1.roll(1);
		gra1.roll(9);
		gra1.roll(10);
		gra1.roll(0);
		gra1.roll(0);
		gra1.roll(3);
		gra1.roll(4);
		gra1.roll(5);
		gra1.roll(2);

		BowlingGameTest.showRollsTable(gra1);
		System.out.println("aktualny wynik: " + gra1.calculateScore());
		
		BowlingGameTest.calculateFrame(gra1,10);
	}
}
