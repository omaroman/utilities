/**
 * Author: OMAROMAN
 * Date: 10/20/11
 * Time: 12:29 PM
 */

package net.parnassoft.playutilities;

import play.mvc.ActionInvoker;
import play.mvc.Controller;
import play.mvc.Http;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ControllerUtility {

    /**
     * Retrieve annotation for the action method
     * @param clazz The annotation class
     * @return Annotation object or null if not found
     */
    public static <T extends Annotation> T getActionAnnotation(Class<T> clazz) {
        Method m = (Method) ActionInvoker.getActionMethod(Http.Request.current().action)[1];
        if (m.isAnnotationPresent(clazz)) {
            return m.getAnnotation(clazz);
        }
        return null;
    }

    public static boolean hasActionAnnotation(Class <? extends Annotation> clazz) {
        Method m = (Method) ActionInvoker.getActionMethod(Http.Request.current().action)[1];
        return m.isAnnotationPresent(clazz);
    }

    /**
     * Retrieve annotation for the controller class
     * @param clazz The annotation class
     * @return Annotation object or null if not found
     */
    public static <T extends Annotation> T getControllerAnnotation(Class<T> clazz) {
        if (getControllerClass().isAnnotationPresent(clazz)) {
            return getControllerClass().getAnnotation(clazz);
        }
        return null;
    }

    public static boolean hasControllerAnnotation(Class <? extends Annotation> clazz) {
        return getControllerClass().isAnnotationPresent(clazz);
    }

    /**
     * Retrieve annotation for the controller class
     * @param clazz The annotation class
     * @return Annotation object or null if not found
     */
    public static <T extends Annotation> T getControllerInheritedAnnotation(Class<T> clazz) {
        Class<?> c = getControllerClass();
        while (!c.equals(Object.class)) {
            if (c.isAnnotationPresent(clazz)) {
                return c.getAnnotation(clazz);
            }
            c = c.getSuperclass();
        }
        return null;
    }

    public static boolean hasControllerInheritedAnnotation(Class <? extends Annotation> clazz) {
        Class<?> c = getControllerClass();
        while (!c.equals(Object.class)) {
            if (c.isAnnotationPresent(clazz)) {
                return true;
            }
            c = c.getSuperclass();
        }
        return false;
    }

    /**
     * Retrieve the controller class
     * @return Annotation object or null if not found
     */
    public static Class<? extends Controller> getControllerClass() {
        return Http.Request.current().controllerClass;
    }
}

