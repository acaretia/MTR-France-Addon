package fr.adlmrl.mtrfra.mod.util;

import fr.adlmrl.mtrfra.mod.Init;
import org.mtr.mapping.mapper.GraphicsHolder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class GuiFill {

    private static Field drawContextField;
    private static Method fillMethod;
    private static Method fillGradientMethod;
    private static boolean failed;

    private GuiFill() {}

    private static boolean ensureInit(GraphicsHolder graphicsHolder) {
        if (failed) {
            return false;
        }
        if (fillMethod != null) {
            return true;
        }
        try {
            drawContextField = GraphicsHolder.class.getDeclaredField("drawContext");
            drawContextField.setAccessible(true);
            final Class<?> drawContextClass = drawContextField.get(graphicsHolder).getClass();
            fillMethod = drawContextClass.getMethod("fill", int.class, int.class, int.class, int.class, int.class);
            fillGradientMethod = drawContextClass.getMethod("fillGradient", int.class, int.class, int.class, int.class, int.class, int.class);
            return true;
        } catch (Exception e) {
            failed = true;
            Init.LOGGER.error("[GuiFill] reflection into GraphicsHolder.drawContext failed, falling back to no decorative fills", e);
            return false;
        }
    }

    public static void fill(GraphicsHolder graphicsHolder, int x1, int y1, int x2, int y2, int color) {
        if (!ensureInit(graphicsHolder)) {
            return;
        }
        try {
            fillMethod.invoke(drawContextField.get(graphicsHolder), x1, y1, x2, y2, color);
        } catch (Exception e) {
            failed = true;
            Init.LOGGER.error("[GuiFill] fill() invocation failed, falling back to no decorative fills", e);
        }
    }

    static void fillGradient(GraphicsHolder graphicsHolder, int x1, int y1, int x2, int y2, int colorTop, int colorBottom) {
        if (!ensureInit(graphicsHolder)) {
            return;
        }
        try {
            fillGradientMethod.invoke(drawContextField.get(graphicsHolder), x1, y1, x2, y2, colorTop, colorBottom);
        } catch (Exception e) {
            failed = true;
            Init.LOGGER.error("[GuiFill] fillGradient() invocation failed, falling back to no decorative fills", e);
        }
    }

    public static int opaque(int rgb) {
        return 0xFF000000 | rgb;
    }

}
