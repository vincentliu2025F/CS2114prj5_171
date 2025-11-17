package prj5;

import java.util.*;

public class InfluencerList
    implements List<Influencer>
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

    Node<Influencer> firstNode;
    int size;

    public InfluencerList()
    {
        firstNode = null;
        size = 0;
    }

    @Override
    public int size()
    {

        return this.size;
    }


    @Override
    public boolean isEmpty()
    {
        return this.size == 0;
    }


    @Override
    public boolean contains(Object object)
    {
        if (object == null)
        {
            return false;
        }
        if (!(object instanceof Influencer))
        {
            return false;
        }
        Influencer influencerObject = (Influencer)object;
        Node<Influencer> currNode = firstNode;
        for (int i = 0; i < size; i++)
        {
            if (currNode.getData().equals(influencerObject))
            {
                return true;
            }
            currNode = currNode.getNext();
        }
        return false;
    }


    @Override
    public Iterator<Influencer> iterator()
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
    public <T> T[] toArray(T[] a)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean add(Influencer newInfluencer)
    {
        Node<Influencer> newNode = new Node<Influencer>(newInfluencer);
        newNode.setNext(firstNode);
        firstNode = newNode;
        this.size++;
        return true;
    }


    @Override
    public boolean remove(Object object)
    {
        if (object == null)
        {
            return false;
        }
        if (!(object instanceof Influencer))
        {
            return false;
        }
        Influencer influencerObject = (Influencer)object;
        if (firstNode.getData() == influencerObject)
        {
            firstNode = firstNode.getNext();
            this.size--;
            return true;
        }

        Node<Influencer> currNode = firstNode;
        for (int i = 0; i < size; i++)
        {
            if (currNode.getNext().equals(influencerObject))
            {
                currNode.setNext(currNode.getNext().getNext());
                this.size--;
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean addAll(Collection<? extends Influencer> c)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean addAll(int index, Collection<? extends Influencer> c)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean removeAll(Collection<?> c)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean retainAll(Collection<?> c)
    {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public void clear()
    {
        firstNode.setNext(null);
        this.size = 0;

    }


    @Override
    public Influencer get(int index)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Influencer set(int index, Influencer element)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void add(int index, Influencer element)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public Influencer remove(int index)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public int indexOf(Object o)
    {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public int lastIndexOf(Object o)
    {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public ListIterator<Influencer> listIterator()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public ListIterator<Influencer> listIterator(int index)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<Influencer> subList(int fromIndex, int toIndex)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
