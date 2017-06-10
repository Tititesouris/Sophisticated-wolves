package sophisticated_wolves.item.pet_carrier;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sophisticated_wolves.SophisticatedWolvesMod;
import sophisticated_wolves.api.EnumWolfSpecies;
import sophisticated_wolves.api.pet_carrier.PetCarrier;
import sophisticated_wolves.entity.EntitySophisticatedWolf;

import java.util.ArrayList;
import java.util.List;

/**
 * Sophisticated Wolves
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SophisticatedWolfPetCarrier extends PetCarrier {

    @Override
    public Class getPetClass() {
        return EntitySophisticatedWolf.class;
    }

    @Override
    public String getPetId() {
        return SophisticatedWolvesMod.SW_NAME;
    }

    @Override
    public EntityLiving spawnPet(World world, EntityPlayer player) {
        return new EntitySophisticatedWolf(world);
    }

    @Override
    public List<String> getInfo(NBTTagCompound infoNbt) {
        if (infoNbt.hasKey("WolfType")) {
            List<String> list = new ArrayList(1);
            StringBuilder str = new StringBuilder(SophisticatedWolvesMod.proxy.getLocalizedString("dog_type"))
                    .append(" - ").append(SophisticatedWolvesMod.proxy.getLocalizedString("wolf_type." + EnumWolfSpecies.getSpeciesByNum(infoNbt.getInteger("WolfType"))
                    .toString().toLowerCase()));
            list.add(str.toString());
            return list;
        }
        return null;
    }

    @Override
    public NBTTagCompound getInfo(EntityLivingBase pet) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("WolfType", ((EntitySophisticatedWolf) pet).getSpecies().ordinal());

        return nbt;
    }

    @Override
    public List<NBTTagCompound> getDefaultPetCarriers() {
        List<NBTTagCompound> list = new ArrayList<>();
        for (EnumWolfSpecies species : EnumWolfSpecies.values()) {
            NBTTagCompound infoNbt = new NBTTagCompound();
            infoNbt.setInteger("WolfType", species.ordinal());

            NBTTagCompound entityNbt = new NBTTagCompound();
            entityNbt.setInteger("Species", species.ordinal());

            list.add(getDefaultPetCarrier(infoNbt, entityNbt));
        }

        return list;
    }
}
