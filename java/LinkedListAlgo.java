/**
 * 1) 单链表反转
 * 2) 链表中环的检测
 * 3) 两个有序的链表合并
 * 4) 删除链表倒数第n个结点
 * 5) 求链表的中间结点
 *
 * Author: Zheng
 */
public class LinkedListAlgo {

  public static void main(String[] args) {
    Node node1 = createNode(1);
    Node node2 = createNode(2);
    node1.next=node2;
    System.out.println("---原始链表");
    printAll(node1);
    System.out.println("---单链表反转结果");
    reverse(node1);
    printAll(node2);
    System.out.println("---");
    printAll(node1);
  }

  // 单链表反转
  public static Node reverse(Node list) {
    Node curr = list, pre = null;
    while (curr != null) {
      Node next = curr.next;
      curr.next = pre;
      pre = curr;
      curr = next;
    }
    return pre;
  }

  // 检测环
  //假设有两个学生A和B在跑道上跑步，两人从相同起点出发，假设A的速度为2m/s，B的速度为1m/s,结果会发生什么？答案很简单，A绕了跑道一圈之后会追上B！
  //将这个问题延伸到链表中，跑道就是链表，我们可以设置两个指针，a跑的快，b跑的慢，如果链表有环，那么当程序执行到某一状态时，a==b。如果链表没有环，程序会执行到a==NULL，结束。
  public static boolean checkCircle(Node list) {
    if (list == null) return false;

    Node fast = list.next;
    Node slow = list;

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;

      if (slow == fast) return true;
    }

    return false;
  }

  // 有序链表合并
  // public static Node mergeSortedLists(Node la, Node lb) {
    // if (la == null) return lb;
    // if (lb == null) return la;

    // Node p = la;
    // Node q = lb;
    // Node head;
    // if (p.data < q.data) {
    //   head = p;
    //   p = p.next;
    // } else {
    //   head = q;
    //   q = q.next;
    // }
    // Node r = head;

    // while (p != null && q != null) {
    //   if (p.data < q.data) {
    //     r.next = p;
    //     p = p.next;
    //   } else {
    //     r.next = q;
    //     q = q.next;
    //   }
    //   r = r.next;
    // }

    // if (p != null) {
    //   r.next = p;
    // } else {
    //   r.next = q;
    // }

    // return head;
   //}
  	
  	//-----------------------------------------

    // 有序链表合并 Leetcode 21 
    /**
 	* Definition for singly-linked list.
 	*/
    public class ListNode {
 	    int val;
 	    ListNode next;
 	   ListNode(int x) { val = x; }
 	}

   public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode soldier = new ListNode(0); //利用哨兵结点简化实现难度 技巧三
        ListNode p = soldier;
        
        while ( l1 != null && l2 != null ){
            if ( l1.val < l2.val ){
                p.next = l1;
                l1 = l1.next;
            }
            else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        
        if (l1 != null) { p.next = l1; }
        if (l2 != null) { p.next = l2; }
        return soldier.next;   
    }


  // 删除倒数第K个结点
  public static Node deleteLastKth(Node list, int k) {
    Node fast = list;
    int i = 1;
    while (fast != null && i < k) {
      fast = fast.next;
      ++i;
    }

    if (fast == null) return list;

    Node slow = list;
    Node prev = null;
    while (fast.next != null) {
      fast = fast.next;
      prev = slow;
      slow = slow.next;
    }

    if (prev == null) {
      list = list.next;
    } else {
      prev.next = prev.next.next;
    }
    return list;
  }

  // 求中间结点
  public static Node findMiddleNode(Node list) {
    if (list == null) return null;

    Node fast = list;
    Node slow = list;

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    return slow;
  }

  public static void printAll(Node list) {
    Node p = list;
    while (p != null) {
      System.out.print(p.data + " ");
      p = p.next;
    }
    System.out.println();
  }

  public static Node createNode(int value) {
    return new Node(value, null);
  }

  public static class Node {
    private int data;
    private Node next;

    public Node(int data, Node next) {
      this.data = data;
      this.next = next;
    }

    public int getData() {
      return data;
    }
  }

}
