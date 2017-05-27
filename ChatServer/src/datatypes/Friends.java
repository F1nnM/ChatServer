package datatypes;

import java.util.ArrayList;

public class Friends {

	private static ArrayList<User> list = new ArrayList<User>();

	/**
	 * Adds user to friends
	 * 
	 * @param usr
	 *            User to add
	 */
	public static void add(User usr) {
		list.add(usr);
	}

	/**
	 * Removes user from friends
	 * 
	 * @param usr
	 *            User to remove
	 */
	public static void remove(User usr) {
		if (contains(usr)) {
			list.remove(getIndex(usr.getId()));
		}
	}

	/**
	 * Tests whether a specific user is a friend
	 * 
	 * @param usr
	 * @return
	 */
	public static boolean contains(User usr) {
		return list.contains(usr);
	}

	/**
	 * Tests whether a friend with a specific ID exists
	 * 
	 * @param id
	 *            The ID to look for
	 * @return true, if ID was found; if not false
	 */
	public static boolean containsId(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id)
				return true;
		}
		return false;
	}

	/**
	 * Tests whether a friend with a specific name exists
	 * 
	 * @param name
	 *            The name to look for
	 * @return true, if name was found; if not false
	 */
	public static boolean containsName(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName() == name)
				return true;
		}
		return false;
	}

	/**
	 * Get user at specific index
	 * 
	 * @param Index
	 *            Index
	 * @return User at index
	 */
	public static User get(int Index) {
		return list.get(Index);
	}

	/**
	 * Get index from user with specific id
	 * 
	 * @param id
	 *            ID to get index from
	 * @return -1 if not found else the index
	 */
	public static int getIndex(int id) {
		if (containsId(id)) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId() == id)
					return i;
			}
		}
		return -1;
	}

	/**
	 * Get index from user with specific name
	 * 
	 * @param name
	 *            Name to get index from
	 * @return -1 if not found else the index
	 */
	public static int getIndex(String name) {
		if (containsName(name)) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getName() == name)
					return i;
			}
		}
		return -1;
	}

	public static int count() {
		return list.size();
	}
	
}
