/**
 * A direction in the game.
 * There exist only a few different directions.
 * 
 * @author Olaf Chitil, Aleksej Bratkovskij
 * @version 15/2/2020
 */
public enum Direction
{
    NORTH("north"), 

    WEST("west"), 

    SOUTH("south"), 

    EAST("east"), 

    UP("up"), 

    DOWN("down");

    private String name;

    /**
     * Constructor with parameter.
     * Pre-condition: name is not null.
     */
    Direction(String name)
    {
        assert name != null : "Direction.Direction has null name";
        this.name = name;
    }

    /**
     * Return the direction name.
     */
    public String toString()
    {
        return name;
    }

    /** 
     * Return the dual (opposite) of this direction.
     */
    public Direction dual()
    {
        Direction result;
        switch(this){
            case NORTH: result = SOUTH; break;
            case SOUTH: result =  NORTH; break;
            case WEST: result = EAST; break;
            case EAST: result = WEST; break;
            case UP: result = DOWN; break;
            case DOWN: result = UP;
            break;
            default:
            throw new IllegalStateException("Unexpected value: " + this);
        }
        return result;
    }
}
