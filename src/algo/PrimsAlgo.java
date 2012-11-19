package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Prim's algorithm realization.
 * User: Vitaliy.Supryhan
 * Date: 11/3/12
 * Time: 12:25 AM
 */
// Some unnecessary comment was added.
public class PrimsAlgo
{
    private List<Node> listNodes;
    private List<Node> resultNodesAlgorithm;

    public PrimsAlgo()
    {
        resultNodesAlgorithm = new LinkedList<Node>();
        listNodes = new LinkedList<Node>();
        System.out.print("Hello, you will fill in values of your graph in this dialog. Type \"exit\" to finish.\n Input first vertex (only name): ");
        try
        {
            registerVertex();
        } catch (IOException e)
        {
            System.out.println("Exception while input.");
            return;
        }

//        Node a = new Node("a");
//        Node b = new Node("b");
//        Node c = new Node("c");
//        Node d = new Node("d");
//        Node e = new Node("e");
//        Node f = new Node("f");
//        Node g = new Node("g");
//
//        a.connect(b, 7);
//        a.connect(d, 5);
//        b.connect(a, 7);
//        b.connect(c, 8);
//        b.connect(e, 7);
//        b.connect(d, 9);
//        c.connect(b, 8);
//        c.connect(e, 5);
//        d.connect(a, 5);
//        d.connect(b, 9);
//        d.connect(e, 15);
//        d.connect(f, 6);
//        e.connect(c, 5);
//        e.connect(b, 7);
//        e.connect(d, 15);
//        e.connect(f, 8);
//        e.connect(g, 9);
//        f.connect(d, 6);
//        f.connect(e, 8);
//        f.connect(g, 11);
//        g.connect(e, 9);
//        g.connect(f, 11);
//
//        listNodes.add(a);
//        listNodes.add(b);
//        listNodes.add(c);
//        listNodes.add(d);
//        listNodes.add(e);
//        listNodes.add(f);
//        listNodes.add(g);

        algorithm();

    }

    private void registerVertex() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = "";
        if ((userInput = bufferedReader.readLine()).equals("exit"))
            return;
        Node newNode = new Node(userInput);
        listNodes.add(newNode);
        System.out.print("Input next vertex (only name): ");
        registerVertex();
        System.out.println("Input all connections for vertex " + userInput + " (example: <name> <length>), (after finish type \"exit\"): ");
        while (!(userInput = bufferedReader.readLine()).equals("exit"))
        {
            registerEdge(newNode, userInput);
        }
    }

    private void registerEdge(Node node, String str)
    {
//        int positionOfConnector = listNodes.indexOf(node);
        String[] userInput = str.split(" ");
        String connecteeName = userInput[0];
        int positionOfConnectee = listNodes.indexOf(getNodeFromListByName(connecteeName));
        Node connectee = listNodes.get(positionOfConnectee);
        int length = Integer.parseInt(userInput[1]);
        node.connect(connectee, length);
    }

    private Node getNodeFromListByName(String name)
    {
        for (Node n : listNodes)
            if (n.name.equals(name))
                return n;
        return null;
    }

    private void algorithm()
    {
        if (listNodes.size() == 0) return;
        resultNodesAlgorithm.add(listNodes.get(0));
        while (resultNodesAlgorithm.size() < listNodes.size())
        {
            int minLength = Integer.MAX_VALUE;
            Node minNode = null;
            SingleConnection minSingleConnection = null;
            for (Node n : resultNodesAlgorithm)
            {
                for (SingleConnection singleConnection : n.listConnections)
                {
                    boolean usedConnection = singleConnection.isConnectionUsed;
                    boolean goBack = resultNodesAlgorithm.contains(singleConnection.node);
                    if (!usedConnection & !goBack)
                    {
                        if (minLength > singleConnection.length)
                        {
                            minLength = singleConnection.length;
                            minNode = singleConnection.node;
                            minSingleConnection = singleConnection;
                        }
                    }
                }
            }
            minNode.isNodeUsed = true;
            minSingleConnection.isConnectionUsed = true;
            resultNodesAlgorithm.add(minNode);
        }
        System.out.print("Result: ");
        for (Node n : resultNodesAlgorithm)
        {
            for (SingleConnection singleConnection : n.listConnections)
            {
                if (singleConnection.isConnectionUsed)
                    System.out.print(n.name + "-" + singleConnection.node.name + "; ");
            }
        }
    }

    class Node
    {
        String name;
        List<SingleConnection> listConnections;
        boolean isNodeUsed = false;

        public Node(String name)
        {
            this.name = name;
            listConnections = new LinkedList<SingleConnection>();
        }

        public void connect(Node node, int length)
        {
            listConnections.add(new SingleConnection(node, length));
        }
    }

    class SingleConnection
    {
        Node node;
        int length;
        boolean isConnectionUsed = false;

        public SingleConnection(Node node, int length)
        {
            this.node = node;
            this.length = length;
        }
    }

    public static void main(String[] args)
    {
        new PrimsAlgo();
    }
}
