/**
 * Author: OMAROMAN
 * Date: 10/20/11
 * Time: 1:10 PM
 */

package net.parnassoft.playutilities;

import play.Play;
import play.PlayPlugin;

public class ModuleUtility {

    public static boolean isModulePluginAvailable(String modulePluginName) {
        for (PlayPlugin plugin : Play.pluginCollection.getAllPlugins()) {
            if (plugin.getClass().getName().equals(modulePluginName)) {
                if (Play.pluginCollection.isEnabled(plugin)) {
                    return true;
                }
            }
        }
        return false;
    }

}
