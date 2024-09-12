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
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Theming attributes used when rendering the outline dividers of a table.
 *
 * @property left The style for the left outline divider of the table.
 * @property top The style for the top outline divider of the table.
 * @property right The style for the right outline divider of the table.
 * @property bottom The style for the bottom outline divider of the table.
 */
@Immutable
data class TableOutlineStyle private constructor(
    val left: TableDividerStyle,
    val top: TableDividerStyle,
    val right: TableDividerStyle,
    val bottom: TableDividerStyle,
) {

    /**
     * Builder class for constructing instances of [TableOutlineStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the style of the left outline divider.
         */
        private var left = TableDividerStyle.Builder()

        /**
         * Builder for configuring the style of the top outline divider.
         */
        private var top = TableDividerStyle.Builder()

        /**
         * Builder for configuring the style of the right outline divider.
         */
        private var right = TableDividerStyle.Builder()

        /**
         * Builder for configuring the style of the bottom outline divider.
         */
        private var bottom = TableDividerStyle.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override the properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            left = builder.left
            top = builder.top
            right = builder.right
            bottom = builder.bottom
        }

        /**
         * Includes the configuration from an existing [TableOutlineStyle] object into this builder.
         *
         * This will include the outline styles for the left, top, right, and bottom dividers from the provided [style].
         *
         * @param style The [TableOutlineStyle] instance whose configuration should be included.
         */
        fun include(style: TableOutlineStyle) {
            left.include(style.left)
            top.include(style.top)
            right.include(style.right)
            bottom.include(style.bottom)
        }

        /**
         * Configures the style for the left outline divider.
         *
         * @param block A lambda to configure the [TableDividerStyle.Builder].
         */
        fun left(block: TableDividerStyle.Builder.() -> Unit) = block(left)

        /**
         * Configures the style for the top outline divider.
         *
         * @param block A lambda to configure the [TableDividerStyle.Builder].
         */
        fun top(block: TableDividerStyle.Builder.() -> Unit) = block(top)

        /**
         * Configures the style for the right outline divider.
         *
         * @param block A lambda to configure the [TableDividerStyle.Builder].
         */
        fun right(block: TableDividerStyle.Builder.() -> Unit) = block(right)

        /**
         * Configures the style for the bottom outline divider.
         *
         * @param block A lambda to configure the [TableDividerStyle.Builder].
         */
        fun bottom(block: TableDividerStyle.Builder.() -> Unit) = block(bottom)

        /**
         * Configures the style for both the left and right outline dividers.
         *
         * @param block A lambda to configure the [TableDividerStyle.Builder].
         */
        fun horizontal(block: TableDividerStyle.Builder.() -> Unit) {
            block(left)
            block(right)
        }

        /**
         * Configures the style for both the top and bottom outline dividers.
         *
         * @param block A lambda to configure the [TableDividerStyle.Builder].
         */
        fun vertical(block: TableDividerStyle.Builder.() -> Unit) {
            block(top)
            block(bottom)
        }

        /**
         * Configures the style for all four outline dividers: left, top, right, and bottom.
         *
         * @param block A lambda to configure the [TableDividerStyle.Builder].
         */
        fun all(block: TableDividerStyle.Builder.() -> Unit) {
            block(left)
            block(top)
            block(right)
            block(bottom)
        }

        /**
         * Builds a new [TableOutlineStyle] instance with the current configuration.
         *
         * @return A [TableOutlineStyle] object with the set properties for the outline dividers.
         */
        internal fun build() = TableOutlineStyle(
            left = left.build(),
            top = top.build(),
            right = right.build(),
            bottom = bottom.build(),
        )
    }
}
