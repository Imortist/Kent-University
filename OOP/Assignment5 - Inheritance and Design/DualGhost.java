/**
 * Class DualGhost
 * A dual ghost in the castle.
 * 
 * @author Olaf Chitil, Aleksej Bratkovskij
 * @version 15/2/2020
 */

public class DualGhost extends Ghost
{
    /**
     * Constructor initialising location and description.
     * Pre-condition: location not null.
     * Pre-condition: description not null.
     */
    public DualGhost(Room loc, String desc)
    {
        super(loc, desc);
        loc.dual();
    }

    /**
     * Moves DualGhost to a new location
     * @param location to move to.
     */
    public void move(Room location){
        super.move(location);
        location.dual();
    }

}
