package lab10;

import java.util.Scanner;
import java.io.*;

public class UndirectedGraph
{
    private int vertexes; //wierzchołki
    private int[][] adjacencyMatrix; //macierz sąsiedztwa
    private List listNeighbourhood[]; //tablica do listy sąsiedztwa
    private int graphDegree; //stopień grafu
    private Queue priorityQueue; //kolejka

    //konstruktor
    public UndirectedGraph(int vertexes)
    {
        this.vertexes = vertexes;
        adjacencyMatrix = new int[vertexes][vertexes];
    }

    //konstruktor
    public UndirectedGraph(String name)
    {
        Scanner myReader = null;
        try
        {
            myReader = new Scanner(new File(name));
        }
        catch (java.io.FileNotFoundException e)
        {
            System.out.println("File not found");
            adjacencyMatrix= new int[0][0];
            return;
        }
        try
        {
            int numberOfVertices = 0;
            if(myReader.hasNextInt())
                numberOfVertices = myReader.nextInt();
            adjacencyMatrix = new int[numberOfVertices][numberOfVertices];

            for(int i=0; i<adjacencyMatrix.length; i++)
            {
                for (int j = 0; j < adjacencyMatrix[i].length; j++)
                {
                    if(myReader.hasNextInt())
                    {
                        adjacencyMatrix[i][j] = myReader.nextInt();
                    }
                }
            }
            myReader.close();
        }
        catch (java.lang.RuntimeException e){
            System.out.println("File read error");
        }
    }

    //zapisywanie do pliku
    public void writeToFile(String name)
    {
        try
        {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(name));
            myWriter.write(this.toString());
            myWriter.close();
        }
        catch (java.io.IOException e)
        {
            System.out.println("File write error");
        }
    }

    //dodanie krawędzi
    public void addBorder(int vertex1,int vertex2)
    {
        adjacencyMatrix[vertex1][vertex2] = 1;
        adjacencyMatrix[vertex2][vertex1] = 1;
    }

    //odejmowanie krawędzi
    public void deleteBorder(int vertex1,int vertex2)
    {
        adjacencyMatrix[vertex1][vertex2] = 0;
        adjacencyMatrix[vertex2][vertex1] = 0;
    }

    //lista sąsiedztwa
    public String adjacencyList()
    {
        String s = "";
        listNeighbourhood = new MyLinkedList[adjacencyMatrix.length];
        for (int i = 0; i < listNeighbourhood.length; i++)
        {
            listNeighbourhood[i] = new MyLinkedList();
        }
        for (int i = 0; i < adjacencyMatrix.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (adjacencyMatrix[i][j] == 1)
                {
                    listNeighbourhood[i].add(j);
                    listNeighbourhood[j].add(i);
                }
            }
        }
        for (int i = 0; i < listNeighbourhood.length; i++)
        {
            s += (i+1) + ": " + listNeighbourhood[i].toString()+ "\n";
        }
        return s;
    }

    //ilosc krawędzi
    public int amountOfBorders()
    {
        int amount = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (adjacencyMatrix[i][j] == 1)
                {
                    amount++;
                }
            }

        }
        return amount;
    }

    //macierz incydencji
    public String incidenceMatrix()
    {
        String s ="";
        int b = 0;
        StringBuilder stringBuilder = new StringBuilder(s);
        int iM[][] = new int [adjacencyMatrix.length][amountOfBorders()];
        for (int i = 0; i < adjacencyMatrix.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if(adjacencyMatrix[i][j] == 1)
                {
                    iM[i][b] = iM[j][b] = 1;
                    b++;
                }
            }
        }

        int len = iM[0].length * 2 + 3;
        for (int i = 0; i < len; i++)
        {
            if(i % 2 != 0 && i > 1)
            {
                stringBuilder.append(i/2);
            }
            else
            {
                stringBuilder.append(" ");
            }
        }
        stringBuilder.append("\n");
        for (int i = 0; i < len; i++)
        {
            stringBuilder.append("-");
        }
        stringBuilder.append("\n");
        for (int i = 0; i < iM.length; i++)
        {
            stringBuilder.append(i+1).append("| ");
            for (int j = 0; j < iM[i].length; j++)
            {
                stringBuilder.append(iM[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String adjacencyMatrix(){
        String s = "";
        StringBuilder sB = new StringBuilder(s);
        int len = adjacencyMatrix.length * 2 + 3;
        for (int i = 0; i < len; i++) {
            if(i % 2 != 0 && i > 1){
                sB.append(i/2);
            }else{
                sB.append(" ");
            }
        }
        sB.append("\n");
        for (int i = 0; i < len; i++) {
            sB.append("-");
        }
        sB.append("\n");
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            sB.append(i+1).append("| ");
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                sB.append(adjacencyMatrix[i][j]).append(" ");
            }
            sB.append("\n");
        }
        return sB.toString();
    }

    //przechodznie grafu wszerz
    public void BFS(int vertice) {
        ListFifoQueue queue = new ListFifoQueue();
        boolean visited[] = new boolean[vertexes];
        visited[vertice] = true;
        queue.enqueue(vertice);
        while (!queue.isEmpty()) {
            vertice = (Integer) queue.dequeue();
            System.out.print(vertice + " ");
            MyLinkedListIterator iterator = listNeighbourhood[vertice].getIterator();
            for (iterator.first(); !iterator.isDone(); iterator.next()) {
                int n = (Integer) iterator.current();

                if (!visited[n]) {
                    visited[n] = true;
                    queue.enqueue(n);
                }
            }
        }
    }

    //przechodzenie grafu wzdłuż
    public void DFS(int vertice) {
        boolean [] visited = new boolean[vertexes];

        DFS_VERT(vertice, visited);
    }

    public void DFS_VERT(int u, boolean visited[]){
        visited[u] = true;
        System.out.print(u + " ");

        MyLinkedListIterator iterator = listNeighbourhood[u].getIterator();

        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            int n = (Integer) iterator.current();

            if (!visited[n]) {
                DFS_VERT(n, visited);
            }
        }
    }

    //stopień grafu
    public int graphVertexDegree()
    {
        for(int i=0;i<adjacencyMatrix.length;i++)
        {
            for(int j=0;j<adjacencyMatrix[i].length;j++)
            {
                if(adjacencyMatrix[i][j] == 1)
                {
                    graphDegree++;
                }
            }
        }
        return graphDegree;
    }

    //wyczyszczenie
    public void clear()
    {
        for(int i = 0; i<adjacencyMatrix.length; i++)
        {
            for(int j = 0; j < adjacencyMatrix.length; j++)
            {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }


    //metoda toString fo zapisu macierzy sąsiedztwa
    public String toString()
    {
        String bufor= "";
        String wiersz = "";
        int k = 0;
        for(int i = 0; i < adjacencyMatrix.length; i++)
        {
            wiersz = k + "| ";
            for(int j = 0; j < adjacencyMatrix.length; j++)
            {
                bufor += adjacencyMatrix[i][j] + "  ";
            }
            bufor +="\n";
            k++;
        }
        return bufor;
    }
}
