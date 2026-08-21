package fr.adlmrl.mtrfra.mod.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.lang.reflect.Method;
import java.util.List;

public final class VersionCompat {

    private static final Registry<Block> BLOCK_REGISTRY = resolveBlockRegistry();

    private VersionCompat() {}

    @SuppressWarnings("unchecked")
    private static Registry<Block> resolveBlockRegistry() {
        try {
            return (Registry<Block>) Class.forName("net.minecraft.core.registries.BuiltInRegistries").getField("BLOCK").get(null);
        } catch (ReflectiveOperationException noBuiltInRegistries) {
            try {
                return (Registry<Block>) Registry.class.getField("BLOCK").get(null);
            } catch (ReflectiveOperationException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    public static Registry<Block> blockRegistry() {
        return BLOCK_REGISTRY;
    }

    public static Object createRandomSource(long seed) {
        try {
            final Class<?> randomSourceClass = Class.forName("net.minecraft.util.RandomSource");
            return randomSourceClass.getMethod("create", long.class).invoke(null, seed);
        } catch (ReflectiveOperationException noRandomSource) {
            return new java.util.Random(seed);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<BakedQuad> getQuads(BakedModel bakedModel, BlockState state, net.minecraft.core.Direction direction, Object random) {
        try {
            for (final Method method : BakedModel.class.getMethods()) {
                if (method.getName().equals("getQuads") && method.getParameterCount() == 3) {
                    return (List<BakedQuad>) method.invoke(bakedModel, state, direction, random);
                }
            }
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }
        throw new IllegalStateException("No matching BakedModel#getQuads overload found");
    }

    public static void mulPoseY(PoseStack poseStack, float degrees) {
        mulPoseAxis(poseStack, degrees, "Y", "rotationY");
    }

    public static void mulPoseX(PoseStack poseStack, float degrees) {
        mulPoseAxis(poseStack, degrees, "X", "rotationX");
    }

    private static void mulPoseAxis(PoseStack poseStack, float degrees, String vector3fAxisField, String quaternionfAxisMethod) {
        try {
            final Class<?> quaternionfClass = Class.forName("org.joml.Quaternionf");
            final Object quaternion = quaternionfClass.getConstructor().newInstance();
            quaternionfClass.getMethod(quaternionfAxisMethod, float.class).invoke(quaternion, (float) Math.toRadians(degrees));
            for (final Method method : PoseStack.class.getMethods()) {
                if (method.getName().equals("mulPose") && method.getParameterCount() == 1 && method.getParameterTypes()[0].isInstance(quaternion)) {
                    method.invoke(poseStack, quaternion);
                    return;
                }
            }
            throw new IllegalStateException("No matching PoseStack#mulPose(Quaternionf) overload found");
        } catch (ReflectiveOperationException noJoml) {
            try {
                final Class<?> vector3fClass = Class.forName("com.mojang.math.Vector3f");
                final Object axis = vector3fClass.getField(vector3fAxisField + "P").get(null);
                final Object quaternion = vector3fClass.getMethod("rotationDegrees", float.class).invoke(axis, degrees);
                for (final Method method : PoseStack.class.getMethods()) {
                    if (method.getName().equals("mulPose") && method.getParameterCount() == 1 && method.getParameterTypes()[0].isInstance(quaternion)) {
                        method.invoke(poseStack, quaternion);
                        return;
                    }
                }
                throw new IllegalStateException("No matching PoseStack#mulPose(Quaternion) overload found");
            } catch (ReflectiveOperationException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

}
