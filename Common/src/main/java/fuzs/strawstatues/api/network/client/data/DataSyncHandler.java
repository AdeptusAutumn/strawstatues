package fuzs.strawstatues.api.network.client.data;

import fuzs.strawstatues.api.world.inventory.data.ArmorStandScreenType;
import fuzs.strawstatues.api.world.inventory.data.ArmorStandStyleOption;
import fuzs.strawstatues.api.world.inventory.ArmorStandHolder;
import fuzs.strawstatues.api.world.inventory.data.ArmorStandPose;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;

import java.util.Optional;

public interface DataSyncHandler extends ArmorStandHolder {

    default void sendName(String name) {
        setCustomArmorStandName(this.getArmorStand(), name);
    }

    default void sendPose(ArmorStandPose currentPose) {
        currentPose.applyToEntity(this.getArmorStand());
    }

    default void sendPosition(double posX, double posY, double posZ) {

    }

    default void sendRotation(float rotation) {

    }

    default void sendStyleOption(ArmorStandStyleOption styleOption, boolean value) {
        styleOption.setOption(this.getArmorStand(), value);
    }

    ArmorStandScreenType[] tabs();

    Optional<ArmorStandScreenType> getLastType();

    void setLastType(ArmorStandScreenType lastType);

    static void setCustomArmorStandName(ArmorStand armorStand, String name) {
        name = SharedConstants.filterText(name);
        if (name.length() <= 50) {
            boolean remove = name.isBlank() || name.equals(EntityType.ARMOR_STAND.getDescription().getString());
            armorStand.setCustomName(remove ? null : Component.literal(name));
        }
    }
}
