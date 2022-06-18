import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static int iloczyn_cyfr(int x) {
		int wynik = 1;
		while (x > 0) {
			wynik = wynik * (x % 10);
			x = x / 10;
		}
		return wynik;
	}

	public static int moc(int liczba) {

		int moc = 1;

		liczba = iloczyn_cyfr(liczba); // iloczyn poczatkowy
		while (liczba > 9) {
			liczba = iloczyn_cyfr(liczba);
			moc++;
		}
		return moc;

	}

	public static boolean czynniki(int liczba) {

		int ile = 0;
		int czynnik = 3;
		if (liczba % 2 == 0)
			return false;
		while (liczba > 1) {
			if (liczba % czynnik == 0)
				ile++;
			while (liczba % czynnik == 0) {
				liczba = liczba / czynnik;
			}
			czynnik = czynnik + 2;
			if (ile > 3)
				return false;
		}
		if (ile == 3)
			return true;
		if (ile < 3)
			return false;
		return false;

	}

	public static int odwroc(int liczba) {

		int nowa = 0;
		while (liczba > 0) {
			nowa = 10 * nowa + liczba % 10;
			liczba = liczba / 10;
		}
		return nowa;

	}

	public static int generuj_losowe(int min, int max) {
		Random random = new Random();
		return random.ints(min, max).findFirst().getAsInt();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		System.out.println("Podaj ilosc liczb");
		int ilosc = s.nextInt();
		int[] liczby = new int[ilosc];
		System.out.println("Podaj zakres:");
		System.out.print("min: ");
		int zakres_min = s.nextInt();
		System.out.print("max: ");
		int zakres_max = s.nextInt();
		System.out.println("Liczby: ");
		for (int i = 0; i < liczby.length; i++) {
			liczby[i] = generuj_losowe(zakres_min, zakres_max);
			//System.out.print(liczby[i] + " | ");
		}
		s.nextLine();
		System.out.println("--------------MENU-----------");
		System.out.println("1. Liczby, w ktorych rozkladzie na czynniki pierwsze wystepuja 3 rozne..");
		System.out.println("2. Liczby, w ktorych zapis dziesietny jest palindromem");
		System.out.println("3. Liczby o zadanej mocy");
		System.out.println("9. WYJSCIE");
		System.out.println("-----------------------------");
		int wybor = 0;
		while (wybor != 9) {
      System.out.println("Podaj wybór: ");
			wybor = s.nextInt();
			switch (wybor) {

				case 1: {
					ArrayList<Integer> wynikowe = new ArrayList<Integer>();
					int licznik_czynniki_p = 0;
					for (int liczba : liczby) {
						if (czynniki(liczba)) {
							licznik_czynniki_p++;
							wynikowe.add(liczba);
						}
					}
					int widok = 0;
					System.out.println("Wybierz format wyswietlania: ");
					System.out.println("1. Wyswietl liczby spelniajace warunek");
					System.out.println("2. Wyswietl ilość liczb spelniajacych warunek");
					widok = s.nextInt();
					if (widok == 1) {
						System.out.println(
								"Liczby, w których rozkładzie na czynniki pierwsze występują dokładnie trzy różne czynniki z których każdy jest nieparzysty: ");
						System.out.println(wynikowe);
	
					} else if (widok == 2) {
						System.out.println(
								"Liczby, w których rozkładzie na czynniki pierwsze występują dokładnie trzy różne czynniki z których każdy jest nieparzysty: "
										+ licznik_czynniki_p);
					} else
						System.out.println("ERROR");
					break;
				}
				case 2: {
					int odwrocona = 0;
					int suma = 0;
					int licznik_pali = 0;
					ArrayList<Integer> wynikowe = new ArrayList<Integer>();
					for (int liczba : liczby) {
						odwrocona = odwroc(liczba);
						suma = odwrocona + liczba;
						if (odwroc(suma) == suma) {
							licznik_pali++;
							wynikowe.add(liczba);
						}
					}
					int widok = 0;
					System.out.println("Wybierz format wyswietlania: ");
					System.out.println("1. Wyswietl liczby spelniajace warunek");
					System.out.println("2. Wyswietl ilość liczb spelniajacych warunek");
					widok = s.nextInt();
					if (widok == 1) {
						System.out.println("Liczby, w ktorych zapis dziesietny jest palindromem: ");
						System.out.println(wynikowe);
	
					} else if (widok == 2) {
						System.out.println("Ilość liczb, w ktorych zapis dziesietny jest palindromem: " + licznik_pali);
					} else
						System.out.println("ERROR");
					break;
				}
				case 3: {
					ArrayList<Integer> wynikowe = new ArrayList<Integer>();
					System.out.println("Podaj moc (od 1 do 8): ");
					int moc = s.nextInt();
					if (moc >= 1 && moc <= 8) {
						int licznik_moc = 0;
						for (int liczba : liczby) {
							if (moc(liczba) == moc) {
								licznik_moc++;
								wynikowe.add(liczba);
							}
						}
						int widok = 0;
						System.out.println("Wybierz format wyswietlania: ");
						System.out.println("1. Wyswietl liczby spelniajace warunek");
						System.out.println("2. Wyswietl ilość liczb spelniajacych warunek");
						widok = s.nextInt();
						if (widok == 1) {
							System.out.println("Liczby o mocy " + moc + ": ");
							System.out.println(wynikowe);
	
						} else if (widok == 2) {
							System.out.println("Ilość liczb o mocy " + moc + ": " + licznik_moc);
						} else
							System.out.println("ERROR");
						break;
					} else
						break;
				}
			}
		}
		s.close();
	}
}