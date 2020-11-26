package com.minecraftcivproject.mcp.common.entity.task;

import com.minecraftcivproject.mcp.common.entity.task.core.Task;
import com.minecraftcivproject.mcp.common.entity.task.core.TaskQueue;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.World;

import java.util.Random;

import static com.minecraftcivproject.mcp.utils.PositionUtils.distanceBetween;
import static com.minecraftcivproject.mcp.utils.PositionUtils.getQuadrantFacing;
import static com.minecraftcivproject.mcp.utils.PositionUtils.getXZFromQuadrant;
import static com.minecraftcivproject.mcp.utils.PositionUtils.getDirectionFacing;

public class MoveToBlockTask extends Task {

    private final World world;
    private final EntityLiving entity;
    private final BlockPos destination;
    private BlockPos lastPos;
    private int stuckTicks;
    private int stuckCnt;  // Number of times we were found to be stuck
    private Random r = new Random();

    public MoveToBlockTask(World world, EntityLiving entity, BlockPos destination, int stuckCnt) {
        this.world = world;
        this.entity = entity;
        this.destination = destination;
        this.stuckCnt = stuckCnt;
        System.out.println("MoveToBlockTask " + Integer.toHexString(this.hashCode()) + " stuck count: " + this.stuckCnt);
        this.lastPos = this.entity.getPosition();
        //this.entity.getMoveHelper().setMoveTo(destination.getX(), destination.getY(), destination.getZ(), 1);  // This does well with y positions that can't be reached!
        this.entity.getNavigator().tryMoveToXYZ((double)destination.getX(), (double)destination.getY(), (double)destination.getZ(), 0.8D);  // This doesn't do well with y positions that can't be reached...
    }

    @Override
    public void start() {
        System.out.println("MoveToBlockTask " + Integer.toHexString(this.hashCode()) + " start was called");
        // I'm thinking this should be called by the constructor, since start is only called by the TaskQueue class yet every single MoveToBlockTask should have a destination whether they are in a queue or not
        //this.entity.getMoveHelper().setMoveTo(destination.getX(), destination.getY(), destination.getZ(), 1);
    }

    @Override
    public void onTick() {
        if (isRunningSubtasks()) {
            System.out.println("MoveToBlockTask " + Integer.toHexString(this.hashCode()) + " has a sub-task, onTick was cut short...");
            return;
        } else {
            System.out.println("MoveToBlockTask " + Integer.toHexString(this.hashCode()) + " onTick is executing");
        }

        if (stuckCnt >= 5) {
            // Check for hole condition
            if (inHole()) {
                digYourselfOut();
                return;
            }
            return;
        }

        // Check last known position
        if (this.lastPos.equals(this.entity.getPosition())) {
            ++stuckTicks;
        } else {
            stuckTicks = 0;  // Resets stuck ticks
        }

        // Update last known position
        this.lastPos = this.entity.getPosition();

        System.out.println("Tick count: " + stuckTicks);
        if (stuckTicks >= 50) {
            if (stuckTicks > 50) {
                System.out.println("HOLY FUCK I'M STILL STUCK!!!");
            } else {
                System.out.println("HOLY FUCK I AM STUCK!!!");
            }
            handleBeingStuck();
            return;
        }

        int dX = destination.getX();
        int dY = destination.getY();
        int dZ = destination.getZ();

        System.out.println("Entity is at: " + this.entity.getPosition() + "             Destination: " + this.destination);

        // It seems that this needs to be called continuously.....
        this.entity.getNavigator().tryMoveToXYZ((double)dX, (double)dY, (double)dZ, 0.8D);  // Called to jump properly (This doesn't do well with y positions that can't be reached... but this gets us out of holes!)
        this.entity.getMoveHelper().setMoveTo(dX, dY, dZ, 1);  // Called to get us out of the rut cause by the path navigator (This does well with y positions that can't be reached, but we jump like every 1000 ticks...)
    }

    @Override
    public boolean isDone() {
        //System.out.println("Distance b/w position and destination = " + distanceBetween(this.entity.getPosition(), this.destination));
        if (distanceBetween(this.entity.getPosition(), this.destination) <= 2) {
            System.out.println("~~~~~~~~~~ MoveToBlockTask " + Integer.toHexString(this.hashCode()) + " is Done! ~~~~~~~~~~");
            stuckTicks = 0;  // Resets stuck ticks
            return true;
        }
        return false;
    }

    private void handleBeingStuck() {
        ++stuckCnt;
        int x = this.entity.getPosition().getX();
        int z = this.entity.getPosition().getZ();
        BlockPos unstuckPos = new BlockPos(x + r.nextInt(5), destination.getY(), z + r.nextInt(5));  // Position to go to to get unstuck

        // Move to try and unstuck ourselves -> THIS NEEDS TO BE A TASK QUEUE... IDK IF THIS IS THE CORRECT PATH FORWARD
        addSubtask(
                new TaskQueue()
                        .then(new MoveToBlockTask(world, entity, unstuckPos, stuckCnt))
        );
        //addSubtask(new MoveToBlockTask(entity, unstuckPos));
    }

    private boolean inHole() {
        // Get surrounding terrain layout
        // If lowest y > 1, return true
        return true;
    }

    private void digYourselfOut() {
        System.out.println("          DIG MOFO DIG!!!          ");
        int blocksAway = 1;
        int levelsAboveFeet = 1;

        // Find which direction we are facing
        System.out.println("My Yaw is " + this.entity.rotationYaw);
        int dir = getDirectionFacing(this.entity);

        int x1 = 0;
        int z1 = 0;
        int x2 = 0;
        int z2 = 0;
        int x3 = 0;
        int z3 = 0;
        if (dir == 1) {
            System.out.println("I'm facing South");
            x1 = 0;
            z1 = 1;
            x2 = 1;
            z2 = z1;
            x3 = -1;
            z3 = z1;
        } else if (dir == 2) {
            System.out.println("I'm facing West");
            x1 = -1;
            z1 = 0;
            x2 = x1;
            z2 = 1;
            x3 = x1;
            z3 = -1;
        } else if (dir == 3) {
            System.out.println("I'm facing North");
            x1 = 0;
            z1 = -1;
            x2 = 1;
            z2 = z1;
            x3 = -1;
            z3 = z1;
        } else if (dir == 4) {
            System.out.println("I'm facing East");
            x1 = 1;
            z1 = 0;
            x2 = x1;
            z2 = 1;
            x3 = x1;
            z3 = -1;
        }

        world.destroyBlock(this.entity.getPosition().add(blocksAway*x1,levelsAboveFeet,blocksAway*z1),true);
        world.destroyBlock(this.entity.getPosition().add(blocksAway*x2,levelsAboveFeet,blocksAway*z2),true);
        world.destroyBlock(this.entity.getPosition().add(blocksAway*x3,levelsAboveFeet,blocksAway*z3),true);
        this.stuckCnt = 0;  // Resets stuck count
    }
}
