package com.minecraftcivproject.mcp.common.entity.task;

import com.minecraftcivproject.mcp.common.entity.task.core.Task;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

import static com.minecraftcivproject.mcp.utils.PositionUtils.distanceBetween;

public class MoveToBlockTask extends Task {

    private final EntityLiving entity;
    private final BlockPos destination;
    private BlockPos lastPos;
    private int tickCnt;
    private int stuckCnt;  // Number of times we were stuck
    private Random r = new Random();

    public MoveToBlockTask(EntityLiving entity, BlockPos destination) {
        this.entity = entity;
        this.destination = destination;
        this.lastPos = this.entity.getPosition();
    }

    @Override
    public void start() {
        System.out.println("MoveToBlockTask startExecuting was called");
        this.entity.getMoveHelper().setMoveTo(destination.getX(), destination.getY(), destination.getZ(), 1);
    }

    @Override
    public void onTick() {

        if (isRunningSubtasks()) {
            System.out.println("MoveToBlockTask updateTask was cut short...");
            return;
        }

        System.out.println("Last pos: " + this.lastPos);
        System.out.println("Current pos: " + this.entity.getPosition());
        if (this.lastPos.equals(this.entity.getPosition())) {
            ++tickCnt;
        } else {
            tickCnt = 0;
        }

        this.lastPos = this.entity.getPosition();

        System.out.println("Tick count: " + this.tickCnt);
        if (tickCnt > 100) {
            handleBeingStuck();
            return;
        }

        System.out.println("The Entity is at: " + this.entity.getPosition());
        System.out.println("The destination is: [" + destination.getX() + "," + destination.getY() + "," + destination.getZ());

        this.entity.getMoveHelper().setMoveTo(destination.getX(), destination.getY(), destination.getZ(), 1);
    }

    @Override
    public boolean isDone(){
        return distanceBetween(this.entity.getPosition(), this.destination) < 2;
    }

    private void handleBeingStuck(){
        int x = this.entity.getPosition().getX();
        int z = this.entity.getPosition().getZ();
        BlockPos unstuckPos = new BlockPos(x + r.nextInt(2), destination.getY(), z + r.nextInt(2));

        // move to try and unstuck ourselves
        addSubtask(new MoveToBlockTask(entity, unstuckPos));
    }
}
