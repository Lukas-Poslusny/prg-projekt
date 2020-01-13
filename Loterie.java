import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Loterie {
    // globalni promene
    static int ticketNum = 0;                       // pocet listku
    static int moneyIn = 0;                         // vklad
    static int moneyWon = 0;                        // vyhry
    static int moneyGeneral = moneyIn + moneyWon;   // vklad + vyhry
    static int numberToArrayIn;                     // uzivatelem zadane cislo do pole (9x)
    static int moneyInTotal;
    static String repeat = "";

    static int[][] array = new int[9][2];

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        do {
            // Uvitani do hry
            System.out.println("VÍTEJTE V LOTERIJNÍ HŘE!");
            System.out.println("Za každé totožné číslo získáte (x) korun českých a pokud se budou všechna čísla shodovat vyhráváte JACKPOT 10.000.000 kč !");
            System.out.println("Každý lístek stojí 100 korun českých");

            // kontrola dulezita pri opakovani hry 
            if (ticketNum == 0) {
                System.out.print("\nKolik lístků si přejete koupit?\nZadejte počet: ");
                ticketNum = sc.nextInt(); // in: listky

                System.out.print("\nKolik peněz si přejete vložit? (min.: " + (ticketNum*100) + ")\nZadejte počet: ");
                // opakuje se dokud nezadame dostatecne penez na zadany pocet listku (1 li = 100 kč)
                do {
                    moneyIn = sc.nextInt(); // in: penize
                    moneyInTotal += moneyIn;
                    if (ticketNum*100 > moneyIn + moneyWon) {
                        System.out.println("\nNedostatek penez, zadejte prosim alespon " + (ticketNum*100) + " korun\n");
                        System.out.print("Kolik peněz si přejete vložit?\nZadejte počet: ");
                    }
                } while (ticketNum*100 > moneyIn + moneyWon);
            }  
            
            do {
                ArrayList<Integer> fixedNumbers = new ArrayList<Integer>();
                ArrayList<Integer> userEnteredNumbers = new ArrayList<Integer>();

                // generování nahodnych cisel 1-100
                // usporadani prvni rady (programem zadaná čísla)
                for (int i = 0; i < 9; i++) {
                    int rand = (int)(Math.random()*100) + 1; // random cislo 1-100
                    fixedNumbers.add(rand);
                }
                Collections.sort(fixedNumbers); // usporada cisla v array listu od nejmensiho po nejvetsi

                for(int i = 0; i < fixedNumbers.size(); i++ ) { // vezme cisla z array listu a vlozi je do pole
                    array[i][0] = fixedNumbers.get(i);
                }

                // TO-DO uživatel zadá 9 čísel
                System.out.println("\nZadejte postupne vsech svych 9 cisel v rozmezi 1-100");
                for (int i = 0; i < 9; i++) {
                    System.out.print("Cislo " + (i+1) + ": ");
                    numberToArrayIn = sc.nextInt();
                    userEnteredNumbers.add(numberToArrayIn);
                }
                Collections.sort(userEnteredNumbers); // usporada cisla v array listu od nejmensiho po nejvetsi

                for (int i = 0; i < userEnteredNumbers.size(); i++) { // vezme cisla z array listu a vlozi je do pole
                    array[i][1] = userEnteredNumbers.get(i);
                }
                
                // vypise pole 
                System.out.print("Predem dana cisla: ");
                for (int y = 0; y < 9; y++) {
                    System.out.print(array[y][0] + " ");
                }
                System.out.println("");
                System.out.print("Vami zadana cisla: ");
                for (int y = 0; y < 9; y++) {
                    System.out.print(array[y][1] + " ");
                }
                System.out.println("");

                for (int i = 0; i < 9; i++) { // porovná čísla a přičte výhru pokud se shodují
                    if (array[i][0] == array[i][1]) {
                        moneyWon += 100;
                    }
                }

                moneyIn -= 100;
                System.out.println("Vas zustatek je: " + (moneyWon + moneyIn) + ". Vyhry: " + moneyWon + ", vklad " + moneyIn);
                ticketNum -= 1;
            } while (ticketNum > 0);

            if (moneyGeneral >= 100) {
                System.out.println("Chcete pokracovat? Ano/Ne");
                repeat = sc.nextLine();
                // Ano/Ne
            }
            else
                repeat = "ne";
        } while (repeat.toLowerCase() == "ano");
        // opakování 

        // ukončit - vypíše zbytek peněz + jestli máme víc nebo méně peněz než při vkladu.
        if (moneyWon > moneyGeneral)
            System.out.println("Vysledny zustatek je " + (moneyGeneral - moneyInTotal) + ". Vyhrali jste " + (moneyWon - moneyIn) + " korun ceskych!");
        else if (moneyWon == moneyGeneral)
            System.out.println("Vysledny zustatek je " + (moneyGeneral - moneyInTotal) + ". Jste na stejne castce jako na zacatku.");
        else
            System.out.println("Vysledny zustatek je " + (moneyGeneral - moneyInTotal) + ". Nic jste nevyhrali.");
    };  
};