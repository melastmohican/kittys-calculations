import java.util.*;

/**
 * Kitty has a tree, , consisting of  nodes where each node is uniquely labeled from  to . Her friend Alex gave her  sets, where each set contains  distinct nodes. Kitty needs to calculate the following expression on each set:

 where:

 denotes an unordered pair of nodes belonging to the set.
 denotes the number of edges on the unique path between nodes  and .
 Given  and  sets of  distinct nodes, can you help her calculate the expression for each set? For each set of nodes, print the value of the expression modulo  on a new line.

 Input Format

 The first line contains two space-separated integers describing the respective values of  (the number of nodes in tree ) and  (the number of sets).
 Each of the  subsequent lines contains two space-separated integers,  and , describing an undirected edge between nodes  and .
 The  subsequent lines define each set over two lines in the following format:

 The first line contains an integer, , denoting the size of the set.
 The second line contains  space-separated integers describing the set's elements.
 Constraints

 The sum of  over all  does not exceed .
 All elements in each set are distinct.
 Subtasks

 for  of the maximum score.
 for  of the maximum score.
 for  of the maximum score.
 Output Format

 Print  lines of output where each line  contains the expression for the  query, modulo .

 Sample Input 0

 7 3
 1 2
 1 3
 1 4
 3 5
 3 6
 3 7
 2
 2 4
 1
 5
 3
 2 4 5
 Sample Output 0

 16
 0
 106
 */


public class Solution {

    static class Pair<F, S> implements Comparable {
        final F first;
        final S second;


        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof Pair) {
                Pair pair = (Pair) o;
                return (first != null ? first.equals(pair.second) : pair.first == null) && (second != null ? second.equals(pair.second) : pair.second == null);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
        }

