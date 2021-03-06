package net.minecraft.world.level.levelgen.feature.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.core.BaseBlockPosition;
import net.minecraft.core.BlockPosition;
import net.minecraft.world.level.GeneratorAccessSeed;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.block.EnumBlockRotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.StructureBoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.DefinedStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.DefinedStructureManager;

public class WorldGenFeatureDefinedStructurePoolList extends WorldGenFeatureDefinedStructurePoolStructure {

    public static final Codec<WorldGenFeatureDefinedStructurePoolList> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(WorldGenFeatureDefinedStructurePoolStructure.CODEC.listOf().fieldOf("elements").forGetter((worldgenfeaturedefinedstructurepoollist) -> {
            return worldgenfeaturedefinedstructurepoollist.elements;
        }), projectionCodec()).apply(instance, WorldGenFeatureDefinedStructurePoolList::new);
    });
    private final List<WorldGenFeatureDefinedStructurePoolStructure> elements;

    public WorldGenFeatureDefinedStructurePoolList(List<WorldGenFeatureDefinedStructurePoolStructure> list, WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        super(worldgenfeaturedefinedstructurepooltemplate_matching);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Elements are empty");
        } else {
            this.elements = list;
            this.setProjectionOnEachElement(worldgenfeaturedefinedstructurepooltemplate_matching);
        }
    }

    @Override
    public BaseBlockPosition getSize(DefinedStructureManager definedstructuremanager, EnumBlockRotation enumblockrotation) {
        int i = 0;
        int j = 0;
        int k = 0;

        BaseBlockPosition baseblockposition;

        for (Iterator iterator = this.elements.iterator(); iterator.hasNext(); k = Math.max(k, baseblockposition.getZ())) {
            WorldGenFeatureDefinedStructurePoolStructure worldgenfeaturedefinedstructurepoolstructure = (WorldGenFeatureDefinedStructurePoolStructure) iterator.next();

            baseblockposition = worldgenfeaturedefinedstructurepoolstructure.getSize(definedstructuremanager, enumblockrotation);
            i = Math.max(i, baseblockposition.getX());
            j = Math.max(j, baseblockposition.getY());
        }

        return new BaseBlockPosition(i, j, k);
    }

    @Override
    public List<DefinedStructure.BlockInfo> getShuffledJigsawBlocks(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation, Random random) {
        return ((WorldGenFeatureDefinedStructurePoolStructure) this.elements.get(0)).getShuffledJigsawBlocks(definedstructuremanager, blockposition, enumblockrotation, random);
    }

    @Override
    public StructureBoundingBox getBoundingBox(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation) {
        Stream<StructureBoundingBox> stream = this.elements.stream().filter((worldgenfeaturedefinedstructurepoolstructure) -> {
            return worldgenfeaturedefinedstructurepoolstructure != WorldGenFeatureDefinedStructurePoolEmpty.INSTANCE;
        }).map((worldgenfeaturedefinedstructurepoolstructure) -> {
            return worldgenfeaturedefinedstructurepoolstructure.getBoundingBox(definedstructuremanager, blockposition, enumblockrotation);
        });

        Objects.requireNonNull(stream);
        return (StructureBoundingBox) StructureBoundingBox.encapsulatingBoxes(stream::iterator).orElseThrow(() -> {
            return new IllegalStateException("Unable to calculate boundingbox for ListPoolElement");
        });
    }

    @Override
    public boolean place(DefinedStructureManager definedstructuremanager, GeneratorAccessSeed generatoraccessseed, StructureManager structuremanager, ChunkGenerator chunkgenerator, BlockPosition blockposition, BlockPosition blockposition1, EnumBlockRotation enumblockrotation, StructureBoundingBox structureboundingbox, Random random, boolean flag) {
        Iterator iterator = this.elements.iterator();

        WorldGenFeatureDefinedStructurePoolStructure worldgenfeaturedefinedstructurepoolstructure;

        do {
            if (!iterator.hasNext()) {
                return true;
            }

            worldgenfeaturedefinedstructurepoolstructure = (WorldGenFeatureDefinedStructurePoolStructure) iterator.next();
        } while (worldgenfeaturedefinedstructurepoolstructure.place(definedstructuremanager, generatoraccessseed, structuremanager, chunkgenerator, blockposition, blockposition1, enumblockrotation, structureboundingbox, random, flag));

        return false;
    }

    @Override
    public WorldGenFeatureDefinedStructurePools<?> getType() {
        return WorldGenFeatureDefinedStructurePools.LIST;
    }

    @Override
    public WorldGenFeatureDefinedStructurePoolStructure setProjection(WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        super.setProjection(worldgenfeaturedefinedstructurepooltemplate_matching);
        this.setProjectionOnEachElement(worldgenfeaturedefinedstructurepooltemplate_matching);
        return this;
    }

    public String toString() {
        Stream stream = this.elements.stream().map(Object::toString);

        return "List[" + (String) stream.collect(Collectors.joining(", ")) + "]";
    }

    private void setProjectionOnEachElement(WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        this.elements.forEach((worldgenfeaturedefinedstructurepoolstructure) -> {
            worldgenfeaturedefinedstructurepoolstructure.setProjection(worldgenfeaturedefinedstructurepooltemplate_matching);
        });
    }
}
