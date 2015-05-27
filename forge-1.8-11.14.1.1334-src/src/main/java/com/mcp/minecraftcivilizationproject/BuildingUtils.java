package com.mcp.minecraftcivilizationproject;

import com.ibm.icu.util.BytesTrie.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BuildingUtils {
	public static final int OAK_WOOD = 17;
	public static final int OAK_PLANKS = 5;
	
	/**
	 * Makes a column of blocks
	 * @param w -  the world
	 * @param start - where the column starts
	 * @param height - the height of the column
	 * @param blockID - the block to be placed
	 */
	public static void makeColumn(World w, BlockPos start, int height, int blockID){
		//arbitrarily chose EW
		makeRectangleVerticalEW(w, start, 1, height, blockID);
	}
	
	/**
	 * Makes a row of blocks going North/South
	 * @param w -  the world
	 * @param start - where the row starts
	 * @param length - the length of the row
	 * @param blockID - the block to be placed
	 */
	public static void makeRowNS(World w, BlockPos start, int length, int blockID){
		makeRectangleHorizontal(w, start, 1, length, blockID);
	}
	
	/**
	 * Makes a row of blocks going East/West
	 * @param w -  the world
	 * @param start - where the row starts
	 * @param length - the length of the row
	 * @param blockID - the block to be placed
	 */
	public static void makeRowEW(World w, BlockPos start, int length, int blockID){
		makeRectangleHorizontal(w, start, length, 1, blockID);
	}
	
	/**
	 * Makes a square vertical going horizontally
	 * @param w - the world
	 * @param start -  the starting location
	 * @param size -  the size of the square
	 * @param blockID -  the block to be placed
	 */
	public static void makeSquareHorizontal(World w, BlockPos start, int size, int blockID){
		makeRectangleHorizontal(w, start, size, size, blockID);
	}
	
	/**
	 * Makes a square vertical going North/South
	 * @param w - the world
	 * @param start -  the starting location
	 * @param size -  the size of the square
	 * @param blockID -  the block to be placed
	 */
	public static void makeSquareVerticalNS(World w, BlockPos start, int size, int blockID){
		makeRectangleVerticalNS(w, start, size, size, blockID);
	}
	
	/**
	 * Makes a square vertical going East/West
	 * @param w - the world
	 * @param start -  the starting location
	 * @param size -  the size of the square
	 * @param blockID -  the block to be placed
	 */
	public static void makeSquareVerticalEW(World w, BlockPos start, int size, int blockID){
		makeRectangleVerticalEW(w, start, size, size, blockID);
	}
	
	/**
	 * Makes a rectangle going horizontally
	 * @param w -  the world
	 * @param start - the starting location
	 * @param xSize -  the x dimension
	 * @param zSize - the z dimension
	 * @param blockID - the block to be placed
	 */
	public static void makeRectangleHorizontal(World w, BlockPos start, int xSize, int zSize, int blockID){
		BlockPos current;
		for(int x = 0; x < xSize; ++x){
			for(int z = 0; z < zSize; ++z){
				current = start.add(x, 0, z);
				w.setBlockState(current, Block.getStateById(blockID));
			}
		}
	}
	
	/**
	 * Makes a rectangle vertically going North/South
	 * @param w -  the world
	 * @param start - the starting location
	 * @param ySize -  the y dimension
	 * @param zSize - the z dimension
	 * @param blockID - the block to be placed
	 */
	public static void makeRectangleVerticalNS(World w, BlockPos start, int ySize, int zSize, int blockID){
		BlockPos current;
		for(int z = 0; z < zSize; ++z){
			for(int y = 0; y < ySize; ++y){
				current = start.add(0, y, z);
				w.setBlockState(current, Block.getStateById(blockID));
			}
		}
	}
	
	/**
	 * Makes a rectangle vertically going East/West
	 * @param w -  the world
	 * @param start - the starting location
	 * @param xSize -  the x dimension
	 * @param ySize - the y dimension
	 * @param blockID - the block to be placed
	 */
	public static void makeRectangleVerticalEW(World w, BlockPos start, int xSize, int ySize, int blockID){
		BlockPos current;
		for(int y = 0; y < ySize; ++y){
			for(int x = 0; x < xSize; ++x){
				current = start.add(x, y, 0);
				w.setBlockState(current, Block.getStateById(blockID));
			}
		}
	}
	
	
	public static void arrayToBuilding(World w, int[][] layout, BlockPos location, int blockID){
		int x = 0;
		int z = 0;
		for(int[] row : layout){
			for(int col: row){
				makeColumn(w, location.add(x, 0, z), col, blockID);
				++z;
			}
			++x;
			z = 0;
		}
	}
}
