package arcaios26.supersaturation.data;

import net.minecraft.server.level.ServerPlayer;

public interface ISuperSat {
    public float getSat();

    public void setSat(float sat);

    public int getHunger();

    public void setHunger(int hunger);

    public void sync(ServerPlayer player);
}
