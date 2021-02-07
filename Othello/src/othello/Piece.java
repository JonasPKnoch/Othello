package othello;

/*
 * Controls the individual pieces on the board. 
 * Most of the code deals with adjacency.
 * 
 *  @author Jonas K
 */
public class Piece {
		int x;
		int y;
		PTeam team;
		
		Piece east;
		Piece southEast;
		Piece south;
		Piece southWest;
		Piece west;
		Piece northWest;
		Piece north;
		Piece northEast;
		
		/*
		 * Just assigns some variables. 
		 */
		Piece(int x, int y, PTeam team) {
			this.x = x;
			this.y = y;
			this.team = team;
		}
		
		/*
		 * @returns the adjacent piece in the given direction.
		 */
		Piece getDir(Dir d) {
			switch(d) {
				case EAST:
					return east;
				case SOUTHEAST:
					return southEast;
				case SOUTH:
					return south;
				case SOUTHWEST:
					return southWest;
				case WEST:
					return west;
				case NORTHWEST:
					return northWest;
				case NORTH:
					return north;
				case NORTHEAST:
					return northEast;
				default:
					return null;
			}
		}
		
		/*
		 * Sets the adjacent piece in the given direction to the given
		 * team.	
		 */
		Piece setDir(Dir d, Piece set) {
			switch(d) {
				case EAST:
					east = set;
					return east;
				case SOUTHEAST:
					southEast = set;
					return southEast;
				case SOUTH:
					south = set;
					return south;
				case SOUTHWEST:
					southWest = set;
					return southWest;
				case WEST:
					west = set;
					return west;
				case NORTHWEST:
					northWest = set;
					return northWest;
				case NORTH:
					north = set;
					return north;
				case NORTHEAST:
					northEast = set;
					return northEast;
				default:
					return null;
			}
		}
		
		/*
		 * Sets the adjacent piece in the opposite of the given 
		 * direction to the given team. Used by Move. 
		 */
		Piece setOppisite(Dir d, Piece set) {
			switch(d) {
				case EAST:
					west = set;
					return west;
				case SOUTHEAST:
					northWest = set;
					return northWest;
				case SOUTH:
					north = set;
					return north;
				case SOUTHWEST:
					northEast = set;
					return northEast;
				case WEST:
					east = set;
					return east;
				case NORTHWEST:
					southEast = set;
					return southEast;
				case NORTH:
					south = set;
					return south;
				case NORTHEAST:
					southWest = set;
					return southWest;
				default:
					return null;
			}
		}
	}
