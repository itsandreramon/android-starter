package app.example.ui.screens.mvrx;

import com.airbnb.mvrx.MavericksTestOverrides;
import com.airbnb.mvrx.test.MvRxTestRule;

/**
 * Used as a proxy between {@link MvRxTestRule} and MvRx.
 * this is Java because the flag is package private.
 */
public class MvRxTestOverridesProxy {

    public static void forceDisableLifecycleAwareObserver(Boolean disableLifecycleAwareObserver) {
        MavericksTestOverrides.FORCE_DISABLE_LIFECYCLE_AWARE_OBSERVER = disableLifecycleAwareObserver;
    }
}
