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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean contains(E o) {
        for (Node<E> current = head.next; current != head; current = current.next) {
            E e = current.element;
            if (o == null ? e == null : o.equals(e))
                return true;
        }
        return false;
    }

    /*                                 READY FOR GRADING: public E get(int index) {...}                                  */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// START: get(int index) finished, but not tested ////////////////////////////////
    @Override
    public E get(int index) {
        // TODO Auto-generated method stub
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException in method: " +
                    "\npublic E get(int index){...} method \nAcceptable values: 0 - " + size +
                    "\nPassed in value: " + index);
        } else{
            return getNode(index).element;
        }
    }
//////////////////////////////////////// END: get(int index) finished, but not tested /////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*                                  READY FOR GRADING: public int lastIndexOf(E e)                                   */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// START: indexOf finished, but not tested /////////////////////////////////////
    @Override
    public int indexOf(E e) {
        // TODO Auto-generated method stub
        // Note: Make sure that you check the equality with == for null  objects and with the equals() for others
        if(e == null) {
            throw new NullPointerException("You passed a null value into the indexOf method");
        } else {

            int i = 0;

            for (Node<E> current = head.next; current != head; current = current.next) {
                E thisElement = current.element;

                if (e.equals(thisElement)) {
                    return i;
                }
                else {
                    i++;
                }
            }

            throw new NoSuchElementException("The element passed into: \npublic int indexOf(E e) {...} " +
                    "\ndoes not exist in the Circular Doubly LinkedList");
        }
    }
/////////////////////////////////////////// END: indexOf finished, but not tested /////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*                                  READY FOR GRADING: public int lastIndexOf(E e)                                   */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// START: lastIndexOf finished, but not tested ///////////////////////////////////
    @Override
    public int lastIndexOf(E e) {
        // TODO Auto-generated method stub
        if(e == null) {
            throw new NullPointerException("You passed a null value into: public int lastIndexOf(E e) {...}");
        }

        int i = 0, lastFoundIndex = -1;

        for(Node<E> current = head.next; current != head; current = current.next) {
            E thisElement = current.element;

            if(e.equals(thisElement)) {
                lastFoundIndex = i+1;
            }
            else {
                i++;
            }
        }

        if(lastFoundIndex == -1){
            throw new NoSuchElementException();
        }

        return lastFoundIndex;
    }
////////////////////////////////////////// END: lastIndexOf finished, but not tested //////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// START: equals() finished, but not tested /////////////////////////////////////
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(!(obj instanceof MyDoublyLinkedList)) { // instanceOf MyList
            return false;
        } else if(((MyDoublyLinkedList) obj).size() != this.size()) {
            return false;
        } else {
            ListIterator<E> iterThis = this.listIterator(0);
            ListIterator<E> iterOther = ((MyDoublyLinkedList<E>)obj).listIterator(0);

            int counter = 0;

            while(iterThis.hasNext()) {
                E elementThis = iterThis.next();
                E elementOther = iterOther.next();

                if(elementThis == null && elementOther != null){
                    return false;
                } else if(elementThis != null && elementOther == null){
                    return false;
                } else if(elementThis != null && elementThis.equals(elementOther)){
                    return false;
                }

                counter++;

            }
            return true;
        }
    }
/////////////////////////////////////////// END: equals() finished, but not tested ////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    @SuppressWarnings("unchecked")
    public Object clone() {
        MyDoublyLinkedList<E> clonedList = null;
        try{
            clonedList = (MyDoublyLinkedList<E>)super.clone();
            Node<E> clonedHead = new Node<>(null);
            clonedList.head = clonedHead;
            clonedHead.next = clonedHead;
            clonedHead.previous = clonedHead;
            int clonedSize = 0;
            Node<E> currentClonedNode = new Node<>(null);

            for (Node<E> current = head.next; current != head; current = current.next) {
                currentClonedNode = getNode(clonedSize);
                clonedList.add(clonedSize, currentClonedNode.element);
                // currentClonedNode.next = currentClonedNode.previous.next;
                // currentClonedNode.previous.next = currentClonedNode;
                // currentClonedNode.next.previous = currentClonedNode;
                clonedSize++;
            }

        } catch(CloneNotSupportedException exc){
            throw new RuntimeException();
        }

        return clonedList;
    }




/*
    @Override
    // @SuppressWarnings("unchecked")
    protected MyDoublyLinkedList<E> clone() throws CloneNotSupportedException {
        MyDoublyLinkedList<E> clonedList;
        // MyDoublyLinkedList<E> clonedList = (MyDoublyLinkedList<E>)super.clone();
        // try{
            // MyDoublyLinkedList<E> clonedList = (MyDoublyLinkedList<E>)super.clone();
            // Node<E> clonedHead = new Node<>(null);
            // clonedHead.next = clonedHead;
            // clonedHead.previous = clonedHead;
            clonedList = new MyDoublyLinkedList<>();
            for(Node<E> current = head.next; current != head; current = current.next){
                clonedList.add(current.element);
            }
            // }
        // catch(CloneNotSupportedException exc){
            // throw new RuntimeException();
        // }
        return clonedList;
            // return super.clone();
    }
*/

    /*                                READY FOR GRADING: public E remove(int index) {...}                                */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// START: remove(int index) finished, but not tested /////////////////////////////
    @Override
    public E remove(int index) {
        // TODO Auto-generated method stub
        if(size == 0){
            throw new NoSuchElementException();
        }
        else if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("You cannot remove an Element that does not exist");
        }
        else{
            Node<E> newNode = getNode(index);
            E returnVal = newNode.element;
            newNode.next.previous = newNode.previous;
            newNode.previous.next = newNode.next;
            size--;

            return returnVal;
        }
    }
