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

@file:Suppress("DataClassPrivateConstructor", "unused")

package com.moveagency.markymark.theme.list

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Represents the color scheme for ordered list elements.
 *
 * @property text The text color for the list items.
 * @property indicator The color of the indicator for the list items.
 */
@Immutable
data class OrderedListColors private constructor(
    override val text: Color,
    val indicator: Color,
) : ListItemColors {

    /**
     * Builder class for constructing instances of [OrderedListColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The text color for the list items. Default is [Black].
         */
        var text = Black

        /**
         * The color of the indicator for the list items. When set, this will override the default [text] color for the
         * indicator.
         */
        var indicator: Color? = null

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            text = builder.text
            indicator = builder.indicator
        }

        /**
         * Includes the configuration from an existing [OrderedListColors] object into this builder.
         *
         * If the `text` and `indicator` colors of the provided [colors] are the same, only the `text` color will be
         * used. Otherwise, both `text` and `indicator` colors will be included.
         *
         * @param colors The `OrderedListColors` instance whose configuration should be included.
         */
        fun include(colors: OrderedListColors) {
            if (colors.text == colors.indicator) {
                text = colors.text
            } else {
                text = colors.text
                indicator = colors.indicator
            }
        }

        /**
         * Builds a new [OrderedListColors] instance with the current configuration.
         *
         * If the `indicator` color is not set, it will default to the `text` color.
         *
         * @return An `OrderedListColors` object with the set properties.
         */
        internal fun build() = OrderedListColors(
            text = text,
            indicator = indicator ?: text,
        )
    }
}
