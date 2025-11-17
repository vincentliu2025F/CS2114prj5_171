package prj5;

import java.util.*;

// -------------------------------------------------------------------------
/**
 * Contains logic and code for the implementation of a linked-list of
 * influencers. Uses 3 comparators to sort the list in various ways.
 * 
 * @author ryanjeronimus
 * @version Nov 17, 2025
 */
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
        if (newPosition < 1 || newPosition > size + 1)
        {
            throw new IndexOutOfBoundsException("Illegal position argument");
        }

        Node<Influencer> newNode = new Node<>(newEntry);
        if (newPosition == 1)
        {
            newNode.setNext(firstNode);
            firstNode = newNode;
        }
        else
        {
            Node<Influencer> nodeBefore = firstNode;
            for (int i = 1; i < newPosition - 1; i++)
            {
                nodeBefore = nodeBefore.getNext();
            }
            newNode.setNext(nodeBefore.getNext());
            nodeBefore.setNext(newNode);
        }
        size++;

    }


    @Override
    public Influencer remove(int givenPosition)
    {
        if (givenPosition < 1 || givenPosition > size)
        {
            throw new IndexOutOfBoundsException("Illegal position argument");
        }
        Influencer currInfluencer;
        if (givenPosition == 1)
        {
            currInfluencer = firstNode.getData();
            firstNode = firstNode.getNext();
        }
        else
        {
            Node<Influencer> nodeBefore = firstNode;
            for (int i = 1; i < givenPosition - 1; i++)
            {
                nodeBefore = nodeBefore.getNext();
            }

            Node<Influencer> remover = nodeBefore.getNext();
            currInfluencer = remover.getData();
            nodeBefore.setNext(remover.getNext());
        }

        size--;
        return currInfluencer;
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
        if (givenPosition < 1 || givenPosition > size)
        {
            throw new IndexOutOfBoundsException("Illegal position argument");
        }
        Node<Influencer> currentNode = firstNode;
        for (int i = 1; i < givenPosition; i++)
        {
            currentNode = currentNode.getNext();
        }
        Influencer oldData = currentNode.getData();
        currentNode.setData(newEntry);
        return oldData;
    }


    @Override
    public Influencer getEntry(int givenPosition)
    {
        if (givenPosition < 1 || givenPosition > size)
        {
            throw new IndexOutOfBoundsException("Illegal position argument");
        }
        Node<Influencer> currentNode = firstNode;
        for (int i = 1; i < givenPosition; i++)
        {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }


    @Override
    public Object[] toArray()
    {
        Object[] influencerArray = new Object[size];
        Node<Influencer> currNode = firstNode;
        for (int i = 0; i < size; i++)
        {
            influencerArray[i] = currNode.getData();
            currNode = currNode.getNext();
        }
        return influencerArray;
    }


    @Override
    public boolean contains(Influencer anEntry)
    {
        Node<Influencer> currNode = firstNode;

        for (int i = 0; i < this.size; i++)
        {
            if (currNode.getData().equals(anEntry))
            {
                return true;
            }
            currNode = currNode.getNext();
        }
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