//////////////////////////////////////// END: remove(int index) finished, but not tested //////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////// START: set(int index, E e) finished, but not tested ///////////////////////
    @Override
    public Object set(int index, E e) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node<E> current = head.next;

        for(int i = 0; i < index; i++){
            current = current.next;
        }
        current.element = e;

        return this;
    }
///////////////////////////////////////////// END: set(int index, E e) finished, but not tested ///////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////// START: getFirst() finished, but not tested /////////////////////////////////
    @Override
    public E getFirst() {
        // TODO Auto-generated method stub
        return getNode(0).element;
    }
//////////////////////////////////////////// END: getFirst() finished, but not tested /////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////// START: getLast() finished, but not tested /////////////////////////////
    @Override
    public E getLast() {
        // TODO Auto-generated method stub
        return getNode(size - 1).element;
    }
//////////////////////////////////////////////// END: getLast() finished, but not tested //////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////// START: addFirst(E e) finished, but not tested /////////////////////////////
    @Override
    public void addFirst(E e) {
        add(0, e);
    }
///////////////////////////////////////////// END: addFirst(E e) finished, but not tested /////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////// START: addLast(E e) finished, but not tested /////////////////////////////
    @Override
    public void addLast(E e) {
        // TODO Auto-generated method stub
        add(size, e);
    }
////////////////////////////////////////////// END: addLast(E e) finished, but not tested /////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*                                  READY FOR GRADING: public E removeFirst() {...}                                  */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////// START: removeFirst() finished, but not tested /////////////////////////////
    @Override
    public E removeFirst() {
        // TODO Auto-generated method stub
        return remove(0);
    }
//////////////////////////////////////////// END: removeFirst() finished, but not tested //////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////// START: removeLast() finished, but not tested /////////////////////////////
    @Override
    public E removeLast() {
        // TODO Auto-generated method stub
        return remove(size - 1);
    }
//////////////////////////////////////////// END: removeLast() finished, but not tested //////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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
            iterState =ITERATOR_STATE.CANNOT_REMOVE;
        }

        /*                                  READY FOR GRADING: public boolean hasNext() {...}                                */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////// START: hasNext() finished, but not tested /////////////////////////////////
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }
///////////////////////////////////////////// END: hasNext() finished, but not tested /////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /*                                READY FOR GRADING: public boolean hasPrevious() {...}                              */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////// START: hasPrevious() ///////////////////////////////////////////////////
        @Override
        public boolean hasPrevious() {
            // TODO Auto-generated method stub
            return nextIndex > 0;
        }
//////////////////////////////////////////////// END: hasPrevious() ///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////// START: nextIndex() finished, but not tested /////////////////////////////////////////////////
        @Override
        public int nextIndex() {
            // TODO Auto-generated method stub
            return nextIndex;
        }
//////////////////////////////////////////// END: nextIndex() finished, but not tested ////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////// START: previous() finished, but not tested (check value of current and iterState ////////////
        @Override
        public E previous() {
            // TODO Auto-generated method stub
            if(nextIndex <= 0){
                throw new NoSuchElementException();
            }
            E returnVal = current.previous.element;
            current = current.previous;
            nextIndex--;
            iterState = ITERATOR_STATE.CAN_REMOVE_CURRENT;
            return returnVal;
        }
/////////////////////////////// END: previous() finished, but not tested (check value of current and iterState ////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////// START: previousIndex() finished, but not tested /////////////////////////////////////////////
        @Override
        public int previousIndex() {
            // TODO Auto-generated method stub
            return (nextIndex - 1);
        }
/////////////////////////////////////////// END: previousIndex() finished, but not tested /////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @Override
        public void remove() {
            switch (iterState) {
                case CANNOT_REMOVE:
                    // ...
                    throw new IllegalStateException();
                case CAN_REMOVE_PREV:
                    // ...
                    MyDoublyLinkedList.this.remove(nextIndex-1);
                    iterState =ITERATOR_STATE.CANNOT_REMOVE;
                    break;
                case CAN_REMOVE_CURRENT:
                    // ...
                    MyDoublyLinkedList.this.remove(nextIndex);
                    iterState =ITERATOR_STATE.CANNOT_REMOVE;
                    break;
            }

        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////// START: set(E arg0) started, but not finished ///////////////////////////
        @Override
        public void set(E arg0) {
            // TODO Auto-generated method stub
            current.element = arg0;
        }
/////////////////////////////////////////////// END: set(E arg0) started, but not finished ////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}