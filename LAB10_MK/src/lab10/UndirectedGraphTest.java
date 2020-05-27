package lab10;

public class UndirectedGraphTest
{
    public static void main(String[] args)
    {
        UndirectedGraph graph = new UndirectedGraph(5);

        System.out.println("Macierz Sąsiedztwa: "+"\n");
        graph.addBorder(1,2);
        graph.addBorder(1,4);
        graph.addBorder(1,1);
        graph.addBorder(3,4);
        System.out.println(graph.toString());
        System.out.println(graph.adjacencyMatrix());
        System.out.println("Stopień grafu wynosi: "+graph.graphVertexDegree()+"\n");
        System.out.println("Ilość krawędzi wynosi: "+graph.amountOfBorders()+"\n");
        System.out.println("Macierz Incydencji: "+"\n"+graph.incidenceMatrix());
        System.out.println("--------------");
        System.out.println("Lista sąsiedztwa: ");
        System.out.print(graph.adjacencyList());
        System.out.println();
        System.out.println("Przechodznie grafu BFS");
        graph.BFS(0);
        System.out.println();
        System.out.println("Przechodzenie grafu DFS");
        graph.DFS(0);
        System.out.println();
        graph.writeToFile("undirectedGraph.txt");
        UndirectedGraph graph1 = new UndirectedGraph("text.txt");
        System.out.println("Macierz sąsiedztwa z pliku: ");
        System.out.println(graph1.adjacencyMatrix());
        //graph.deleteBorder(1,4);
        //System.out.println("Po usunięciu: ");
        //System.out.println(graph);
    }
}
