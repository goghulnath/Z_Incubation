package com.ZohoIncubation.Task3;

public class CustomStackLL<T> {

    private Node head;
    private Node tail;
    private int size =0;

    @Override
    public String toString(){
        this.display();
        return "";
    }
    public void showHeadAndTail(){
        System.out.println("Head :" + head.getValue() + ", Tail:" + tail.getValue());
    }
    public void display(){
        try {
            Exception exception = CustomException.flowExceptionCheck(head);
            if(exception!=null)
                throw exception;
            StringBuilder string = new StringBuilder();
            string.append("[");
            Node temp = head;
            while(temp!=null){
                string.append(temp.getValue()+",");
                temp = temp.getNext();
            }

            string.replace(string.length()-1, string.length(),"]");
            System.out.println(string);

        }catch (Exception ex){
            System.out.println("[]");
        }
    }
    public Node<T> getTail(){
        return tail;
    }

    public boolean push(T value){
        Node newNode = new Node(value);
        if(head==null){
            head = tail = newNode;
            ++size;
            return true;
        }
        tail.setNext(newNode);
        newNode.setPrev(tail);
        tail = newNode;
        size++;
        return true;
    }

    public T pop(){
        try {
            Exception exception = CustomException.flowExceptionCheck(head);
            if(exception!=null)
                throw exception;
            T temp = (T)tail.getValue();
            if(size==1){
                head = tail = null;
                --size;
                return temp;
            }
            tail = tail.getPrev();
            tail.setNext(null);
            --size;
            return temp;
        }
        catch (Exception ex){
            System.out.print(ex.getMessage());
        }
        return null;
    }

    public T peek(){
        try {
            Exception exception = CustomException.flowExceptionCheck(head);
            if (exception != null)
                throw exception;
            T temp = (T)(tail.getValue());
            return temp;
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
        return null;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public Object search(T ob){
        try {
            Exception exception = CustomException.flowExceptionCheck(head);
            if (exception != null)
                throw exception;

            Node temp = tail;
            int count=0;
            while (temp!=null){
                ++count;
                if((T)temp.getValue()== ob){
                    return count;
                }
                temp = temp.getPrev();
            }
        }
        catch (Exception ex){
            System.out.print(ex.getMessage());
        }
        return -1;
    }


}
