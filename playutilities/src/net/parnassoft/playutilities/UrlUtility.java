/**
 * Author: OMAROMAN
 * Date: 10/20/11
 * Time: 12:44 PM
 */

package net.parnassoft.playutilities;

import play.mvc.Router;
import play.mvc.results.Redirect;

public class UrlUtility {

    public static void redirectToDomain() {
        String url = UrlUtility.buildFullDomainUrl();
        redirectTo(url);
    }

    public static void redirectToUriPattern(String path) {
        redirectTo(UrlUtility.buildFullUrl(path));
    }

    public static void redirectByReverseRouting(String javaCall) {
        String url = Router.reverse(javaCall).url;
        redirectToUriPattern(url);
    }

    public static void redirectToUrl(String url) {
        redirectTo(url);
    }

    private static void redirectTo(String url) {
        boolean permanent = false;
        throw new Redirect(url, permanent);
    }

    private static String buildFullDomainUrl() {
        return String.format("%s://%s", RequestUtility.getScheme(), RequestUtility.getBase());
    }

    private static String buildFullReferredUrl(String url) throws Throwable {
        return String.format("%s://%s%s", RequestUtility.getScheme(), RequestUtility.getBase(), url);
    }

    private static String buildFullUrl(String uriPattern) {
        return String.format("%s://%s%s", RequestUtility.getScheme(), RequestUtility.getBase(), uriPattern);
    }

}

