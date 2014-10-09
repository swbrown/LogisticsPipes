/** 
 * Copyright (c) Krapht, 2011
 * 
 * "LogisticsPipes" is distributed under the terms of the Minecraft Mod Public 
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package logisticspipes.proxy.buildcraft.bc61.gates;

import logisticspipes.textures.provider.LPActionTriggerIconProvider;
import buildcraft.api.gates.IActionParameter;
import buildcraft.api.gates.IGate;

public class ActionDisableLogistics extends LPAction {

	public ActionDisableLogistics() {
		super("LogisticsPipes:action.pipeDisable");
	}
	
	@Override
	public String getDescription() {
		return "Disable Pipe";
	}
	
	@Override
	public int getIconIndex() {
		return LPActionTriggerIconProvider.actionDisablePipeIconIndex;
	}

	@Override
	public void actionActivate(IGate arg0, IActionParameter[] arg1) {
		// TODO Auto-generated method stub
		
	}
}