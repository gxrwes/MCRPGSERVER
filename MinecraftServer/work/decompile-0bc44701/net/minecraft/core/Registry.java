package net.minecraft.core;

import javax.annotation.Nullable;

public interface Registry<T> extends Iterable<T> {

    int DEFAULT = -1;

    int getId(T t0);

    @Nullable
    T byId(int i);

    int size();
}
