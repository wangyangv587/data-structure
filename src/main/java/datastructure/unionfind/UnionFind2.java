package datastructure.unionfind;

/**
 * @author: Alex
 * @date: 2018/12/14 17:24
 * description: 快速查找Quick find
 */
public class UnionFind2 implements UF {

    int[] parent;

    public UnionFind2(int size){
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
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

        if(pRoot == qRoot){
            return;
        }

        parent[qRoot] = qRoot;
    }

    private int find(int id){
        if(id < 0 || id >= parent.length){
            throw new IllegalArgumentException("index is out of range");
        }
        while (parent[id] != id){
            id = parent[id];
        }
        return id;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
