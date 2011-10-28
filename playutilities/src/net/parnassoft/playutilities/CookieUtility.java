/**
 * Author: OMAROMAN
 * Date: 10/20/11
 * Time: 12:24 PM
 */

package net.parnassoft.playutilities;

import play.mvc.Http;

public class CookieUtility {

    public static void createCookie(String cookieName, String cookieValue) {
        Http.Response response = Http.Response.current();
        response.setCookie(cookieName, cookieValue);
    }

    public static void createCookie(String cookieName, String cookieValue, String time) {
        Http.Response response = Http.Response.current();
        response.setCookie(cookieName, cookieValue, time); // TODO: What if time is incorrect???
    }

    public static Http.Cookie getCookie(String cookieName) {
        return Http.Request.current().cookies.get(cookieName);
    }

    /**
     * Reads the value of the cookie
     * @param cookieName - Name of the cookie
     * @return - A String obj containing the value of the cookie
     */
    public static String readCookie(String cookieName) {
        Http.Request request = Http.Request.current();

        if (request.cookies.get(cookieName) != null) {
            return request.cookies.get(cookieName).value;
        }

        return null;
    }

    public static void deleteCookie(String cookieName) {
        Http.Response response = Http.Response.current();
        response.removeCookie(cookieName);
    }
}

