/**
 * Class Player
 * A single object represents the single player.
 * 
 * @author Olaf Chitil, Aleksej Bratkovskij
 * @version 15/2/2020
 */

public class Player extends Character implements Comparable
{

    private Room goal;
    private int time = 12;
    /**
     * Constructor, taking start room and goal room.
     * Pre-condition: start location is not null.
     */
    public Player(Room start, Room goal)
    {
        super(start);
        this.goal = goal;
    }

    /**
     * reduces time counter
     */
    public void takeTime() {
        time--;
    }

    /**
     * Check whether time limit has been reached.
     */
    public boolean isAtTimeLimit()
    {
        return time == 0;
    }

    /**
     * Check whether goal has been reached.
     */
    public boolean isAtGoal()
    {
        return super.getLocation().getShortDescription().equals(goal.getShortDescription());
    }

    /**
     * Return a description.
     */
    public String toString()
    {
        return "you";
    }

    /**
     * Compares players by time left.
     * @param o object to compare to
     * @return the value 0 if this == o; a value less than 0 if this < o; and a value greater than 0 if this > o
     */
    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }
}
