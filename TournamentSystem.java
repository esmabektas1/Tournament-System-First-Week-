import java.util.Scanner;

public class TournamentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //  (0: Team A, 1: Team B, 2: Team C, 3: Team D)
        String[] teams = {"Team A", "Team B", "Team C", "Team D"};
        
        int[] played = new int[4];
        int[] won = new int[4];
        int[] drawn = new int[4];
        int[] lost = new int[4];
        int[] goalsFor = new int[4];
        int[] goalsAgainst = new int[4];
        int[] goalDifference = new int[4];
        int[] points = new int[4];

        
        // Match 1: A vs B, Match 2: A vs C, Match 3: A vs D
        // Match 4: B vs C, Match 5: B vs D, Match 6: C vs D
        int[] homeTeam = {0, 0, 0, 1, 1, 2};
        int[] awayTeam = {1, 2, 3, 2, 3, 3};

                int[] homeScores = new int[6];
        int[] awayScores = new int[6];

        
        System.out.println("========================================");
        System.out.println("            MATCH FIXTURE               ");
        System.out.println("========================================");
        System.out.println("Match 1: Team A vs Team B");
        System.out.println("Match 2: Team A vs Team C");
        System.out.println("Match 3: Team A vs Team D");
        System.out.println("Match 4: Team B vs Team C");
        System.out.println("Match 5: Team B vs Team D");
        System.out.println("Match 6: Team C vs Team D");
        System.out.println("========================================\n");

        System.out.println("Please enter the match scores:");
        for (int i = 0; i < 6; i++) {
            int h = homeTeam[i];
            int a = awayTeam[i];

            System.out.println("\n--- Match " + (i + 1) + ": " + teams[h] + " vs " + teams[a] + " ---");
            System.out.print(teams[h] + " goals: ");
            homeScores[i] = scanner.nextInt();

            System.out.print(teams[a] + " goals: ");
            awayScores[i] = scanner.nextInt();

            
            goalsFor[h] += homeScores[i];
            goalsAgainst[h] += awayScores[i];
            goalsFor[a] += awayScores[i];
            goalsAgainst[a] += homeScores[i];

            played[h]++;
            played[a]++;

            
            if (homeScores[i] > awayScores[i]) {
                won[h]++;
                points[h] += 3;
                lost[a]++;
            } else if (homeScores[i] < awayScores[i]) {
                won[a]++;
                points[a] += 3;
                lost[h]++;
            } else {
                drawn[h]++;
                drawn[a]++;
                points[h] += 1;
                points[a] += 1;
            }
        }

        
        System.out.println("\n========================================");
        System.out.println("         ENTERED MATCH SCORES           ");
        System.out.println("========================================");
        for (int i = 0; i < 6; i++) {
            int h = homeTeam[i];
            int a = awayTeam[i];
            System.out.println("Match " + (i + 1) + ": " + teams[h] + " vs " + teams[a] +
                               " — " + teams[h] + " goals: " + homeScores[i] + 
                               ", " + teams[a] + " goals: " + awayScores[i]);
        }

        
        for (int i = 0; i < 4; i++) {
            goalDifference[i] = goalsFor[i] - goalsAgainst[i];
        }

        
        System.out.println("\n==========================================================================");
        System.out.println("                            STANDINGS TABLE                               ");
        System.out.println("==========================================================================");
        System.out.println("Team\t\tPlayed\tWon\tDrawn\tLost\tGF\tGA\tGD\tPoints");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < 4; i++) {
            System.out.println(teams[i] +"     "+ "\t" + played[i] + "\t" + won[i] + "\t" +
                               drawn[i] + "\t" + lost[i] + "\t" + goalsFor[i] + "\t" +
                               goalsAgainst[i] + "\t" + goalDifference[i] + "\t" + points[i]);
        }
        System.out.println("==========================================================================");

        
        int championIndex = 0;
        for (int i = 1; i < 4; i++) {
            if (points[i] > points[championIndex]) {
                championIndex = i;
            } else if (points[i] == points[championIndex]) {
                

                if (goalDifference[i] > goalDifference[championIndex]) {
                    championIndex = i;
                }
            }
        }

        System.out.println("\nTournament Champion: " + teams[championIndex]);

        scanner.close();
    }
}
