/**
 * Author: OMAROMAN
 * Date: 10/20/11
 * Time: 12:32 PM
 */

package net.parnassoft.playutilities.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Interceptor {
}
