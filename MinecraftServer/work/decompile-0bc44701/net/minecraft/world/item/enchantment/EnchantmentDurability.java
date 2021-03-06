package net.minecraft.world.item.enchantment;

import java.util.Random;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.ItemArmor;
import net.minecraft.world.item.ItemStack;

public class EnchantmentDurability extends Enchantment {

    protected EnchantmentDurability(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.BREAKABLE, aenumitemslot);
    }

    @Override
    public int getMinCost(int i) {
        return 5 + (i - 1) * 8;
    }

    @Override
    public int getMaxCost(int i) {
        return super.getMinCost(i) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canEnchant(ItemStack itemstack) {
        return itemstack.isDamageableItem() ? true : super.canEnchant(itemstack);
    }

    public static boolean shouldIgnoreDurabilityDrop(ItemStack itemstack, int i, Random random) {
        return itemstack.getItem() instanceof ItemArmor && random.nextFloat() < 0.6F ? false : random.nextInt(i + 1) > 0;
    }
}
