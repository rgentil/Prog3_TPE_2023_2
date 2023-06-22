package tudai.prog3.util;

public class UnionFind2 {
	private int[] parent;
	private int[] rank;
	private int n;

	public UnionFind2(int n) {
		this.n = n;
		parent = new int[n];
		rank = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	public void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);

		if (rank[xRoot] < rank[yRoot]) {
			parent[xRoot] = yRoot;
		} else if (rank[xRoot] > rank[yRoot]) {
			parent[yRoot] = xRoot;
		} else {
			parent[yRoot] = xRoot;
			rank[xRoot]++;
		}
	}

	public void disconnect(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);

		if (xRoot == yRoot) {
			parent[yRoot] = yRoot; // Se establece el padre de la raíz como sí misma
		}
	}

	public boolean connected() {
		int root = find(0);
		for (int i = 1; i < parent.length; i++) {
			if (find(i) != root) {
				return false;
			}
		}
		return true;
	}

}
