package lotb.entities.ai.brain.tasks;

import java.util.Map;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class LookForBedTask extends Task<LivingEntity> {
	public LookForBedTask(Map<MemoryModuleType<?>, MemoryModuleStatus> requiredMemoryStateIn) {
		super(requiredMemoryStateIn);
	}

	private boolean posIsBed(ServerWorld world, BlockPos pos) {
		return world.getBlockState(pos).isIn(BlockTags.BEDS);
	}
}
