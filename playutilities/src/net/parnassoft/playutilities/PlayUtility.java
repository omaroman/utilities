/**
 * Author: OMAROMAN
 * Date: 10/20/11
 * Time: 12:35 PM
 */

package net.parnassoft.playutilities;

import play.Logger;
import play.data.validation.*;
import play.data.validation.Error;
import play.mvc.Http;

public class PlayUtility {

    public static void printParams(Http.Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n"));
        sb.append(String.format("-----PARAMS-----%n"));
        for (String name : request.params.all().keySet()) {
            sb.append(String.format("%s: %s%n", name, request.params.get(name)));
        }
        Logger.debug(sb.toString());
    }

    public static void printHeaders(Http.Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n"));
        sb.append(String.format("-----HEADERS-----%n"));
        for (String name : request.headers.keySet()) {
            sb.append(String.format("%s: %s%n", name, request.headers.get(name).value()));
        }
        Logger.debug(sb.toString());
    }

    public static void printErrors() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("-----ERRORS-----%n"));
        sb.append(String.format("%n"));
        for (Error error : Validation.errors()) {
            sb.append(error);
        }
        sb.append(String.format("%n"));
        Logger.debug(sb.toString());
    }

    public static void printRequest(Http.Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n"));
        sb.append(String.format("-----REQUEST-----%n"));
        sb.append(String.format("request.domain: %s%n", request.domain));
        sb.append(String.format("request.port: %s%n", request.port));
        sb.append(String.format("request.action: %s%n", request.action));
        sb.append(String.format("request.actionMethod: %s%n", request.actionMethod));
        sb.append(String.format("request.url: %s%n", request.url));
        sb.append(String.format("request.path: %s%n", request.path));
        sb.append(String.format("request.host: %s%n", request.host));
        sb.append(String.format("request.format: %s%n", request.format));
        sb.append(String.format("request.base: %s%n", request.getBase()));
        sb.append(String.format("%n"));
        Logger.debug(sb.toString());
    }
}

