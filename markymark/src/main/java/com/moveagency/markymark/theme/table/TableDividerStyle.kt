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

package com.moveagency.markymark.theme.table

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Theming attributes used when rendering table dividers.
 *
 * @property thickness The thickness of the table dividers, defined in [Dp].
 */
@Immutable
data class TableDividerStyle private constructor(
    val thickness: Dp,
) {

    /**
     * Builder class for constructing instances of [TableDividerStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The thickness of the table dividers. Default is [Dp.Unspecified].
         */
        var thickness = Dp.Unspecified

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override the thickness with the value from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            thickness = builder.thickness
        }

        /**
         * Includes the configuration from an existing [TableDividerStyle] object into this builder.
         *
         * This will override the thickness with the value from the provided [style].
         *
         * @param style The [TableDividerStyle] instance whose configuration should be included.
         */
        fun include(style: TableDividerStyle) {
            thickness = style.thickness
        }

        /**
         * Builds a new [TableDividerStyle] instance with the current configuration.
         *
         * @return A [TableDividerStyle] object with the set thickness.
         */
        internal fun build() = TableDividerStyle(
            thickness = thickness,
        )
    }
}
