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
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Theming attributes used when rendering the header of a table.
 *
 * @property cell The style applied to the cells in the table header.
 * @property divider The style applied to the dividers between cells in the table header.
 */
@Immutable
data class TableHeaderStyle private constructor(
    val cell: TableCellStyle,
    val divider: TableDividerStyle,
) {

    /**
     * Builder class for constructing instances of [TableHeaderStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the style of cells in the table header.
         */
        private var cell = TableCellStyle.Builder()

        /**
         * Builder for configuring the style of dividers between cells in the table header.
         */
        private var divider = TableDividerStyle.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            cell = builder.cell
            divider = builder.divider
        }

        /**
         * Includes the configuration from an existing [TableHeaderStyle] object into this builder.
         *
         * This will include the cell style and dividers from the provided [style].
         *
         * @param style The [TableHeaderStyle] instance whose configuration should be included.
         */
        fun include(style: TableHeaderStyle) {
            cell.include(style.cell)
            divider.include(style.divider)
        }

        /**
         * Configures the style of cells in the table header.
         *
         * @param block A lambda to configure the [TableCellStyle.Builder].
         */
        fun cell(block: TableCellStyle.Builder.() -> Unit) = block(cell)

        /**
         * Configures the style of dividers between cells in the table header.
         *
         * @param block A lambda to configure the [TableDividerStyle.Builder].
         */
        fun divider(block: TableDividerStyle.Builder.() -> Unit) = block(divider)

        /**
         * Builds a new [TableHeaderStyle] instance with the current configuration.
         *
         * @return A [TableHeaderStyle] object with the set properties for the table header.
         */
        internal fun build() = TableHeaderStyle(
            cell = cell.build(),
            divider = divider.build(),
        )
    }
}
