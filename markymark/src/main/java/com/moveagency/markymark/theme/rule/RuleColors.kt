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

@file:Suppress("DataClassPrivateConstructor")

package com.moveagency.markymark.theme.rule

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Represents the color scheme for horizontal rule elements.
 *
 * @property background The background color of the horizontal rule.
 */
@Immutable
data class RuleColors private constructor(
    val background: Color,
) {

    /**
     * Builder class for constructing instances of [RuleColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The background color of the horizontal rule. Default is [Black].
         */
        var background = Black

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override the background color with the one from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            background = builder.background
        }

        /**
         * Includes the configuration from an existing [RuleColors] object into this builder.
         *
         * This will override the background color with the one from the provided [colors].
         *
         * @param colors The `RuleColors` instance whose configuration should be included.
         */
        fun include(colors: RuleColors) {
            background = colors.background
        }

        /**
         * Builds a new [RuleColors] instance with the current configuration.
         *
         * @return A `RuleColors` object with the set background color.
         */
        internal fun build() = RuleColors(
            background = background,
        )
    }
}
