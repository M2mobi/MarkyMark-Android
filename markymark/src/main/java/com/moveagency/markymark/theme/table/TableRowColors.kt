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

package com.moveagency.markymark.theme.table

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Represents the color scheme for a table row, including text and dividers.
 *
 * @property text The text color for the row.
 * @property dividers The color scheme for the dividers between table cells in the row.
 */
@Immutable
data class TableRowColors private constructor(
    val text: Color,
    val divider: Color,
) {

    /**
     * Builder class for constructing instances of [TableRowColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The text color for the row. Default is [Black].
         */
        var text = Black

        /**
         * The color of the divider that separates the header from the body. Default is [Color.DarkGray].
         */
        var divider = DarkGray

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            text = builder.text
            divider = builder.divider
        }

        /**
         * Includes the configuration from an existing [TableRowColors] object into this builder.
         *
         * This will override all properties with the ones from the provided [colors].
         *
         * @param colors The [TableRowColors] instance whose configuration should be included.
         */
        fun include(colors: TableRowColors) {
            text = colors.text
            divider = colors.divider
        }

        /**
         * Builds a new [TableRowColors] instance with the current configuration.
         *
         * @return A [TableRowColors] object with the set properties.
         */
        internal fun build() = TableRowColors(
            text = text,
            divider = divider,
        )
    }
}
