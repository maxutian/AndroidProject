package com.demo.mrma.demo;

/**
 * Created by mr.ma on 2018/4/4.
 */

public class StyleMatrixs {

    private static final float[] GREY_SCALE = new float[] {
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0, 0, 0, 1, 0,
    };

    public static final float[] greyScale() {
        return GREY_SCALE.clone();
    }

    private static final float[] SEPIA = new float[] {
            0.393F, 0.769F, 0.189F, 0, 0,
            0.349F, 0.686F, 0.168F, 0, 0,
            0.272F, 0.534F, 0.131F, 0, 0,
            0, 0, 0, 1, 0,
    };

    public static final float[] sepia() {
        return SEPIA.clone();
    }

    private static final float[] BRIGHT = new float[] {
            1.438F, -0.122F, -0.016F, 0, 0,
            -0.062F, 1.378F, -0.016F, 0, 0,
            -0.062F, -0.122F, 1.483F, 0, 0,
            0, 0, 0, 1, 0,
    };

    public static final float[] bright() {k
        return BRIGHT.clone();
    }

    private static final float[] VINTAGE_PINHOLE = new float[] {
            0.6279345635605994F, 0.3202183420819367F, -0.03965408211312453F, 0, 9.651285835294123F,
            0.02578397704808868F, 0.6441188644374771F, 0.03259127616149294F, 0, 7.462829176470591F,
            0.0466055556782719F, -0.0851232987247891F, 0.5241648018700465F, 0, 5.159190588235296F,
            0, 0, 0, 1, 0
    };

    public static final float[] vintagePinhole() {
        return VINTAGE_PINHOLE.clone();
    }

}
