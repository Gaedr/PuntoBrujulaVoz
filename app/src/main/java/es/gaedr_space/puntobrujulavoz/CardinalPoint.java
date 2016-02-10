package es.gaedr_space.puntobrujulavoz;

public enum CardinalPoint {
    Norte(1),
    Noreste(45),
    Este(90),
    Sudeste(135),
    Sur(180),
    Suroeste(225),
    Oeste(270),
    Noroeste(315);

    private final float angle;

    /**
     * Establece el ángulo del punto cardinal
     *
     * @param ang
     */
    CardinalPoint(float ang) {
        this.angle = ang;
    }

    /**
     * Devuelve el ángulo del punto cardinal
     *
     * @return
     */
    public float getAngle() {
        return this.angle;
    }
}

