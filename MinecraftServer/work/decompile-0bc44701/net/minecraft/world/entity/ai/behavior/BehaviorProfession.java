package net.minecraft.world.entity.ai.behavior;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.npc.EntityVillager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;

public class BehaviorProfession extends Behavior<EntityVillager> {

    public BehaviorProfession() {
        super(ImmutableMap.of(MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_ABSENT));
    }

    protected boolean checkExtraStartConditions(WorldServer worldserver, EntityVillager entityvillager) {
        VillagerData villagerdata = entityvillager.getVillagerData();

        return villagerdata.getProfession() != VillagerProfession.NONE && villagerdata.getProfession() != VillagerProfession.NITWIT && entityvillager.getVillagerXp() == 0 && villagerdata.getLevel() <= 1;
    }

    protected void start(WorldServer worldserver, EntityVillager entityvillager, long i) {
        entityvillager.setVillagerData(entityvillager.getVillagerData().setProfession(VillagerProfession.NONE));
        entityvillager.refreshBrain(worldserver);
    }
}
