
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
//Bu class ın içinde düğüm,kenar ve maaliyetleri(1) verdim.

public class GraphShow {
//düğümlerin x,y lerini ve kaçıncı düğüm olduğunu tutuyor
    static int[][] nodePosition = new int[78][3];
    //haritamızı tutuyor
    int dizi[][] = Karakter.getMap();

    public GraphShow(int goodX, int goodY, int badX, int badY) {
        //graf oluşturduk
        Graph graph = new Graph(true);
        //düğümleri oluşturduk
        Node[] node = new Node[78];
        int x = 0;
        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[0].length; j++) {
                if (dizi[i][j] == 1) {

                    nodePosition[x][0] = i;
                    nodePosition[x][1] = j;
                    nodePosition[x][2] = x;
                    node[x] = new Node(x, x);
                    x++;
                }
            }
        }
        x = 0;
        //komşu kenarları ekliyoruz
        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[0].length; j++) {
                if (dizi[i][j] == 1) {
                    if (j + 1 < 13 && dizi[i][j + 1] == 1) {
                        graph.addEdge(node[x], node[x + 1], 1);
                    }
                    if (j - 1 >= 0 && dizi[i][j - 1] == 1) {
                        graph.addEdge(node[x], node[x - 1], 1);
                    }
                    if (i + 1 < 11 && dizi[i + 1][j] == 1) {
                        int a = 0;
                        for (int k = 0; k < 78; k++) {
                            if (i + 1 == nodePosition[k][0] && j == nodePosition[k][1]) {
                                a = nodePosition[k][2];
                                break;
                            }
                        }
                        graph.addEdge(node[x], node[a], 1);
                    }
                    if (i - 1 >= 0 && dizi[i - 1][j] == 1) {
                        int a = 0;
                        for (int k = 0; k < 78; k++) {
                            if (i - 1 == nodePosition[k][0] && j == nodePosition[k][1]) {
                                a = nodePosition[k][2];
                                break;
                            }
                        }
                        graph.addEdge(node[x], node[a], 1);
                    }
                    x++;
                }
            }
        }
        //iyi ve kötü karakterin x,y sine göre hesaplanması gereken yolu buluyor
        int aaa = -1;
        int bbb = -1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                for (int k = 0; k < 78; k++) {
                    if (nodePosition[k][0] == goodX && nodePosition[k][1] == goodY) {
                        aaa = nodePosition[k][2];
                    }
                    if (nodePosition[k][0] == badX && nodePosition[k][1] == badY) {
                        bbb = nodePosition[k][2];
                    }
                    if (aaa != -1 && bbb != -1) {
                        break;
                    }
                }
            }
        }
        graph.DijkstraShortestPath(node[aaa], node[bbb]);
    }
}
//kenarların maaliyet lerine bakıyor
//bizimki ağırlıksız graf olduğundan çok önemli değil ama yine de kullandım

class Edge implements Comparable<Edge> {

    Node source;
    Node destination;
    double weight;

    Edge(Node s, Node d, double w) {
        source = s;
        destination = d;
        weight = w;
    }

    /*public String toString() {
        return String.format("(%s -> %s, %f)", source.id, destination.id, weight);
    }*/
    public int compareTo(Edge otherEdge) {
        if (this.weight > otherEdge.weight) {
            return 1;
        } else {
            return -1;
        }
    }
}
//ilk başta node ler ziyaret edilmemiş,ziyaret edilip edilmediğini tutuyor.

class Node {

    int node;
    int nodeId;
    private boolean visited;
    LinkedList<Edge> edges;

    Node(int n, int id) {
        this.node = n;
        this.nodeId = id;
        visited = false;
        edges = new LinkedList<>();
    }

    boolean isVisited() {
        return visited;
    }

    void visit() {
        visited = true;
    }
}
//hesap yapan class ımız bu class

class Graph {

    private Set<Node> nodes;
    private boolean directed;

    Graph(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    public void addEdge(Node source, Node destination, double weight) {
        nodes.add(source);
        nodes.add(destination);
        addEdgeHelper(source, destination, weight);

        if (!directed && source != destination) {
            addEdgeHelper(destination, source, weight);
        }
    }

    private void addEdgeHelper(Node a, Node b, double weight) {
        for (Edge edge : a.edges) {
            if (edge.source == a && edge.destination == b) {
                edge.weight = weight;
                return;
            }
        }
        a.edges.add(new Edge(a, b, weight));
    }

    public void DijkstraShortestPath(Node start, Node end) {
        int[][] shortestPathArray = new int[11][13];
        HashMap<Node, Node> changedAt = new HashMap<>();
        //put (Object key, Object value): Anahtar — değer ikilisini kayıt eder.
        changedAt.put(start, null);
        HashMap<Node, Double> shortestPathMap = new HashMap<>();
        for (Node node : nodes) {
            if (node == start) {
                shortestPathMap.put(start, 0.0);
            } else {
                shortestPathMap.put(node, Double.POSITIVE_INFINITY);
            }
        }
        for (Edge edge : start.edges) {
            shortestPathMap.put(edge.destination, edge.weight);
            changedAt.put(edge.destination, start);
        }

        start.visit();
        int say = 1;
        while (true) {
            Node currentNode = closestReachableUnvisited(shortestPathMap);
            if (currentNode == null) {
                System.out.println("Böyle bir yol yok " + start.nodeId + " ile " + end.nodeId+" arasında ");
                return;
            }
            if (currentNode == end) {
                System.out.println("En kısa yol "
                        + start.nodeId + " ile " + end.nodeId + " arasındaki:");

                Node child = end;
                String path = "" + end.nodeId;
                while (true) {
                    Node parent = changedAt.get(child);
                    if (parent == null) {
                        break;
                    }
                    path = parent.nodeId + " " + path;
                    child = parent;
                    say++;
                }
                String[] bol = path.split(" ");
                int[] dizi = new int[say];
                int[][] dizim = new int[say][2];
                for (int i = 0; i < say; i++) {
                    dizi[i] = Integer.valueOf(bol[i]);
                    dizim[i][0] = GraphShow.nodePosition[dizi[i]][0];
                    dizim[i][1] = GraphShow.nodePosition[dizi[i]][1];
                    for (int j = 0; j < 11; j++) {
                        for (int k = 0; k < 13; k++) {

                            if (dizim[i][0] == j && dizim[i][1] == k) {
                                shortestPathArray[j][k] = 1;

                            }
                        }
                    }
                }
                System.out.println(path);
                Dusman.setMatris(shortestPathArray);
                System.out.println("En kısa yolun maaliyeti: " + shortestPathMap.get(end));
                return;
            }
            currentNode.visit();
            for (Edge edge : currentNode.edges) {
                if (edge.destination.isVisited()) {
                    continue;
                }
                if (shortestPathMap.get(currentNode)
                        + edge.weight
                        < shortestPathMap.get(edge.destination)) {
                    shortestPathMap.put(edge.destination,
                            shortestPathMap.get(currentNode) + edge.weight);
                    changedAt.put(edge.destination, currentNode);
                }
            }
        }
    }

    private Node closestReachableUnvisited(HashMap<Node, Double> shortestPathMap) {

        double shortestDistance = Double.POSITIVE_INFINITY;
        Node closestReachableNode = null;
        for (Node node : nodes) {
            if (node.isVisited()) {
                continue;
            }
            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.POSITIVE_INFINITY) {
                continue;
            }

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }
}
