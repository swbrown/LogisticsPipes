package logisticspipes.utils;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.ModContainer;

public class ModStatusHelper {
	public static boolean isModLoaded(String modId) {
		if(modId.contains("@")) {
			String version = modId.substring(modId.indexOf('@') + 1);
			modId = modId.substring(0, modId.indexOf('@'));
			if(Loader.isModLoaded(modId)) {
				ModContainer mod = Loader.instance().getIndexedModList().get(modId);
				if(mod != null) {
					return mod.getVersion().startsWith(version);
				}
			}
			return false;
		} else if(Loader.isModLoaded(modId)) {
			return true;
		} else {
			return ModAPIManager.INSTANCE.hasAPI(modId);
		}
	}
}
