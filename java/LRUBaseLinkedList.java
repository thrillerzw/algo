/**
 * 基于单链表LRU算法（java）
 *
 * @author hoda
 * @create 2018-12-17
 */
public class LRUBaseLinkedList<T> {

    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY =3;

    /**
     * 头结点
     */
    private SNode<T> headNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);

        // 链表中存在，删除原数据，再插入到链表的头部
        if (preNode != null) {
            deleteElemOptim(preNode);
            intsertElemAtBegin(data);
        } else {
            if (length >= this.capacity) {
                //删除尾结点
                deleteElemAtEnd();
            }
            intsertElemAtBegin(data);
        }
    }

    /**
     * 删除preNode结点下一个元素
     *
     * @param preNode
     */
    private void deleteElemOptim(SNode preNode) {
        preNode.next=preNode.next.next;
        length--;
    }

    /**
     * 链表头部插入节点
     *
     * @param data
     */
    private void intsertElemAtBegin(T data) {
        headNode.next=(new SNode(data, headNode.next));
        length++;
    }

    /**
     * 获取查找到元素的前一个结点
     *
     * @param data
     * @return
     */
    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.next != null) {
            if (data.equals(node.next.data)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        // 空链表直接返回
        if (ptr.next == null) {
            return;
        }

        // 倒数第二个结点
        while (ptr.next.next != null) {
            ptr = ptr.next;
        }

        ptr.next=null;
        length--;
    }

    private void printAll() {
        SNode p = headNode.next;
        while (p != null) {
            System.out.print(p.data + ",");
            p = p.next;
        }
        System.out.println();
    }

    public class SNode<T> {

        private T data;

        private SNode next;

        public SNode(T data) {
            this.data = data;
        }

        public SNode(T data, SNode next) {
            this.data = data;
            this.next = next;
        }

        public SNode() { }
    }
    //添加 1,2,3 输出 3,2,1 。再添加4，输出4,3,2,淘汰了1
    public static void main(String[] args) {
        LRUBaseLinkedList<String> list = new LRUBaseLinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.printAll();
       /* Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.next());
            list.printAll();
        }*/
    }
}
