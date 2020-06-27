public class main {

    final static int anzahl_knoten = 13;
    final static int INF = Integer.MAX_VALUE;


    //Helferfunktion, welche unsere Lösung ausgibt
    public static void druckeErgebnis(int dist[])
    {
        System.out.println("Knoten \t Entfernung vom Start");
        for (int i = 0; i < anzahl_knoten; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }
    /*
    Helferfunktion, welche den Knoten mit der geringsten Distanz findet, der noch nicht in besucht ist
     */
    public static int minDistance(int[] distanz, boolean[] besucht)
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < anzahl_knoten; v++)
            if (!besucht[v] && distanz[v] <= min) {
                min = distanz[v];
                min_index = v;
            }

        return min_index;
    }

    public static void dijkstra(int[][] graph, int start) {
        int[] distanz = new int[anzahl_knoten]; //Unser Array mit Lösungen. distanz[i] ist die Distanz vom Startpunkt zu i

        //Dieses Array zeigt, welche Knoten wir bereits untersucht haben
        boolean[] bereitsBesucht = new boolean[anzahl_knoten];

        //Die Distanz zu anderen Knoten wir zunächst mit INFINITE angelegt
        //Zusätzlich haben wir noch keinen Knoten besucht
        for(int i = 0; i < anzahl_knoten; i++) {
            distanz[i] = INF;
            bereitsBesucht[i] = false;

        }
        //Distanz zu unserer aktuellen Position ist immer 0
        distanz[start] = 0;

        //Hier beginnt der Algorithmus mit der Berechnung
        for(int count = 0; count < anzahl_knoten - 1; count++) {

            //Suche den Knoten mit der kleinsten Distanz aus den noch nicht bearbeiteten
            int u = minDistance(distanz, bereitsBesucht);
            //Markiere diesen als besucht
            bereitsBesucht[u] = true;
            //Update die Arrays
            for(int v = 0; v < anzahl_knoten; v++){
                if(!bereitsBesucht[v] && graph[u][v] != 0 && distanz[u] != INF && distanz[u] + graph[u][v] < distanz[v])
                    distanz[v] = distanz[u] + graph[u][v];
            }
        }
        //Gebe Ergebnis aus
        druckeErgebnis(distanz);
    }

    public static void main(String[] args) {

        //Das ist unser Graph als Matrix
        int[][] graph = {
                {0,7,2,3,0,0,0,0,0,0,0,0,0}, //S -> 0
                {7,0,3,0,4,0,0,0,0,0,0,0,0}, //A -> 1
                {2,3,0,0,4,1,0,0,0,0,0,0,0}, //B -> 2
                {3,0,0,0,0,0,0,0,2,0,0,0,0}, //C -> 3
                {0,4,4,0,0,0,5,0,0,0,0,0,0}, //D -> 4
                {0,0,1,0,0,0,3,2,0,0,0,0,0}, //H -> 5
                {0,0,0,0,5,3,0,0,0,0,0,0,0}, //F -> 6
                {0,0,0,0,0,2,0,0,0,0,0,0,2}, //G -> 7
                {0,0,0,2,0,0,0,0,0,4,4,0,0}, //L -> 8
                {0,0,0,0,0,0,0,0,4,0,6,4,0}, //I -> 9
                {0,0,0,0,0,0,0,0,4,6,0,4,0}, //J -> 10
                {0,0,0,0,0,0,0,0,0,4,4,0,5}, //K -> 11
                {0,0,0,0,0,0,0,2,0,0,0,5,0}  //E -> 12
        };
        //Finde die kürzeste Distanz zu allen Punkten, von null ausgehend
        dijkstra(graph, 0);
    }

}
