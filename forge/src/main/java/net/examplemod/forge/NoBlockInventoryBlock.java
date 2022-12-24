//package net.examplemod.forge;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.screen.NamedScreenHandlerFactory;
//import net.minecraft.screen.ScreenHandler;
//import net.minecraft.text.Text;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.Hand;
//import net.minecraft.util.hit.BlockHitResult;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//public class NoBlockInventoryBlock extends Block implements NamedScreenHandlerFactory {
//	public NoBlockInventoryBlock(Settings settings) {
//		super(settings);
//	}
//
//	@Override
//	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//		player.openHandledScreen(this);
//		return world.isClient ? ActionResult.SUCCESS : ActionResult.CONSUME;
//	}
//
//	@Override
//	public Text getDisplayName() {
//		return getName();
//	}
//
//	@Nullable
//	@Override
//	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
//		return new ReallySimpleDescription(syncId, inv);
//	}
//}
