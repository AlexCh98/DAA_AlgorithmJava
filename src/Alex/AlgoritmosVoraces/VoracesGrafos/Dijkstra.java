package Alex.AlgoritmosVoraces.VoracesGrafos;

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Arco> graph[] = new List[n + 1];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int w = sc.nextInt();
            graph[v1].add(new Arco(v1, v2, w));
            graph[v2].add(new Arco(v2, v1, w));
        }

        findShortestPath(1, graph);

    }

    public static class Arco {
        public int from;
        public int to;
        public int weight;

        public Arco(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static int[] findShortestPath(int v_start, List<Arco>[] graph) {
        boolean[] visited = new boolean[graph.length];
        int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);
        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        Arco fake_Arco = new Arco(-1, v_start, 0);
        Comparator<Arco> compareWeight = Comparator.comparing(Arco -> Arco.weight);
        PriorityQueue<Arco> pq = new PriorityQueue<>(compareWeight);
        pq.add(fake_Arco);
        while (!pq.isEmpty()) {
            Arco e1 = pq.poll();
            if (!visited[e1.to]) {
                visited[e1.to] = true;
                distances[e1.to] = e1.weight;
                previous[e1.to] = e1.from;
                for (Arco e2 : graph[e1.to]) {
                    if (e1.weight + e2.weight < distances[e2.to]) {
                        pq.add(new Arco(e2.from, e2.to, e1.weight + e2.weight));
                        distances[e2.to] = e1.weight + e2.weight;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(previous));
        System.out.println(Arrays.toString(distances));
        return previous;
    }
}

/*
7
11
1 3 1
1 4 2
1 7 6
2 5 2
2 6 4
2 7 7
3 7 5
3 4 3
4 5 1
4 6 9
5 7 8







 */