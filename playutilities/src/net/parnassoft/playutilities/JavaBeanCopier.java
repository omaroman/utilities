/**
 * JavaBean Deep copy
 * Hanson Char
 * Sun, 13 Jun 2004 05:34:10 -0700
 *
 * I see BeanUtils (package org.apache.commons.beanutils) currently has a
 * cloneBean utility method to do shallow bean cloning.  Sometimes it is useful
 * to do a deep clone.  Please find below a simple implementation.  Can similar
 * method be incorporated/provided in BeanUtils ?
 */

package net.parnassoft.playutilities;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

/**
 * Used to clone java beans.
 */
public class JavaBeanCopier {
    /**
     *  Returns a deeply cloned java bean.
     *
     * @param fromBean java bean to be cloned.
     * @return a new java bean cloned from fromBean.
     */
    public static Object copy(Object fromBean) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder out = new XMLEncoder(bos);
        out.writeObject(fromBean);
        out.close();
        ByteArrayInputStream bis = new
                ByteArrayInputStream(bos.toByteArray());
        XMLDecoder in = new XMLDecoder(bis);
        Object toBean = in.readObject();
        in.close();
        return toBean;
    }
}
