import java.util.*;
import java.util.stream.Collectors;

/**
 * Class Room - a room in a game.
 *
 * This class is part of the "Haunted Castle" application. 
 * "Haunted Castle" is a very simple, text based travel game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes and Olaf Chitil, Aleksej Bratkovskij
 * @version 15/2/2020
 */

public class Room 
{
    private String description;
    private HashMap<Direction, Room> exits;
    private ArrayList<Character> characters = new ArrayList<>();// stores exits of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * Pre-condition: description is not null.
     */
    public Room(String description) 
    {
        assert description != null : "Room.Room has null description";
        this.description = description;
        exits = new HashMap<>();
        sane();
    }

    /**
     * Class invariant: getShortDescription() and getLongDescription() don't return null.
     */
    public void sane()
    {
        assert getShortDescription() != null : "Room has no short description" ;
        assert getLongDescription() != null : "Room has no long description" ;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     * Pre-condition: neither direction nor neighbor are null; 
     * there is no room in given direction yet.
     */
    public void setExit(Direction direction, Room neighbor) 
    {
        assert direction != null : "Room.setExit gets null direction";
        assert neighbor != null : "Room.setExit gets null neighbor";
        assert getExit(direction) == null : "Room.setExit set for direction that has neighbor";
        sane();
        exits.put(direction, neighbor);
        sane();
        assert getExit(direction) == neighbor : "Room.setExit has wrong neighbor";
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        if(characters.isEmpty()) return "You are " + description + "\n" + getExitString();
        return "You are " + description + "\n" + getExitString() + "\n" + getAllCharacters().trim();
    }

    /**
     * Prints all characters in the room
     * @return String containing all characters.
     */
    private String getAllCharacters(){
        StringBuilder sb = new StringBuilder();
        Arrays.sort(characters.toArray());
        for(Character c : characters){
            sb.append(c.toString()).append("; ");
        }
        if(!characters.isEmpty()){
            sb.insert(0,"Characters: ");
            return  sb.toString();
        }
        return "";
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        StringBuilder returnString = new StringBuilder("Exits:");
        // Ensure some fixed ordering of keys, so that return String uniquely defined.
        List<String> es = exits.keySet().stream().map(Direction::toString).sorted().collect(Collectors.toList());
        for(String e : es) {
            returnString.append(" ").append(e);
        }
        return returnString.toString();
    }

    /**
     * Accessor method for all available exits in collection
     * @return HashMap<Direction,Room>
     */
    public HashMap<Direction,Room> getExits(){
        return exits;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     * Pre-condition: direction is not null
     */
    public Room getExit(Direction direction) 
    {
        assert direction != null : "Room.getExit has null direction";
        sane();
        return exits.get(direction);
    }

    /**
     * Add given character to the room
     * @param c The character to add.
     * Pre-condition: character is not null.
     * Pre-condition: character itself has this room as location.
     */
    public void addCharacter(Character c)
    {
        characters.add(c);
    }

    /**
     * Remove given character from the room.
     * @param c The character to remove.
     * Pre-condition: character is not null.
     * Pre-condition: character itself has this room as location.
     */
    public void removeCharacter(Character c)
    {
        characters.remove(c);
    }

    /**
     * Change all exits of a room to their dual.
     */
    public void dual()
    {
        HashMap<Direction, Room> directionsTemp = new HashMap<>();
        for(Direction d : exits.keySet()){
            directionsTemp.put(d.dual(), exits.get(d));
        }
        exits = directionsTemp;
    }

    /**
     * Getter for characters ArrayList
     * @return returns ArrayList of all characters.
     */
    public ArrayList<Character> getCharacters(){
        return characters;
    }
}

