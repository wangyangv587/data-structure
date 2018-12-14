package datastructure.tree;

/**
 * @author: Alex
 * @date: 2018/12/12 15:55
 * description:
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr,Merger<E> merger){
        data = (E[])new Object[arr.length];
        for(int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0,0,data.length - 1);
        this.merger = merger;
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(data.length == 0)
            throw new IllegalArgumentException("data is empty");

        return data[index];
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }
//          [0,1,2]
//        [0,1]   [2]
    /**
     *
     * @param treeIndex 根节点索引 0
     * @param l 跟节点起始下标 0
     * @param r 跟节点结束下标 2
     */
    private void buildSegmentTree(int treeIndex,int l, int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int left = leftChild(treeIndex);
        int right = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildSegmentTree(left, l, mid);
        buildSegmentTree(right, (mid + 1), r);
        tree[treeIndex] = merger.merger(tree[left],tree[right]);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("[");
        for(int i = 0; i < data.length; i++){
            if(data[i] != null){
                res.append(data[i]);
            }else{
                res.append("null");
            }
            if(i != data.length - 1){
                res.append(",");
            }
        }
        res.append("]");
        return "";
    }

    public static void main(String[] args) {
        Integer[] arr = {-2,5,0,-1,3};
        SegmentTree<Integer> tree = new SegmentTree<>(arr,(a,b) -> {
            return ((a == null ? 0 : a) + (b == null ? 0 : b));
        });
        System.out.println(tree);
    }
}
