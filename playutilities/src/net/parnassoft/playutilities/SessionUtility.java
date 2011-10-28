/**
 * Author: OMAROMAN
 * Date: 10/27/11
 * Time: 3:05 PM
 */
package net.parnassoft.playutilities;

import play.mvc.Scope;

public class SessionUtility {

    /**
     * This method returns the current connected either by user_email or username
     * @param key -
     * @return -
     */
    public static Object get(String key) {
        Scope.Session session = Scope.Session.current();
        return session.get(key);
    }

    /**
     * Indicate if a user is currently connected
     * @param key -
     * @return  true if the user is connected
     */
    public static boolean contains(String key) {
        Scope.Session session = Scope.Session.current();
        return session.contains(key);
    }

    public static void put(String key, Object obj) {
        Scope.Session session = Scope.Session.current();
        session.put(key, obj);
    }

    public static void put(String key, String value) {
        Scope.Session session = Scope.Session.current();
        session.put(key, value);
    }

    public static void remove(String key) {
        Scope.Session session = Scope.Session.current();
        session.remove(key);
    }

}
