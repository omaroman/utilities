/**
 * Author: OMAROMAN
 * Date: 10/20/11
 * Time: 12:30 PM
 */

package net.parnassoft.playutilities;

import javassist.*;
import javassist.Modifier;
import javassist.bytecode.AccessFlag;
import net.parnassoft.playutilities.annotations.Interceptor;
import org.apache.commons.lang.StringUtils;
import play.Logger;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

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

    // TODO: New methods

    public static List<CtField> getAllPersistentFields(CtClass ctClass) {
        List<CtField> fields = new ArrayList<CtField>();

        // - the field must be a valid javabean property
        // - the field must be non-transient
        // - there must be a getter for the field
        // - there must be a setter for the field

        for (CtField ctField : ctClass.getDeclaredFields()) {
            try {
//                Logger.debug("PROPERTY %s", ctField.getName());
//                Logger.debug("isProperty: %b", isProperty(ctField));
//                Logger.debug("isNotTransient: %b", !isTransient(ctField));
//                Logger.debug("hasGetterMethod: %b", hasGetterMethod(ctClass, ctField));
//                Logger.debug("hasSetterMethod: %b", hasSetterMethod(ctClass, ctField));
                if (isProperty(ctField) && !isTransient(ctField) /*&& hasGetterMethod(ctClass, ctField) && hasSetterMethod(ctClass, ctField)*/) {
                    fields.add(ctField);
                }
            } catch (ClassNotFoundException e) {
                // Do Nothing... @Transient annotation indeed exist
            }
//            catch (NotFoundException e) {
//                // Do Nothing... Getter
//                throw new UnexpectedException(e.getMessage());
//            }

        }

        return fields;
    }

    /**
     * Is this field a valid javabean property ?
     * @param ctField -
     * @return a boolean value
     */
    public static boolean isProperty(CtField ctField) {
        if (ctField.getName().equals(ctField.getName().toUpperCase()) ||
            ctField.getName().substring(0, 1).equals(ctField.getName().substring(0, 1).toUpperCase())) {
            return false;
        }
//        Logger.debug("isPublic: %b", Modifier.isPublic(ctField.getModifiers()));
//        Logger.debug("isProtected: %b", Modifier.isProtected(ctField.getModifiers()));
//        Logger.debug("isPrivate: %b", Modifier.isPrivate(ctField.getModifiers()));

//        Logger.debug("isNotFinal: %b", !Modifier.isFinal(ctField.getModifiers()));
//        Logger.debug("isNotStatic: %b", !Modifier.isStatic(ctField.getModifiers()));
//        Logger.debug("isNotNative: %b", !Modifier.isNative(ctField.getModifiers()));

//        try {
//            Logger.debug("hasPropertyAccessor: %b", EnhancerUtility.hasAnnotation(ctField, PropertiesEnhancer.PlayPropertyAccessor.class.getName()));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        return /*Modifier.isPrivate(ctField.getModifiers()) &&*/
               !java.lang.reflect.Modifier.isFinal(ctField.getModifiers()) &&
               !java.lang.reflect.Modifier.isStatic(ctField.getModifiers());
                /* && !Modifier.isNative(ctField.getModifiers()); */
    }

    public static boolean isSet(CtField ctField) throws NotFoundException {
        if (ctField.getType().getName().equals(java.util.Set.class.getName())) {
            return true;
        }
        if (ctField.getType().getName().equals(java.util.HashSet.class.getName())) {
            return true;
        }
        return false;
    }

    public static boolean isList(CtField ctField) throws NotFoundException {
//        Logger.debug("%s isList: %b", ctField.getName(), ctField.getType().getName().equals(java.util.List.class.getName()));
//        Logger.debug("%s isA: %s", ctField.getName(), ctField.getType().getName());
        if (ctField.getType().getName().equals(java.util.List.class.getName())) {
            return true;
        }
        if (ctField.getType().getName().equals(java.util.ArrayList.class.getName())) {
            return true;
        }
        return false;
    }

    public static boolean isTransient(CtField ctField) throws ClassNotFoundException {
        return EnhancerUtility.hasAnnotation(ctField, Transient.class.getName());
    }

    public static boolean isRelationship(CtField ctField) throws ClassNotFoundException {
        return EnhancerUtility.hasAnnotation(ctField, ManyToMany.class.getName()) ||
               EnhancerUtility.hasAnnotation(ctField, ManyToOne.class.getName()) ||
               EnhancerUtility.hasAnnotation(ctField, OneToMany.class.getName()) ||
               EnhancerUtility.hasAnnotation(ctField, OneToOne.class.getName());
    }

    public static boolean hasGetterMethod(CtClass ctClass, CtField ctField) throws ClassNotFoundException, NotFoundException {
        // Property name
        String propertyName = ctField.getName().substring(0, 1).toUpperCase() + ctField.getName().substring(1);
        String getter = "get" + propertyName;

        try {
            CtMethod ctMethod = ctClass.getDeclaredMethod(getter);
            if (ctMethod.getParameterTypes().length > 0 || java.lang.reflect.Modifier.isStatic(ctMethod.getModifiers())) {
                return false;
            }
        } catch (NotFoundException e) {
            throw new NotFoundException(String.format("%s it's not a getter !", getter));
        }

        return false;
    }

    public static boolean hasSetterMethod(CtClass ctClass, CtField ctField) throws ClassNotFoundException, NotFoundException {
        // Property name
        String propertyName = ctField.getName().substring(0, 1).toUpperCase() + ctField.getName().substring(1);
        String setter = "set" + propertyName;

        try {
            CtMethod ctMethod = ctClass.getDeclaredMethod(setter);
            if (ctMethod.getParameterTypes().length != 1 || !ctMethod.getParameterTypes()[0].equals(ctField.getType()) || java.lang.reflect.Modifier.isStatic(ctMethod.getModifiers())) {
                return false;
            }
        } catch (NotFoundException e) {
            throw new NotFoundException(String.format("%s it's not a setter !", setter));
        }

        return false;
    }

    public static CtMethod getMethodAnnotatedWith(CtClass ctClass, String annot) {
        for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
            try {
                if (EnhancerUtility.hasAnnotation(ctMethod, annot)) {
                    return ctMethod;
                }
            } catch (ClassNotFoundException e) {
                // Do Nothing...
            }
        }
        return null;
    }

    public static Class methodReturnType(CtMethod ctMethod) throws ClassNotFoundException {
        // return type
        String descriptor = ctMethod.getMethodInfo().getDescriptor();
        int offset = StringUtils.indexOf(descriptor, 'L') + 1;
        String fullQualifiedName = StringUtils.substring(descriptor, offset, descriptor.indexOf(';'));
        String clazz = StringUtils.replaceChars(fullQualifiedName, '/', '.');
        return Class.forName(clazz);
    }

    public static Class fieldType(CtField ctField) throws ClassNotFoundException {
        // return type
        String descriptor = ctField.getFieldInfo().getDescriptor();
        int offset = StringUtils.indexOf(descriptor, 'L') + 1;
        String fullQualifiedName = StringUtils.substring(descriptor, offset, descriptor.indexOf(';'));
        String clazz = StringUtils.replaceChars(fullQualifiedName, '/', '.');
        return Class.forName(clazz);
    }

    /**
     * Primitive type -> WrapperPrimitive object
     * @param ctField - a CtField of primitive type (boolean, short, int, long, float, double, byte, char)
     * @return - A Primitive Wrapper class
     */
    public static Class primitiveWrapper(CtField ctField) {
        CtClass primitive;
        try {
            primitive = ctField.getType();
        } catch (NotFoundException e) {
            // Not a primitive type
            return null;
        }

        if (primitive == CtClass.booleanType) {
            return Boolean.class;
        } else if (primitive == CtClass.shortType) {
            return Short.class;
        } else if (primitive == CtClass.intType) {
            return Integer.class;
        } else if (primitive == CtClass.longType) {
            return Long.class;
        } else if (primitive == CtClass.floatType) {
            return Float.class;
        } else if (primitive == CtClass.doubleType) {
            return Double.class;
        } else if (primitive == CtClass.byteType) {
            return Byte.class;
        } else if (primitive == CtClass.charType) {
            return Character.class;
        } else {
            return null;
        }
    }

    public static List<CtClass> mappedSuperClassesUpToModel(CtClass lastChildClass) throws NotFoundException, ClassNotFoundException {
        List<CtClass> mappedSupperClasses = new ArrayList<CtClass>();
        CtClass superClass = lastChildClass;

        while (superClass.getSuperclass() != null) {
            superClass = superClass.getSuperclass();
            if (superClass.getName().equals(GenericModel.class.getName())) {
                break;
            }
            if (EnhancerUtility.hasAnnotation(superClass, MappedSuperclass.class.getName())) {
                mappedSupperClasses.add(superClass);
            } else {
                break;
            }
        }

        return mappedSupperClasses;
    }
}

