package com.minecraftcivproject.mcp.common.entity.task;

import com.minecraftcivproject.mcp.common.entity.task.core.OneTimeTask;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;

import static com.minecraftcivproject.mcp.utils.PositionUtils.distanceBetween;

public class MoveToBlockTask extends OneTimeTask {

    private final EntityLiving entity;
    private final BlockPos destination;

    public MoveToBlockTask(EntityLiving entity, BlockPos destination){
        this.entity = entity;
        this.destination = destination;
    }

    @Override
    public void updateTask(){
        if(distanceBetween(this.entity.getPosition(), this.destination) < 2){
            setDone();
        }else{
            this.entity.getMoveHelper().setMoveTo(destination.getX(), destination.getY(), destination.getZ(), 1);
        }
    }
}
