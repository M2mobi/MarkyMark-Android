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

package com.moveagency.markymark.theme.quote

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import com.moveagency.markymark.theme.MarkyMarkColors
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * A theme to be used for the colors of a block quote and its content.
 *
 * @property background The background color of the block quote.
 * @property colors The theme for the colors of the content inside the block quote. If null, the previous levels colors
 *                  will be used.
 */
@Immutable
data class BlockQuoteTheme private constructor(
    val background: Color,
    val indicator: Color,
    val colors: MarkyMarkColors?,
) {

    /**
     * Builder class for constructing instances of [BlockQuoteTheme].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The background color of the block quote. Default is [Transparent].
         */
        var background = Transparent

        /**
         * The color of the indicator of the block quote. Default is [Color.Transparent].
         */
        var indicator = Transparent

        /**
         * Builder for configuring the theme of the colors used within the block quote.
         */
        private var colors: MarkyMarkColors.Builder? = null

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            background = builder.background
            indicator = builder.indicator
            colors = builder.colors
        }

        /**
         * Includes the configuration from an existing [BlockQuoteTheme] object into this builder.
         *
         * This will include the background color and, if present, the color theme from the provided [theme].
         *
         * @param theme The [BlockQuoteTheme] instance whose configuration should be included.
         */
        fun include(theme: BlockQuoteTheme) {
            background = theme.background
            indicator = theme.indicator
            theme.colors?.let {
                colors = MarkyMarkColors.Builder().apply { include(it) }
            }
        }

        /**
         * Configures the theme for the colors used within the block quote.
         *
         * @param block A lambda to configure the [MarkyMarkColors.Builder].
         */
        fun colors(block: MarkyMarkColors.Builder.() -> Unit) {
            if (colors == null) colors = MarkyMarkColors.Builder()
            block(colors!!)
        }

        /**
         * Builds a new [BlockQuoteTheme] instance with the current configuration.
         *
         * @return A [BlockQuoteTheme] object with the set properties.
         */
        internal fun build() = BlockQuoteTheme(
            background = background,
            indicator = indicator,
            colors = colors?.build(),
        )
    }
}
