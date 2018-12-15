package datastructure.unionfind;

/**
 * @author: Alex
 * @date: 2018/12/14 17:54
 * description: 路径压缩优化
 */
public class UnionFind5 implements UF {

    int[] parent;
    int[] rank;

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] = rank[pRoot] + 1;
        }
    }

    private int find(int id) {
        if (id < 0 || id >= parent.length) {
            throw new IllegalArgumentException("index is out of range");
        }
        while (parent[id] != id) {
            //路径压缩  元素的父节点改成元素父节点的父节点
            parent[id] = parent[parent[id]];
            id = parent[id];
        }
        return id;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

}
