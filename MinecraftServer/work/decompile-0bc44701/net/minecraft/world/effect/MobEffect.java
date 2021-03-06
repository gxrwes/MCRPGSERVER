package net.minecraft.world.effect;

import com.google.common.collect.ComparisonChain;
import javax.annotation.Nullable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.entity.EntityLiving;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MobEffect implements Comparable<MobEffect> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final MobEffectList effect;
    private int duration;
    private int amplifier;
    private boolean ambient;
    private boolean noCounter;
    private boolean visible;
    private boolean showIcon;
    @Nullable
    private MobEffect hiddenEffect;

    public MobEffect(MobEffectList mobeffectlist) {
        this(mobeffectlist, 0, 0);
    }

    public MobEffect(MobEffectList mobeffectlist, int i) {
        this(mobeffectlist, i, 0);
    }

    public MobEffect(MobEffectList mobeffectlist, int i, int j) {
        this(mobeffectlist, i, j, false, true);
    }

    public MobEffect(MobEffectList mobeffectlist, int i, int j, boolean flag, boolean flag1) {
        this(mobeffectlist, i, j, flag, flag1, flag1);
    }

    public MobEffect(MobEffectList mobeffectlist, int i, int j, boolean flag, boolean flag1, boolean flag2) {
        this(mobeffectlist, i, j, flag, flag1, flag2, (MobEffect) null);
    }

    public MobEffect(MobEffectList mobeffectlist, int i, int j, boolean flag, boolean flag1, boolean flag2, @Nullable MobEffect mobeffect) {
        this.effect = mobeffectlist;
        this.duration = i;
        this.amplifier = j;
        this.ambient = flag;
        this.visible = flag1;
        this.showIcon = flag2;
        this.hiddenEffect = mobeffect;
    }

    public MobEffect(MobEffect mobeffect) {
        this.effect = mobeffect.effect;
        this.setDetailsFrom(mobeffect);
    }

    void setDetailsFrom(MobEffect mobeffect) {
        this.duration = mobeffect.duration;
        this.amplifier = mobeffect.amplifier;
        this.ambient = mobeffect.ambient;
        this.visible = mobeffect.visible;
        this.showIcon = mobeffect.showIcon;
    }

    public boolean update(MobEffect mobeffect) {
        if (this.effect != mobeffect.effect) {
            MobEffect.LOGGER.warn("This method should only be called for matching effects!");
        }

        boolean flag = false;

        if (mobeffect.amplifier > this.amplifier) {
            if (mobeffect.duration < this.duration) {
                MobEffect mobeffect1 = this.hiddenEffect;

                this.hiddenEffect = new MobEffect(this);
                this.hiddenEffect.hiddenEffect = mobeffect1;
            }

            this.amplifier = mobeffect.amplifier;
            this.duration = mobeffect.duration;
            flag = true;
        } else if (mobeffect.duration > this.duration) {
            if (mobeffect.amplifier == this.amplifier) {
                this.duration = mobeffect.duration;
                flag = true;
            } else if (this.hiddenEffect == null) {
                this.hiddenEffect = new MobEffect(mobeffect);
            } else {
                this.hiddenEffect.update(mobeffect);
            }
        }

        if (!mobeffect.ambient && this.ambient || flag) {
            this.ambient = mobeffect.ambient;
            flag = true;
        }

        if (mobeffect.visible != this.visible) {
            this.visible = mobeffect.visible;
            flag = true;
        }

        if (mobeffect.showIcon != this.showIcon) {
            this.showIcon = mobeffect.showIcon;
            flag = true;
        }

        return flag;
    }

    public MobEffectList getEffect() {
        return this.effect;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getAmplifier() {
        return this.amplifier;
    }

    public boolean isAmbient() {
        return this.ambient;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean showIcon() {
        return this.showIcon;
    }

    public boolean tick(EntityLiving entityliving, Runnable runnable) {
        if (this.duration > 0) {
            if (this.effect.isDurationEffectTick(this.duration, this.amplifier)) {
                this.applyEffect(entityliving);
            }

            this.tickDownDuration();
            if (this.duration == 0 && this.hiddenEffect != null) {
                this.setDetailsFrom(this.hiddenEffect);
                this.hiddenEffect = this.hiddenEffect.hiddenEffect;
                runnable.run();
            }
        }

        return this.duration > 0;
    }

    private int tickDownDuration() {
        if (this.hiddenEffect != null) {
            this.hiddenEffect.tickDownDuration();
        }

        return --this.duration;
    }

    public void applyEffect(EntityLiving entityliving) {
        if (this.duration > 0) {
            this.effect.applyEffectTick(entityliving, this.amplifier);
        }

    }

    public String getDescriptionId() {
        return this.effect.getDescriptionId();
    }

    public String toString() {
        String s;
        String s1;

        if (this.amplifier > 0) {
            s = this.getDescriptionId();
            s1 = s + " x " + (this.amplifier + 1) + ", Duration: " + this.duration;
        } else {
            s = this.getDescriptionId();
            s1 = s + ", Duration: " + this.duration;
        }

        if (!this.visible) {
            s1 = s1 + ", Particles: false";
        }

        if (!this.showIcon) {
            s1 = s1 + ", Show Icon: false";
        }

        return s1;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof MobEffect)) {
            return false;
        } else {
            MobEffect mobeffect = (MobEffect) object;

            return this.duration == mobeffect.duration && this.amplifier == mobeffect.amplifier && this.ambient == mobeffect.ambient && this.effect.equals(mobeffect.effect);
        }
    }

    public int hashCode() {
        int i = this.effect.hashCode();

        i = 31 * i + this.duration;
        i = 31 * i + this.amplifier;
        i = 31 * i + (this.ambient ? 1 : 0);
        return i;
    }

    public NBTTagCompound save(NBTTagCompound nbttagcompound) {
        nbttagcompound.putByte("Id", (byte) MobEffectList.getId(this.getEffect()));
        this.writeDetailsTo(nbttagcompound);
        return nbttagcompound;
    }

    private void writeDetailsTo(NBTTagCompound nbttagcompound) {
        nbttagcompound.putByte("Amplifier", (byte) this.getAmplifier());
        nbttagcompound.putInt("Duration", this.getDuration());
        nbttagcompound.putBoolean("Ambient", this.isAmbient());
        nbttagcompound.putBoolean("ShowParticles", this.isVisible());
        nbttagcompound.putBoolean("ShowIcon", this.showIcon());
        if (this.hiddenEffect != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            this.hiddenEffect.save(nbttagcompound1);
            nbttagcompound.put("HiddenEffect", nbttagcompound1);
        }

    }

    @Nullable
    public static MobEffect load(NBTTagCompound nbttagcompound) {
        byte b0 = nbttagcompound.getByte("Id");
        MobEffectList mobeffectlist = MobEffectList.byId(b0);

        return mobeffectlist == null ? null : loadSpecifiedEffect(mobeffectlist, nbttagcompound);
    }

    private static MobEffect loadSpecifiedEffect(MobEffectList mobeffectlist, NBTTagCompound nbttagcompound) {
        byte b0 = nbttagcompound.getByte("Amplifier");
        int i = nbttagcompound.getInt("Duration");
        boolean flag = nbttagcompound.getBoolean("Ambient");
        boolean flag1 = true;

        if (nbttagcompound.contains("ShowParticles", 1)) {
            flag1 = nbttagcompound.getBoolean("ShowParticles");
        }

        boolean flag2 = flag1;

        if (nbttagcompound.contains("ShowIcon", 1)) {
            flag2 = nbttagcompound.getBoolean("ShowIcon");
        }

        MobEffect mobeffect = null;

        if (nbttagcompound.contains("HiddenEffect", 10)) {
            mobeffect = loadSpecifiedEffect(mobeffectlist, nbttagcompound.getCompound("HiddenEffect"));
        }

        return new MobEffect(mobeffectlist, i, b0 < 0 ? 0 : b0, flag, flag1, flag2, mobeffect);
    }

    public void setNoCounter(boolean flag) {
        this.noCounter = flag;
    }

    public boolean isNoCounter() {
        return this.noCounter;
    }

    public int compareTo(MobEffect mobeffect) {
        boolean flag = true;

        return (this.getDuration() <= 32147 || mobeffect.getDuration() <= 32147) && (!this.isAmbient() || !mobeffect.isAmbient()) ? ComparisonChain.start().compare(this.isAmbient(), mobeffect.isAmbient()).compare(this.getDuration(), mobeffect.getDuration()).compare(this.getEffect().getColor(), mobeffect.getEffect().getColor()).result() : ComparisonChain.start().compare(this.isAmbient(), mobeffect.isAmbient()).compare(this.getEffect().getColor(), mobeffect.getEffect().getColor()).result();
    }
}
