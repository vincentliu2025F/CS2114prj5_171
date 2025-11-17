package prj5;

import java.util.*;

public class InfluencerList
    implements ListInterface<Influencer>
{
    private class Node<T>
    {
        private T data;
        private Node<T> next;

        public Node(T dataPortion, Node<T> nextNode)
        {
            data = dataPortion;
            next = nextNode;
        } // end constructor


        public Node(T dataPortion)
        {
            this(dataPortion, null);
        } // end constructor


        public Node<T> getNext()
        {
            return next;
        }


        public void setNext(Node<T> newNext)
        {
            next = newNext;
        }


        public T getData()
        {
            return data;
        }


        public void setData(T data)
        {
            this.data = data;
        }
    }

    private Node<Influencer> firstNode;
    private int size;

    public InfluencerList()
    {
        firstNode = null;
        size = 0;
    }


    @Override
    public void add(Influencer newEntry)
    {
        Node<Influencer> newNode = new Node<Influencer>(newEntry);
        newNode.setNext(firstNode);
        firstNode = newNode;
        this.size++;

    }


    @Override
    public void add(int newPosition, Influencer newEntry)
    {

    }


    @Override
    public Influencer remove(int givenPosition)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void clear()
    {
        firstNode = null;
        this.size = 0;

    }


    @Override
    public Influencer replace(int givenPosition, Influencer newEntry)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Influencer getEntry(int givenPosition)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Object[] toArray()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean contains(Influencer anEntry)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public int getLength()
    {
        return this.size;
    }


    @Override
    public boolean isEmpty()
    {
        return this.size == 0;
    }

}
