import java.util.*;

public class MyDoublyLinkedList<E> extends MyAbstractSequentialList<E> implements Cloneable {
    private Node<E> head = new Node<E>(null);

    /** Create a default list */
    public MyDoublyLinkedList() {
        head.next = head;
        head.previous = head;
    }

    private static class Node<E> {
        E element;
        Node<E> previous;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }


    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head.next;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != head) {
                result.append(", "); // Separate two elements with a comma
            }
        }
        result.append("]"); // Insert the closing ] in the string

        return result.toString();
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        if (index < size / 2)
            for (int i = -1; i < index; i++)
                current = current.next;
        else
            for (int i = size; i > index; i--)
                current = current.previous;
        return current;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> prev = getNode(index - 1);
        Node<E> next = prev.next;
        Node<E> newNode = new Node<E>(e);
        prev.next = newNode;
        next.previous = newNode;
        newNode.previous = prev;
        newNode.next = next;
        size++;
    }

////////////////////////////////////////////// START: clear() finished, but not tested ////////////////////////////////
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        Node<E> current = head.next;

        if(current == null && size == 0) {
            return;
        }

        for(; current != head; current = current.next) {
            E thisElement = current.element;

            if(remove(thisElement)) {
                remove(thisElement);
            }
        }
    }
//////////////////////////////////////////////// END: clear() finished, but not tested ////////////////////////////////

    @Override
    public boolean contains(E o) {
        for (Node<E> current = head.next; current != head; current = current.next) {
            E e = current.element;
            if (o == null ? e == null : o.equals(e))
                return true;
        }
        return false;
    }

    @Override
    public E get(int index) {
        // TODO Auto-generated method stub
        return null;
    }
///////////////////////////////////////// START: indexOf finished, but not tested /////////////////////////////////////
    @Override
    public int indexOf(E e) {
        // TODO Auto-generated method stub
        // Note: Make sure that you check the equality with == for null  objects and with the equals() for others
        if(e == null) {
            throw new NullPointerException("You passed a null value into the indexOf method");
        }

        int i = 0;

        for(Node<E> current = head.next; current != head; current = current.next) {
            E thisElement = current.element;

            if(e.equals(thisElement)) {
                return i;
            }
            else {
                i++;
            }
        }

        return -1;
    }
/////////////////////////////////////////// END: indexOf finished, but not tested /////////////////////////////////////

/////////////////////////////////////// START: lastIndexOf finished, but not tested ///////////////////////////////////
    @Override
    public int lastIndexOf(E e) {
        // TODO Auto-generated method stub
        if(e == null) {
            throw new NullPointerException("You passed a null value into the indexOf method");
        }

        int i = 0, lastFoundIndex = -1;

        for(Node<E> current = head.next; current != head; current = current.next) {
            E thisElement = current.element;

            if(e.equals(thisElement)) {
                lastFoundIndex = i;
            }
            else {
                i++;
            }
        }

        return lastFoundIndex;
    }
////////////////////////////////////////// END: lastIndexOf finished, but not tested //////////////////////////////////



//////////////////////////////////////// START: equals() finished, but not tested /////////////////////////////////////
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(!(obj instanceof MyList)) {
            return false;
        } else if(((MyList) obj).size() != this.size()) {
            return false;
        } else {
            Iterator iterThis = this.iterator();
            Iterator iterOther = ((MyList) obj).iterator();

            while(iterThis.hasNext()) {
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////// NEED TO HANDLE NULL VALUES /////////////////////////////////////////////
////////////////////////////////// Note that lists are allowed to have null elements. /////////////////////////////////
///////////////////////////// Your code should not throw a NullPointException in that case. ///////////////////////////
////////////////////////////// Deal with this issue like you did in the contains, indexOf, ////////////////////////////
///////////////////////////////////////////////// and lastIndexOf methods. ////////////////////////////////////////////
/////////////////////// I.e., you will need to use the == operator to compare references to null, /////////////////////
////////////////////////// but use the equals method to compare actual objects to each other. /////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                if(!(iterThis.next().equals(iterOther.next()))) {
                    return false;
                }
            }
            return true;
        }
    }
