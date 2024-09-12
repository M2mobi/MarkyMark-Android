/*
 * Copyright © 2024 Move
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the “Software”), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

@file:Suppress("DataClassPrivateConstructor", "MemberVisibilityCanBePrivate")

package com.moveagency.markymark.theme.rule

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moveagency.markymark.composable.MarkyMarkRule
import com.moveagency.markymark.model.composable.Rule
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding

/**
 * Theming attributes used for rendering [Rule]s with [MarkyMarkRule].
 *
 * @property thickness The thickness of the rule, defined in [Dp].
 * @property padding The padding around the rule.
 */
@Immutable
data class RuleStyle private constructor(
    val thickness: Dp,
    val padding: Padding,
) {

    /**
     * Builder class for constructing instances of [RuleStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The thickness of the rule, defined in [Dp]. Default is `1.dp`.
         */
        var thickness = 1.dp

        /**
         * Builder for configuring the padding around the rule.
         */
        private var padding = Padding.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            padding = builder.padding
            thickness = builder.thickness
        }

        /**
         * Includes the configuration from an existing [RuleStyle] object into this builder.
         *
         * This will include the thickness and padding from the provided [style].
         *
         * @param style The [RuleStyle] instance whose configuration should be included.
         */
        fun include(style: RuleStyle) {
            thickness = style.thickness
            padding.include(style.padding)
        }

        /**
         * Configures the padding around the rule.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Builds a new [RuleStyle] instance with the current configuration.
         *
         * @return A [RuleStyle] object with the set properties for the rule.
         */
        internal fun build() = RuleStyle(
            thickness = thickness,
            padding = padding.build(),
        )
    }
}
