package io.codex.encofree4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Model capability gates. These are easy to get wrong when adding a model, and the UI
 * hides or shows controls based on them, so keep the expectations explicit.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk=34)
public class EncoModelsTest {
    @Test public void x3GetsItsOwnCapabilitiesWithoutX4AdaptiveMode() {
        String name = "OPPO Enco X3";
        assertTrue(EncoModels.isSupported(name));
        assertTrue(EncoModels.isX3(name));
        assertTrue(EncoModels.supportsControl(name));
        assertTrue(EncoModels.hasNoiseControl(name));
        assertTrue(EncoModels.hasAncLevels(name));
        assertTrue(EncoModels.hasTransparencyVoice(name));
        assertFalse(EncoModels.hasAdaptiveMode(name));
    }

    @Test public void x4KeepsTheAdaptiveMajorModeOnly() {
        String name = "OPPO Enco X4";
        assertTrue(EncoModels.hasAdaptiveMode(name));
        assertTrue(EncoModels.hasAncLevels(name));
        assertFalse(EncoModels.hasTransparencyVoice(name));
    }

    @Test public void free4DoesNotExposeUnverifiedLevels() {
        String name = "OPPO Enco Free4";
        assertTrue(EncoModels.isSupported(name));
        assertFalse(EncoModels.hasAncLevels(name));
        assertFalse(EncoModels.hasTransparencyVoice(name));
        assertFalse(EncoModels.hasAdaptiveMode(name));
    }

    @Test public void namesAreNormalizedAndUnknownModelsFallBackToGenericArtwork() {
        assertTrue(EncoModels.isSupported(" OPPO Enco X3 "));
        assertFalse(EncoModels.isSupported("OPPO Enco Air4 Pro"));
        assertEquals(R.drawable.enco_device, EncoModels.deviceImage(null, "white", false));
        assertEquals(R.drawable.enco_x4_white_buds,
                EncoModels.deviceImage("OPPO Enco X4", "white", false));
    }

    @Test public void x3UsesItsOwnArtworkForBothStates() {
        // The X3 render ships bare earbuds only, so the in-case state reuses it.
        assertEquals(R.drawable.enco_x3_black_buds,
                EncoModels.deviceImage("OPPO Enco X3", "white", false));
        assertEquals(R.drawable.enco_x3_black_buds,
                EncoModels.deviceImage("OPPO Enco X3", "white", true));
    }

    @Test public void panelTitleUsesTheModelNameWhenKnown() {
        assertEquals("OPPO Enco X3", EncoModels.displayName("OPPO Enco X3"));
        assertEquals("OPPO Enco X4", EncoModels.displayName(" OPPO Enco X4 "));
        assertEquals("OPPO Enco Free4", EncoModels.displayName("OPPO Enco Free4"));
        assertEquals("OPPO Enco Clip", EncoModels.displayName("OPPO Enco Clip"));
        assertNull(EncoModels.displayName("OPPO Enco Air4 Pro"));
        assertNull(EncoModels.displayName(null));
    }
}
