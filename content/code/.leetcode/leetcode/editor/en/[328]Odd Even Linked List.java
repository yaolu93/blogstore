//Given the head of a singly linked list, group all the nodes with odd indices 
//together followed by the nodes with even indices, and return the reordered list. 
//
//
// The first node is considered odd, and the second node is even, and so on. 
//
// Note that the relative order inside both the even and odd groups should 
//remain as it was in the input. 
//
// You must solve the problem in O(1) extra space complexity and O(n) time 
//complexity. 
//
// 
// Example 1: 
// 
// 
//Input: head = [1,2,3,4,5]
//Output: [1,3,5,2,4]
// 
//
// Example 2: 
// 
// 
//Input: head = [2,1,3,5,6,4,7]
//Output: [2,3,6,7,1,5,4]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the linked list is in the range [0, 10⁴]. 
// -10⁶ <= Node.val <= 10⁶ 
// 
//
// Related Topics Linked List 👍 9823 👎 524


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)
////链表的奇偶重排-双指针
//public class sort5 {
//    public static void main(String[] args) {
//        ListNode head = creatList();
//        printList(head.next);
//        ListNode list = oddEvenList(head.next);
//        printList(list);
//    }
//
//    public static ListNode oddEvenList (ListNode head) {
//        if(head==null) return null;
//        ListNode J = head;// 扫描奇数结点
//        ListNode O = head.next;// 扫描偶数结点
//        ListNode Ohead = O; // 保存偶链的头结点
//        //修改指针指向
//        while(J!=null && O!=null){
//            J.next = O.next;//O.next是下一个奇数结点
//            J = J.next;//J指向下一个奇数结点
//            O.next = J.next;// 下一个奇数结点的next是下一个偶数结点
//            O = O.next;//O指向下一个偶数结点
//        }
//        // 奇链连上偶链
//        J.next = Ohead;
//        return head;
//    }
//
//    public static ListNode creatList(){
//        Scanner scanner = new Scanner(System.in);
//        ListNode head = new ListNode(-1);
//        ListNode last = head;
//        while(scanner.hasNext()){
//            int num = scanner.nextInt();
//            if(num==-1) break;//输入-1结束输
//            ListNode node = new ListNode(num);
//            last.next = node;
//            last = last.next;
//        }
//        return head;
//    }
//
//    public static void printList(ListNode head){
//        while(head!=null){
//            System.out.print(head.val + "->");
//            head = head.next;
//        }
//        System.out.println();
//    }
//}