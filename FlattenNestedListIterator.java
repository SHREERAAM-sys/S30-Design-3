public class FlattenNestedListIterator {
}
/*

    L.C: 341. Flatten Nested List Iterator

    Approach: Controlled Recursion with Stack

    Working:
        initialize a stack and push the current list itretaor to it

        in hasNext()

            check if the current element in the stack has next element
                if no pop from the stack
            assign and check if the next lement in and integer
                return true
            else // the current element would be and list
                add the iterator to the stack

        Time Complexity: O(1) - next
                        O(Depth) - hasNext() the number of times to perform push operation to gat an integer

        Space Complexity: O(Depth) - the maximum number of nested list in the input list
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    //Declaring a stack for controlled recursion
    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEle;


    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator()); //pushing the current list with its native iterator in the stack
    }

    @Override
    public Integer next() {
        return nextEle.getInteger();
    }

    @Override
    public boolean hasNext() {

        while(!s.isEmpty()) {

            //when the native iterator does not have an next element ex: [1,1] iterator is at last index
            if(!s.peek().hasNext()) {
                s.pop();
            }
            else if((nextEle = s.peek().next()).isInteger()) {
                //checking if the next element is an integer, if yes i has next element

                return true;
            }
            else {
                //if the next element is not an integer then it must be an list, push it to the stack
                s.push(nextEle.getList().iterator());
            }
        }

        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
