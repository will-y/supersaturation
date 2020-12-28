package arcaios26.supersaturation.data;

public class SuperSat implements ISuperSat {
    private float saturation = 0.0f;

    @Override
    public float getSat() {
        return this.saturation;
    }

    @Override
    public void setSat(float sat) {
        this.saturation = sat;
    }
}
