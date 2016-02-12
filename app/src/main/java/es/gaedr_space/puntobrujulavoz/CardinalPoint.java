/*
 * Copyright (c) 2016. Samuel Peregrina Morillas <gaedr0@gmail.com>, Nieves V. Velásquez Díaz <chibi.pawn@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package es.gaedr_space.puntobrujulavoz;

/**
 * Enum que contiene los punto cardinales y sus correspondientes grados
 *
 * @author gaedr
 */
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
     * Constructor del enum
     * Establece el ángulo del punto cardinal
     *
     * @param ang angulo asignado al punto cardinal
     */
    CardinalPoint(float ang) {
        this.angle = ang;
    }

    /**
     * Devuelve el ángulo del punto cardinal
     *
     * @return float que contiene el ángulo
     */
    public float getAngle() {
        return this.angle;
    }
}

