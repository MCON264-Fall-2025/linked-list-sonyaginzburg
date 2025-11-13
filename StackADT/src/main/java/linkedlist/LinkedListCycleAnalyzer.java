package linkedlist;

import org.apache.commons.lang3.NotImplementedException;
import support.CycleInfo;
import support.LLNode;

public class LinkedListCycleAnalyzer<T> {
    public static <T> CycleInfo detectCycleInfo(LLNode<T> head){
        if (head == null) return new CycleInfo(-1,0);
        LLNode<T> slow = head;
        LLNode<T> fast = head;
        boolean hasCycle = false;

        //cycle detection
        while (fast != null && fast.getLink() != null) {
            slow = slow.getLink();
            fast = fast.getLink().getLink();
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return new CycleInfo(-1,0);
        }
        //entry point of the cycle
        LLNode<T>  ptr1 = head;
        LLNode<T>  ptr2 = slow;
        int entryIndex = 0;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.getLink();
            ptr2 = ptr2.getLink();
            entryIndex++;
        }
        int length = 1;
        LLNode<T> current = ptr2.getLink();
        while (current != ptr2) {
            current = current.getLink();
            length++;
        }
        return new CycleInfo(entryIndex, length);
    }
}
