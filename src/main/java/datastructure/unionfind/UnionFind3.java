package datastructure.unionfind;

/**
 * @author: Alex
 * @date: 2018/12/14 17:54
 * description:
 */
public class UnionFind3 implements UF {

    int[] parent;
    int[] sz;

    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
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
        if(sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
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
