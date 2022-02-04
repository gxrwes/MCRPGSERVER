package net.minecraft.util.datafix.fixes;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import it.unimi.dsi.fastutil.shorts.ShortList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataConverterProtoChunk extends DataFix {

    private static final int NUM_SECTIONS = 16;

    public DataConverterProtoChunk(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        return TypeRewriteRule.seq(this.writeFixAndRead("ChunkToProtoChunkFix", this.getInputSchema().getType(DataConverterTypes.CHUNK), this.getOutputSchema().getType(DataConverterTypes.CHUNK), (dynamic) -> {
            return dynamic.update("Level", DataConverterProtoChunk::fixChunkData);
        }), this.writeAndRead("Structure biome inject", this.getInputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE), this.getOutputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE)));
    }

    private static <T> Dynamic<T> fixChunkData(Dynamic<T> dynamic) {
        boolean flag = dynamic.get("TerrainPopulated").asBoolean(false) && (dynamic.get("LightPopulated").asNumber().result().isEmpty() || dynamic.get("LightPopulated").asBoolean(false));
        Dynamic dynamic1;

        if (flag) {
            dynamic1 = repackTicks(repackBiomes(dynamic));
        } else {
            dynamic1 = createEmptyChunk(dynamic);
        }

        return dynamic1.set("Status", dynamic.createString(flag ? "mobs_spawned" : "empty")).set("hasLegacyStructureData", dynamic.createBoolean(true));
    }

    private static <T> Dynamic<T> repackBiomes(Dynamic<T> dynamic) {
        return dynamic.update("Biomes", (dynamic1) -> {
            return (Dynamic) DataFixUtils.orElse(dynamic1.asByteBufferOpt().result().map((bytebuffer) -> {
                int[] aint = new int[256];

                for (int i = 0; i < aint.length; ++i) {
                    if (i < bytebuffer.capacity()) {
                        aint[i] = bytebuffer.get(i) & 255;
                    }
                }

                return dynamic.createIntList(Arrays.stream(aint));
            }), dynamic1);
        });
    }

    private static <T> Dynamic<T> repackTicks(Dynamic<T> dynamic) {
        return (Dynamic) DataFixUtils.orElse(dynamic.get("TileTicks").asStreamOpt().result().map((stream) -> {
            List<ShortList> list = (List) IntStream.range(0, 16).mapToObj((i) -> {
                return new ShortArrayList();
            }).collect(Collectors.toList());

            stream.forEach((dynamic1) -> {
                int i = dynamic1.get("x").asInt(0);
                int j = dynamic1.get("y").asInt(0);
                int k = dynamic1.get("z").asInt(0);
                short short0 = packOffsetCoordinates(i, j, k);

                ((ShortList) list.get(j >> 4)).add(short0);
            });
            return dynamic.remove("TileTicks").set("ToBeTicked", dynamic.createList(list.stream().map((shortlist) -> {
                return dynamic.createList(shortlist.intStream().mapToObj((i) -> {
                    return dynamic.createShort((short) i);
                }));
            })));
        }), dynamic);
    }

    private static <T> Dynamic<T> createEmptyChunk(Dynamic<T> dynamic) {
        Builder<Dynamic<T>, Dynamic<T>> builder = ImmutableMap.builder();

        dynamic.get("xPos").result().ifPresent((dynamic1) -> {
            builder.put(dynamic.createString("xPos"), dynamic1);
        });
        dynamic.get("zPos").result().ifPresent((dynamic1) -> {
            builder.put(dynamic.createString("zPos"), dynamic1);
        });
        return dynamic.createMap(builder.build());
    }

    private static short packOffsetCoordinates(int i, int j, int k) {
        return (short) (i & 15 | (j & 15) << 4 | (k & 15) << 8);
    }
}
