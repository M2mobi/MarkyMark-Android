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

package com.moveagency.markymark.theme.table

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Represents the color scheme for the outline of a table, including the left, top, right, and bottom edges.
 *
 * @property left The color for the left edge of the table outline.
 * @property top The color for the top edge of the table outline.
 * @property right The color for the right edge of the table outline.
 * @property bottom The color for the bottom edge of the table outline.
 */
@Immutable
data class TableOutlineColors private constructor(
    val left: Color,
    val top: Color,
    val right: Color,
    val bottom: Color,
) {

    /**
     * Builder class for constructing instances of [TableOutlineColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The color for the left edge of the table outline. If not set, defaults to [horizontal] or [all].
         */
        var left: Color? = null

        /**
         * The color for the top edge of the table outline. If not set, defaults to [vertical] or [all].
         */
        var top: Color? = null

        /**
         * The color for the right edge of the table outline. If not set, defaults to [horizontal] or [all].
         */
        var right: Color? = null

        /**
         * The color for the bottom edge of the table outline. If not set, defaults to [vertical] or [all].
         */
        var bottom: Color? = null

        /**
         * The default color for the horizontal edges of the table outline. If not set, defaults to [all].
         */
        var horizontal: Color? = null

        /**
         * The default color for the vertical edges of the table outline. If not set, defaults to [all].
         */
        var vertical: Color? = null

        /**
         * The default color to be applied to all edges if not individually specified. Default is [Black].
         */
        var all = Black

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            left = builder.left
            top = builder.top
            right = builder.right
            bottom = builder.bottom
            horizontal = builder.horizontal
            vertical = builder.vertical
            all = builder.all
        }

        /**
         * Includes the configuration from an existing [TableOutlineColors] object into this builder.
         *
         * If all edges of the provided [colors] are the same, [all] will be set. If this is not the case however we
         * check if the axes match.
         *
         * If [TableOutlineColors.left] and [TableOutlineColors.right] are the same then [horizontal] will be set. If
         * they are not the same [left] and [right] will be set individually.
         *
         * If [TableOutlineColors.top] and [TableOutlineColors.bottom] are the same then [vertical] will be set. If
         * they are not the same [top] and [bottom] will be set individually.
         *
         * @param colors The [TableOutlineColors] instance whose configuration should be included.
         */
        fun include(colors: TableOutlineColors) {
            if (colors.left == colors.top && colors.left == colors.right && colors.left == colors.bottom) {
                all = colors.left
            } else {
                if (colors.left == colors.right) {
                    horizontal = colors.left
                } else {
                    left = colors.left
                    right = colors.right
                }

                if (colors.top == colors.bottom) {
                    vertical = colors.top
                } else {
                    top = colors.top
                    bottom = colors.bottom
                }
            }
        }

        /**
         * Builds a new [TableOutlineColors] instance with the current configuration.
         *
         * If left or right edges are not set, they will default to the [horizontal] color. If top or bottom edges are
         * not set, they will default to the [vertical] color. if [horizontal] and/or [vertical] are not set they will
         * default to [all].
         *
         * @return A [TableOutlineColors] object with the set properties.
         */
        internal fun build() = TableOutlineColors(
            left = left ?: horizontal ?: all,
            top = top ?: vertical ?: all,
            right = right ?: horizontal ?: all,
            bottom = bottom ?: vertical ?: all,
        )
    }
}
