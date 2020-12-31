package arcaios26.supersaturation.data;

public class SuperSat implements ISuperSat {
    private float saturation = 0.0f;
    private int hunger = 0;

    @Override
    public float getSat() {
        return this.saturation;
    }

    @Override
    public void setSat(float sat) {
        this.saturation = sat;
    }

    @Override
    public int getHunger() {
        return this.hunger;
    }

    @Override
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
}
