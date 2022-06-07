package zwTest;
/**
 *  单链表反转
 *  @author: zhaowei
 */
public class LinkedListAlgoTest {

    public static void main(String[] args){
        Node list = createList(3);
        printList(list);
        Node reverseList = reverse(list);
        printList(reverseList);

        System.out.println("正常无环链表检查结果="+checkCircle(list));
        Node circleList = createCircleList();
        System.out.println("有环链表检查结果="+checkCircle(circleList));
    }

    public static boolean checkCircle(Node list){
        Node slow=list;
        Node fast=list.next;
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                System.out.println("快的追上慢的了，有环");
                return true;
            }
        }
        return false;
    }

    /**
     * 故意创建有环链表
     * @return
     */
    public static Node createCircleList(){
        Node node1=new Node();
        node1.data=1;
        Node node2=new Node();
        node2.data=2;
        Node node3=new Node();
        node3.data=3;
        Node node4=new Node();
        node3.data=4;

        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node2;

        return node1;
    }


    /**
     * 反转单链表
     * @param list
     * @return
     */
    public static Node reverse(Node list){
        Node curr=list;
        Node pre=null;
        while(curr!=null){
            Node next=curr.next;
            curr.next=pre;
            pre=curr;
            curr=next;
        }
        return pre;
    }

    /**
     * 构建节点
     */
    static class Node{
        private int data;
        private Node next;
    }

    /**
     * 创建单链表，输入3，输出链表为 1 2 3
     * @param length
     * @return
     */
    public static Node createList(int length){
        Node next=null;
        for(int i=length;i>=1;i--){
            Node node=new Node();
            node.data=i;
            node.next=next;
            next=node;
            if(i==1){
                return node;
            }
        }
        return null;
    }

    /**
     * 打印链表
     * @param list
     */
    public static void printList(Node list){
        Node p=list;
        while(p!=null){
            System.out.print(p.data+" ");
            p=p.next;
        }
        System.out.println();
    }

}

