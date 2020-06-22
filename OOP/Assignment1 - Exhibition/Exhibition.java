/**
Exhibition class allows us to replicate exhibition behaviour.
Aleksej Bratkovskij (ab2323)
10/10/19
 **/

public class Exhibition
{

    //Variable declaration
    private int capacity;
    private int occupancy = 0; // variable initiation
    private String name;

    /**
     * Constructor
     */
    public Exhibition(String venueName, int size)
    {
        capacity = size;
        name = venueName;
    }

    /**
     * Accessor methods
     */
    public int getCapacity()
    {
        return capacity;
    }

    public int getOccupancy()
    {
        return occupancy;
    }

    public String getName()
    {
        return name;
    }

    /**
    changeCapacity changes capacity by an input value. It will throw messages and act differently
    if new value of capacity is less than occupancy or if occupancy is 0 and result of addition is
    a negative number, capacity will set to 0 and message will be printed.
     **/
    public void changeCapacity(int byHowMuch)
    {
        if (capacity + byHowMuch < occupancy && occupancy > 0)
        {
            System.out.println("New capacity too small");
        }
        else if (capacity + byHowMuch < 0 && occupancy == 0)
        {
            System.out.println("Exhibition is closed");
            capacity = 0;
        }
        else
        {
            capacity += byHowMuch;
        }
    }

    /**
     * attend method checks if there is space 
     * and then increases occupancy by 1, 
     * otherwise prints message.
     */
    public void attend(){
        if ((capacity > occupancy))
        {
            occupancy++;
        }
        else
        {
            System.out.println("Exhibition is full");
        }
    }

    /**
     * leave method checks if there are people 
     * then decreases occupancy by 1, 
     * otherwise prints message.
     */
    public void leave()
    {
        if (occupancy > 0)
        {
            occupancy--;
        }
        else
        {
            System.out.print("Exhibition is empty");
        }
    }

    /**
     * checks if there is space for the group; 
     * returns false if negative input
     */
    public boolean isSpace(int groupSize)
    {
        if (groupSize <= 0)
        {
            return false;
        }
        return capacity - occupancy >= groupSize;
    }
}
