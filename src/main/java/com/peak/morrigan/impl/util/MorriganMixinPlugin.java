package com.peak.morrigan.impl.util;

import net.acoyt.acornlib.api.template.CompatMixinPlugin;

/**
 * @author Chemthunder
 */
public class MorriganMixinPlugin extends CompatMixinPlugin {
    public MorriganMixinPlugin() {
        super("com.peak.morrigan.mixin.");
    }

    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return super.shouldApplyMixin(targetClassName, mixinClassName);
    }
}
