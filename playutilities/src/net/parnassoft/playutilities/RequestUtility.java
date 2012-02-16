/**
 * Author: OMAROMAN
 * Date: 10/20/11
 * Time: 12:47 PM
 */

package net.parnassoft.playutilities;

import play.mvc.Http;

public class RequestUtility {

    public static String getScheme() {
        Http.Request request = Http.Request.current();
        return request.getBase().substring(0, request.getBase().indexOf(':'));
    }

    public static String getBase() {
        Http.Request request = Http.Request.current();
        return String.format("%s%s", request.domain, (request.port == 80 || request.port == 443) ? "" : ":" + request.port);
    }

}

