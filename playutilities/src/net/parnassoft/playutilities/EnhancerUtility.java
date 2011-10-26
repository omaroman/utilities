/**
 * Author: OMAROMAN
 * Date: 10/20/11
 * Time: 12:30 PM
 */

package net.parnassoft.playutilities;

import javassist.*;
import javassist.bytecode.AccessFlag;
import net.parnassoft.playutilities.annotations.Interceptor;

import javax.persistence.Entity;
import java.lang.annotation.Annotation;

public class EnhancerUtility {

    public static boolean isAController(ClassPool classPool, CtClass ctClass) throws Exception {
        return ctClass.subtypeOf(classPool.get(play.mvc.Controller.class.getName()));
    }

    public static boolean isAModel(ClassPool classPool, CtClass ctClass) throws Exception {
        // Only enhance model classes.
        return ctClass.subtypeOf(classPool.get(play.db.jpa.JPABase.class.getName()));  //"play.db.jpa.JPABase"
    }

    public static boolean isAnEntity(CtClass ctClass) throws Exception {
        return hasAnnotation(ctClass, Entity.class.getName());    // "javax.persistence.Entity"
    }

    public static boolean isAnInterceptor (CtClass ctClass) throws Exception {
        return hasAnnotation(ctClass, Interceptor.class.getName());
    }

    /**
     * Check if a method is: public static void
     * @param ctMethod - A CtMethod obj.
     * @return - A boolean value: true if it is indeed, false otherwise
     * @throws Exception -
     */
    public static boolean isPublicStaticVoid(CtMethod ctMethod) throws Exception {
//        if (ctMethod.getMethodInfo().getAccessFlags() == AccessFlag.PUBLIC + AccessFlag.STATIC) {
//            if (ctMethod.getReturnType().equals(CtClass.voidType)) {
//                return true;
//            }
//        }
//        return false;

        return (Modifier.isPublic(ctMethod.getModifiers()) && Modifier.isStatic(ctMethod.getModifiers()) && ctMethod.getReturnType().equals(CtClass.voidType));
    }

    /**
     * Test if a class has the provided annotation
     * @param ctClass the javassist class representation
     * @param annotation fully qualified name of the annotation class eg."javax.persistence.Entity"
     * @return true if class has the annotation
     * @throws java.lang.ClassNotFoundException -
     */
    public static boolean hasAnnotation(CtClass ctClass, String annotation) throws ClassNotFoundException {
//        for (Object object : ctClass.getAvailableAnnotations()) {
//            Annotation ann = (Annotation) object;
//            if (ann.annotationType().getName().equals(annotation)) {
//                return true;
//            }
//        }
//        return false;
        return hasAnnotation(annotation, ctClass.getAvailableAnnotations());
    }

    /**
	 * Test if a method has the provided annotation
	 * @param ctMethod the javassist method representation
	 * @param annotation fully qualified name of the annotation class eg."javax.persistence.Entity"
	 * @return true if field has the annotation
	 * @throws java.lang.ClassNotFoundException -
	 */
    public static boolean hasAnnotation(CtMethod ctMethod, String annotation) throws ClassNotFoundException {
//        for (Object object : ctMethod.getAvailableAnnotations()) {
//            Annotation ann = (Annotation) object;
//            if (ann.annotationType().getName().equals(annotation)) {
//                return true;
//            }
//        }
//        return false;
        return hasAnnotation(annotation, ctMethod.getAvailableAnnotations());
    }

    /**
     * Test if a field has the provided annotation
     * @param ctField the javassist field representation
     * @param annotation fully qualified name of the annotation class eg."javax.persistence.Entity"
     * @return true if field has the annotation
     * @throws java.lang.ClassNotFoundException -
     */
    public static boolean hasAnnotation(CtField ctField, String annotation) throws ClassNotFoundException {
//        for (Object object : ctField.getAvailableAnnotations()) {
//            Annotation ann = (Annotation) object;
//            if (ann.annotationType().getName().equals(annotation)) {
//                return true;
//            }
//        }
//        return false;
        return hasAnnotation(annotation, ctField.getAvailableAnnotations());
    }

    private static boolean hasAnnotation(String annotation, Object... annotations) {
        for (Object object : annotations) {
            Annotation ann = (Annotation) object;
            if (ann.annotationType().getName().equals(annotation)) {
                return true;
            }
        }
        return false;
    }
}

