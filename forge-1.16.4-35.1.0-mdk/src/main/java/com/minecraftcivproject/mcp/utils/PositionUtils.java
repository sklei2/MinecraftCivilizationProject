package com.minecraftcivproject.mcp.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;

public class PositionUtils {

    public static double distanceBetween(BlockPos a, BlockPos b){
        return a.getDistance(b.getX(), b.getY(), b.getZ());
    }

    /**
     * Minecraft works in an East South Up (ESU) Coordinate System (East = +X, South = +Z, Up = +Y) where Yaw is 0
     * pointed South and increases counterclockwise
     * Quadrant 1 = SE Quadrant (+X +Z) 270 <= Yaw < 360 -> ASSUMES THERE IS NO YAW = 360 (THIS WOULD BE 0)
     * Quadrant 2 = SW Quadrant (-X +Z) 0 <= Yaw < 90
     * Quadrant 3 = NW Quadrant (-X -Z) 90 <= Yaw < 180
     * Quadrant 4 = NE Quadrant (+X -Z) 180 <= Yaw < 270
     */
    public static int getQuadrantFacing(Entity entity) {
        int quad = 0;
        double yaw = entity.rotationYaw;

        if (yaw >= 270 && yaw < 360) {
            quad = 1;
        } else if (yaw >= 0 && yaw < 90) {
            quad = 2;
        } else if (yaw >= 90 && yaw < 180) {
            quad = 3;
        } else if (yaw >= 180 && yaw < 270) {
            quad = 4;
        } else {
            System.out.println("ERROR: YAW OF " + yaw + " IS OUT OF BOUNDS");
        }
        return quad;
    }

    public static Vec2f getXZFromQuadrant(int quad) {
        int signX = 0;
        int signZ = 0;

        if (quad == 1) {
            signX = 1;
            signZ = 1;
        } else if (quad == 2) {
            signX = -1;
            signZ = 1;
        } else if (quad == 3) {
            signX = -1;
            signZ = -1;
        } else if (quad == 4) {
            signX = 1;
            signZ = -1;
        } else {
            System.out.println("ERROR: QUADRANT IS OUT OF BOUNDS");
        }
        return new Vec2f(signX, signZ);
    }

    /**
     * Minecraft works in an East South Up (ESU) Coordinate System (East = +X, South = +Z, Up = +Y) where Yaw is 0
     * pointed South and increases counterclockwise
     * Direction 1 = South 315 <= Yaw < 360 || 0 <= Yaw < 45 -> ASSUMES THERE IS NO YAW = 360 (THIS WOULD BE 0)
     * Direction 2 = West 45 <= Yaw < 135
     * Direction 3 = North 135 <= Yaw < 225
     * Direction 4 = East 225 <= Yaw < 315
     */
    public static int getDirectionFacing(Entity entity) {
        int dir = 0;
        double yaw = entity.rotationYaw;

        if (yaw >= 315 && yaw < 360 || yaw >= 0 && yaw < 45) {
            dir = 1;
        } else if (yaw >= 45 && yaw < 135) {
            dir = 2;
        } else if (yaw >= 135 && yaw < 225) {
            dir = 3;
        } else if (yaw >= 225 && yaw < 315) {
            dir = 4;
        } else {
            System.out.println("ERROR: YAW OF " + yaw + " IS OUT OF BOUNDS");
        }
        return dir;
    }

}
