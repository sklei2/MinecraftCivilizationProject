package com.minecraftcivproject.mcp.common.entity.task;

import com.minecraftcivproject.mcp.common.entity.task.core.MultiStepTask;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

import static com.minecraftcivproject.mcp.utils.PositionUtils.distanceBetween;

public class MoveToBlockTask extends MultiStepTask {

    private final EntityLiving entity;
    private final BlockPos destination;
    private BlockPos lastPos;
    private int tickCnt;
    private int stuckCnt;  // Number of times we were stuck
    private Random r = new Random();
    private boolean isTryingToGetUnstuck;  // Defaulted to false
    private MoveToBlockTask unstuckTask;

    public MoveToBlockTask(EntityLiving entity, BlockPos destination) {
        this.entity = entity;
        this.destination = destination;
        this.lastPos = this.entity.getPosition();
    }

    @Override
    public void startExecuting() {
        System.out.println("MoveToBlockTask startExecuting was called");
        this.entity.getMoveHelper().setMoveTo(destination.getX(), destination.getY(), destination.getZ(), 1);
    }

    @Override
    public void updateTask() {
        System.out.println("MoveToBlockTask super.updateTask was called");
        super.updateTask();

        if (this.unstuckTask != null && !this.unstuckTask.isDone()) {
            System.out.println("MoveToBlockTask updateTask was cut short...");
            return;
        }

        if (this.lastPos == this.entity.getPosition()) {
            ++tickCnt;
        }

        System.out.println("Tick count: " + this.tickCnt);
        if (tickCnt > 100) {
            int x = this.entity.getPosition().getX();
            int z = this.entity.getPosition().getZ();
            BlockPos unstuckPos = new BlockPos(x + r.nextInt(2), destination.getY(), z + r.nextInt(2));
            this.unstuckTask = new MoveToBlockTask(entity, unstuckPos);
            addSubtask(this.unstuckTask);
        }

        System.out.println("The Entity is at: " + this.entity.getPosition());
        System.out.println("The destination is: [" + destination.getX() + "," + destination.getY() + "," + destination.getZ());
        if (distanceBetween(this.entity.getPosition(), this.destination) < 2) {
            tickCnt = 0;
            setDone();
        }

        this.lastPos = this.entity.getPosition();
    }

    @Override
    public void onNoRemainingSubtasks() {
        this.startExecuting();  // Resets task destination
    }

    @Override
    public boolean isDone() {
        return distanceBetween(this.entity.getPosition(), this.destination) < 2;
    }
}
