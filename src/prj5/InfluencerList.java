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
    private InfluencerNameComparator nameComparator;
    private InfluencerReachRateComparator reachRateComparator;

    // ----------------------------------------------------------
    /**
     * Create a new InfluencerList object.
     */
    public InfluencerList()
    {
        firstNode = null;
        size = 0;
        nameComparator = new InfluencerNameComparator();
        reachRateComparator = new InfluencerReachRateComparator();
    }


    /**
     * Add influencer to list
     * 
     * @param newEntry
     *            - new influencer to be added
     */
    @Override
    public void add(Influencer newEntry)
    {
        Node<Influencer> newNode = new Node<Influencer>(newEntry);
        newNode.setNext(firstNode);
        firstNode = newNode;
        this.size++;

    }


    /**
     * Add influencer to list at a certain positions
     * 
     * @param newEntry
     *            - new influencer to be added
     * @param newPosition
     *            - index to add influencer
     */
    @Override
    public void add(int newPosition, Influencer newEntry)
    {
        if (newPosition < 0 || newPosition > size)
        {
            throw new IndexOutOfBoundsException("Illegal position argument");
        }

        Node<Influencer> newNode = new Node<>(newEntry);

        if (newPosition == 0)
        {
            newNode.setNext(firstNode);
            firstNode = newNode;
        }
        else
        {
            Node<Influencer> nodeBefore = firstNode;
            for (int i = 0; i < newPosition - 1; i++)
            {
                nodeBefore = nodeBefore.getNext();
            }
            newNode.setNext(nodeBefore.getNext());
            nodeBefore.setNext(newNode);
        }
        size++;
    }


    /**
     * Remove influencer from list at a specified position
     * 
     * @param givenPosition
     *            - index at which influencer should be removed
     */
    @Override
    public Influencer remove(int givenPosition)
    {
        if (givenPosition < 0 || givenPosition >= size)
        {
            throw new IndexOutOfBoundsException("Illegal position argument");
        }

        Influencer result;

        if (givenPosition == 0)
        {
            result = firstNode.getData();
            firstNode = firstNode.getNext();
        }
        else
        {
            Node<Influencer> nodeBefore = firstNode;
            for (int i = 0; i < givenPosition - 1; i++)
            {
                nodeBefore = nodeBefore.getNext();
            }
            Node<Influencer> removeNode = nodeBefore.getNext();
            result = removeNode.getData();
            nodeBefore.setNext(removeNode.getNext());
        }

        size--;
        return result;
    }


    /**
     * Clears the Influencer list.
     */
    @Override
    public void clear()
    {
        firstNode = null;
        this.size = 0;

    }


    /**
     * Replaces the influencer at the given position with a new influencer
     * 
     * @param givenPosition
     *            - position of influencer to be replaced
     * @param newEntry
     *            - new influencer to be added
     * @return influencer that was removed.
     */
    @Override
    public Influencer replace(int givenPosition, Influencer newEntry)
    {
        if (givenPosition < 0 || givenPosition >= size)
        {
            throw new IndexOutOfBoundsException("Illegal position argument");
        }

        Node<Influencer> currentNode = firstNode;
        for (int i = 0; i < givenPosition; i++)
        {
            currentNode = currentNode.getNext();
        }

        Influencer oldData = currentNode.getData();
        currentNode.setData(newEntry);
        return oldData;
    }


    /**
     * Returns an influencer at a specified position
     * 
     * @param givenPosition
     *            - position to grab influencer
     * @return grabbed influencer
     */
    @Override
    public Influencer getEntry(int givenPosition)
    {
        if (givenPosition < 0 || givenPosition >= size)
        {
            throw new IndexOutOfBoundsException("Illegal position argument");
        }

        Node<Influencer> currentNode = firstNode;
        for (int i = 0; i < givenPosition; i++)
        {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }


    /**
     * Converts linked influencer list to an array
     * 
     * @return - array form of the linked list
     */
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


    /**
     * checks if the list contains a specified influencer
     * 
     * @param anEntry
     *            - influencer to check for
     * @return boolean value - found or not
     */
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


    /**
     * Returns the length of the list
     * 
     * @return length of list
     */
    @Override
    public int getLength()
    {
        return this.size;
    }


    /**
     * Checks if list is empty or not
     * 
     * @return boolean value representing whether the list is empty or not
     */
    @Override
    public boolean isEmpty()
    {
        return this.size == 0;
    }


    /**
     * Returns the front Influencer
     * 
     * @return front influencer
     */
    public Influencer getFront()
    {
        return firstNode.getData();

    }


    /**
     * Uses insertion sort to sort the list by channel name (alphanumeric)
     */
    public void sortByChannelName()
    {
        insertionSort(this.nameComparator);
    }


    /**
     * Sorts the influencer list by reach rate
     */
    public void sortByReachRate()
    {
        insertionSort(this.reachRateComparator);
    }


    private Node<Influencer> sortedInsert(
        Node<Influencer> node,
        Node<Influencer> sortedHead,
        Comparator<Influencer> comparator)
    {
        if (sortedHead == null
            || comparator.compare(node.getData(), sortedHead.getData()) <= 0)
        {
            node.setNext(sortedHead);
            return node;
        }

        Node<Influencer> curr = sortedHead;
        while (curr.getNext() != null
            && comparator.compare(node.getData(), curr.getNext().getData()) > 0)
        {
            curr = curr.getNext();
        }

        node.setNext(curr.getNext());
        curr.setNext(node);
        return sortedHead;
    }


    private void insertionSort(Comparator<Influencer> comparator)
    {
        Node<Influencer> sorted = null;
        Node<Influencer> curr = firstNode;
        while (curr != null)
        {
            Node<Influencer> next = curr.getNext();
            curr.setNext(null);
            sorted = sortedInsert(curr, sorted, comparator);
            curr = next;
        }
        firstNode = sorted;
    }


    // ----------------------------------------------------------
    /**
     * Returns the size of the list
     * 
     * @return size of list
     */
    public int getSize()
    {
        return this.size;
    }

}
