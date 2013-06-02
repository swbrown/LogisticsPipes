package logisticspipes.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logisticspipes.LogisticsPipes;
import logisticspipes.network.packets.abstracts.ModernPacket;
import logisticspipes.proxy.MainProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	public static List<ModernPacket> packetlist;

	public static Map<Class<? extends ModernPacket>, ModernPacket> packetmap;

	public PacketHandler() {
		try {
			ImmutableSet<ClassInfo> classes = ClassPath.from(
					this.getClass().getClassLoader()).getTopLevelClasses(
					"logisticpipes.network.packets");

			packetlist = new ArrayList<ModernPacket>(classes.size());
			packetmap = new HashMap<Class<? extends ModernPacket>, ModernPacket>(
					classes.size());

			int currentid = 200;// TODO: Only 200 until all packets get
								// converted

			for (ClassInfo c : classes) {

				Class<?> cls = c.load();
				if (!cls.isInstance(ModernPacket.class)) {
					if (LogisticsPipes.DEBUG)
						LogisticsPipes.log
								.warning("The following class is in the wrong place: "
										+ c.getName());
					continue;
				}
				ModernPacket instance = (ModernPacket) cls.getConstructor(
						Integer.class).newInstance(currentid++);
				packetlist.add(instance);
				packetmap.put(cls.asSubclass(ModernPacket.class), instance);
			}
			Collections.sort(packetlist);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if(packet.data == null) {
			new Exception("Packet content has been null").printStackTrace();
		}
		if(MainProxy.isClient(((EntityPlayer)player).worldObj)) {
			ClientPacketHandler.onPacketData(manager, packet, player);
		} else {
			ServerPacketHandler.onPacketData(manager, packet, player);
		}
	}
}
