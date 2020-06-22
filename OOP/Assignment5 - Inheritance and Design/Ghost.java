import java.util.List;
import java.util.Random;

/**
 * Class Ghost
 * A ghost in the castle.
 * 
 * @author Olaf Chitil, Aleksej Bratkovskij 
 * @version 15/2/2020
 */

public class Ghost extends Character implements Comparable{
    private String description;
    private Random r = new Random();

    /**
     * Constructor initialising location and description.
     * Pre-condition: location not null.
     * Pre-condition: description not null.
     */
    public Ghost(Room loc, String desc)
    {
        super(loc);
        description = desc;
    }

    /**
     * Return the description.
     */
    public String toString()
    {
        return description;
    }

    /**
     * Go to a random room.
     * @param rooms all rooms available
     * Pre-condition: the list is not empty.
     */
    public void goRandom(List<Room> rooms)
    {
        int index = r.nextInt(rooms.size()-1);
        if(index < 0) index++;
        super.move(rooms.get(index));
    }

    /**
     * Compares two Ghosts based on their description
     * @param o to compare to.
     * @return -1 if less than; 0 if equal to; 1 if greater than.
     */
    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }
}