/////////////////////////////////////////// END: equals() finished, but not tested ////////////////////////////////////

    @Override
    @SuppressWarnings("unchecked")
    protected MyDoublyLinkedList<E> clone() throws CloneNotSupportedException {
        MyDoublyLinkedList<E> clonedList;
        try{
            // MyDoublyLinkedList<E> clonedList = (MyDoublyLinkedList<E>)super.clone();
            // Node<E> clonedHead = new Node<>(null);
            // clonedHead.next = clonedHead;
            // clonedHead.previous = clonedHead;
            clonedList = new MyDoublyLinkedList<>();


            for(Node<E> current = head.next; current != head; current = current.next){
                clonedList.add(current.element);
            }

            if(head != null){
                throw new RuntimeException(a);

                // return clonedList;
            }
        }
        catch(CloneNotSupportedException exc){
            throw new RuntimeException();
        }

        return clonedList;
        // return super.clone();
    }


/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
    @Override
    public E remove(int index) {
        // TODO Auto-generated method stub

        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("You cannot remove an Element that does not exist");
        } else{

        }

        return null;
    }
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/
/*****************************************************************************************************************************/






    @Override
    public Object set(int index, E e) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        for(Node<E> current = head.next; current != head; current = current.next){
        }

        Node<E> current = head.next;

        for(int i = 0; i < index; i++){
            current = current.next;
        }
        current.element = e;

        return this;
    }

    @Override
    public E getFirst() {
        // TODO Auto-generated method stub
        return head.next.element;
    }

    @Override
    public E getLast() {
        // TODO Auto-generated method stub
        return head.previous.element;
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        // TODO Auto-generated method stub
        add(size - 1, e);
    }

    @Override
    public E removeFirst() {
        // TODO Auto-generated method stub
        return remove(0);
    }

    @Override
    public E removeLast() {
        // TODO Auto-generated method stub
        return remove(size - 1);
    }



    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyDoublyLinkedListIterator(index);
    }

    private static enum ITERATOR_STATE {
        CANNOT_REMOVE, CAN_REMOVE_PREV, CAN_REMOVE_CURRENT
    };

    private class MyDoublyLinkedListIterator implements ListIterator<E> {
        private Node<E> current; // node that holds the next element in the
        // iteration
        private int nextIndex; // index of current
        ITERATOR_STATE iterState = ITERATOR_STATE.CANNOT_REMOVE;

        private MyDoublyLinkedListIterator(int index) {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException("iterator index out of bounds");
            current = getNode(index);
            nextIndex = index;
        }

        @Override
        public void add(E arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public boolean hasPrevious() {
            // TODO Auto-generated method stub
            return (nextIndex > -size);
        }

        @Override
        public E next() {
            if (nextIndex >= size)
                throw new NoSuchElementException();
            E returnVal = current.element;
            current = current.next;
            nextIndex++;
            iterState = ITERATOR_STATE.CAN_REMOVE_PREV;
            return returnVal;
        }

        @Override
        public int nextIndex() {
            // TODO Auto-generated method stub
            return 0;
        }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////// START: previous() finished, but not tested (check value of current and iterState ////////////
        @Override
        public E previous() {
            // TODO Auto-generated method stub
            if(nextIndex <= -size){
                throw new NoSuchElementException();
            }
            E returnVal = current.element;
            current = current.previous;
            nextIndex--;
            iterState = ITERATOR_STATE.CAN_REMOVE_CURRENT;
            return returnVal;
        }
/////////////////////////////// END: previous() finished, but not tested (check value of current and iterState ////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        @Override
        public int previousIndex() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void remove() {
            switch (iterState) {
                case CANNOT_REMOVE:
                    // ...
                    break;
                case CAN_REMOVE_PREV:
                    // ...
                    break;
                case CAN_REMOVE_CURRENT:
                    // ...
                    break;
            }

        }

        @Override
        public void set(E arg0) {
            // TODO Auto-generated method stub
            current.element = arg0;
        }
    }
}