        @Override
        public int compareTo(Object other) {
            Pair<Comparable, Comparable> pair = (Pair<Comparable, Comparable>) other;
            int i = ((Comparable) this.first).compareTo(pair.first);
            if (i != 0) return i;
            i = ((Comparable) this.second).compareTo(pair.second);
            return i;
        }
    }

    private static final int MOD_VALUE = (int) (Math.pow(10, 9) + 7);
    private static final int maxn = (int) (5 * 1e5 + 5);

    private final Set<Integer>[] adj = new HashSet[maxn];
    private final List<Pair<Integer, Integer>>[] g = new List[maxn];

    public Solution() {
        for (int i = 0; i < maxn; i++) {
            adj[i] = new HashSet<Integer>();
            g[i] = new LinkedList<Pair<Integer, Integer>>();
        }
    }

    private final int[][] LCA = new int[maxn][22];
    private final int[] vis = new int[maxn];
    private final int[] sz = new int[maxn];
    private final int[] L = new int[maxn];
    private final int[] inp = new int[maxn];
    private final int[] out = new int[maxn];
    private final boolean[] check = new boolean[maxn];
    private final long[][] dp = new long[2][maxn];
    private final long[] lev = new long[maxn];

    private int lg;

    private double log2(int n) {
        return Math.log(n) / Math.log(2);
    }


    private void dfs() {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(1);
        vis[1] = 1;
        Stack<Integer> S = new Stack<>();
        while (!Q.isEmpty()) {
            int u = Q.poll();
            sz[u] = 1;
            S.push(u);
            for (Integer it : adj[u]) {
                if (vis[it] == 0) {
                    vis[it] = 1;
                    L[it] = L[u] + 1;
                    LCA[it][0] = u;
                    Q.add(it);
                }
            }
        }

        while (!S.isEmpty()) {
            int u = S.pop();
            vis[u] = 0;
            for (Integer it : adj[u]) {
                if (vis[it] == 0)
                    sz[u] += sz[it];
            }
        }

        Q.add(1);
        inp[1] = 1;
        vis[1] = 1;
        while (!Q.isEmpty()) {
            int u = Q.poll();
            S.push(u);
            int s = 0;
            for (Integer it : adj[u]) {
                if (vis[it] == 0) {
                    vis[it] = 1;
                    inp[it] = s + inp[u] + 1;
                    s += sz[it];
                    Q.add(it);
                }
            }
        }

        while (!S.isEmpty()) {
            int u = S.pop();
            vis[u] = 0;
            out[u] = inp[u];
            for (Integer it : adj[u]) {
                if (vis[it] == 0) {
                    out[u] = Math.max(out[u], out[it]);
                }
            }
        }
    }

    private void constructLCA(int n) {
        lg = (int) Math.ceil(log2(n));
        dfs();
        for (int i = 1; i <= lg; i++) {
            for (int j = 1; j <= n; j++) {
                if (LCA[j][i - 1] != 0) {
                    LCA[j][i] = LCA[LCA[j][i - 1]][i - 1];
                }
            }
        }
    }

    private int getLca(int x, int y) {
        if (L[x] < L[y]) {
            x = x + y;
            y = x - y;
            x = x - y;
        }
        for (int i = lg; i >= 0; i--) {
            if (LCA[x][i] != 0 && L[LCA[x][i]] >= L[y])
                x = LCA[x][i];
        }
        if (x == y)
            return x;
        for (int i = lg; i >= 0; i--) {
            if (LCA[x][i] != 0 && LCA[x][i] != LCA[y][i]) {
                x = LCA[x][i];
                y = LCA[y][i];
            }
        }
        return LCA[x][0];
    }

    private boolean anc(int p, int u) {
        return inp[p] <= inp[u] && out[p] >= out[u];
    }

    private long solve(int u) {
        long ans = 0;
        Queue<Integer> Q = new LinkedList<>();
        Q.add(u);
        vis[u] = 1;
        lev[u] = 0;
        Stack<Integer> S = new Stack<>();
        while (!Q.isEmpty()) {
            u = Q.poll();
            S.push(u);
            for (Pair<Integer, Integer> it : g[u]) {
                if (vis[it.first] == 0) {
                    vis[it.first] = 1;
                    lev[it.first] = lev[u] + it.second;
                    Q.add(it.first);
                }
            }
        }

        while (!S.isEmpty()) {
            u = S.pop();
            vis[u] = 0;
            dp[0][u] = check[u] ? u : 0;
            dp[1][u] = check[u] ? 1L * lev[u] * u : 0;
            dp[1][u] %= MOD_VALUE;
            long s = 0;
            for (Pair<Integer, Integer> it : g[u]) {
                if (vis[it.first] == 0) {
                    s += dp[0][it.first];
                    if (s >= MOD_VALUE) s -= MOD_VALUE;
                    dp[0][u] += dp[0][it.first];
                    if (dp[0][u] >= MOD_VALUE) dp[0][u] -= MOD_VALUE;
                    dp[1][u] += dp[1][it.first];
                    if (dp[1][u] >= MOD_VALUE) dp[1][u] -= MOD_VALUE;
                }
            }
            // ok
            for (Pair<Integer, Integer> it : g[u]) {
                if (vis[it.first] == 0) {
                    ans += 1L * (dp[1][it.first] - 1L * dp[0][it.first] * lev[u] % MOD_VALUE) * (s - dp[0][it.first]) % MOD_VALUE;
                    if (ans >= MOD_VALUE) ans -= MOD_VALUE;
                    if (ans < 0) ans += MOD_VALUE;
                }
            }
            if (check[u]) ans += 1L * (dp[1][u] - 1L * lev[u] * dp[0][u] % MOD_VALUE) * u % MOD_VALUE;
            if (ans >= MOD_VALUE) ans -= MOD_VALUE;
            if (ans < 0) ans += MOD_VALUE;
        }
        return ans;
    }

    public void execute(Scanner sc) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        for (int i = 1; i <= n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        constructLCA(n);

        while (q-- > 0) {
            int k = sc.nextInt();
            ArrayList<Pair<Integer, Integer>> Q = new ArrayList<>();
            Set<Integer> K = new HashSet<>();
            for (int i = 1; i <= k; i++) {
                int x = sc.nextInt();
                check[x] = true;
                if (!K.contains(x)) {
                    K.add(x);
                    Q.add(new Pair<>(inp[x], x));
                }
            }
            k = Q.size();
            Collections.sort(Q);
            for (int i = 0; i <= k - 2; i++) {
                int lca = getLca(Q.get(i).second, Q.get(i + 1).second);
                if (!K.contains(lca)) {
                    K.add(lca);
                    Q.add(new Pair<>(inp[lca], lca));
                }
            }
            Collections.sort(Q);
            Stack<Integer> s = new Stack<>();
            s.push(Q.get(0).second);
            for (int i = 1; i < Q.size(); i++) {
                while (!anc(s.peek(), Q.get(i).second)) {
                    s.pop();
                }
                Pair<Integer, Integer> t1 = new Pair(Q.get(i).second, L[Q.get(i).second] - L[s.peek()]);
                g[s.peek()].add(t1);
                Pair<Integer, Integer> t2 = new Pair(s.peek(), L[Q.get(i).second] - L[s.peek()]);
                g[Q.get(i).second].add(t2);
                s.push(Q.get(i).second);
            }
            long ans = solve(Q.get(0).second);
            System.out.println(ans);
            for (Pair<Integer, Integer> it : Q) {
                g[it.second].clear();
                dp[0][it.second] = dp[1][it.second] = lev[it.second] = 0;
                check[it.second] = false;
            }

        }
    }


    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            new Solution().execute(sc);
        }
    }
}
