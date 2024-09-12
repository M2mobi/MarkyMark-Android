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
 * Theming attributes used when rendering the body of a table.
 *
 * @property cell The style applied to the cells in the table body.
 * @property horizontalDivider The style applied to the horizontal dividers between rows in the table body.
 * @property verticalDivider The style applied to the vertical dividers between columns in the table body.
 */
@Immutable
data class TableBodyStyle private constructor(
    val cell: TableCellStyle,
    val horizontalDivider: TableDividerStyle,
    val verticalDivider: TableDividerStyle,
) {

    /**
     * Builder class for constructing instances of [TableBodyStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the style of cells in the table body.
         */
        private var cell = TableCellStyle.Builder()

        /**
         * Builder for configuring the style of horizontal dividers between rows in the table body.
         */
        private var horizontalDivider = TableDividerStyle.Builder()

        /**
         * Builder for configuring the style of vertical dividers between columns in the table body.
         */
        private var verticalDivider = TableDividerStyle.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            cell = builder.cell
            horizontalDivider = builder.horizontalDivider
            verticalDivider = builder.verticalDivider
        }

        /**
         * Includes the configuration from an existing [TableBodyStyle] object into this builder.
         *
         * This will include the cell style, horizontal dividers, and vertical dividers from the provided [style].
         *
         * @param style The [TableBodyStyle] instance whose configuration should be included.
         */
        fun include(style: TableBodyStyle) {
            cell.include(style.cell)
            horizontalDivider.include(style.horizontalDivider)
            verticalDivider.include(style.verticalDivider)
        }

        /**
         * Configures the style of cells in the table body.
         *
         * @param block A lambda to configure the [TableCellStyle.Builder].
         */
        fun cell(block: TableCellStyle.Builder.() -> Unit) = block(cell)

        /**
         * Configures the style of horizontal dividers between rows in the table body.
         *
         * @param block A lambda to configure the [TableDividerStyle.Builder].
         */
        fun horizontalDivider(block: TableDividerStyle.Builder.() -> Unit) = block(horizontalDivider)

        /**
         * Configures the style of vertical dividers between columns in the table body.
         *
         * @param block A lambda to configure the [TableDividerStyle.Builder].
         */
        fun verticalDivider(block: TableDividerStyle.Builder.() -> Unit) = block(verticalDivider)

        /**
         * Builds a new [TableBodyStyle] instance with the current configuration.
         *
         * @return A [TableBodyStyle] object with the set properties for the table body.
         */
        internal fun build() = TableBodyStyle(
            cell = cell.build(),
            horizontalDivider = horizontalDivider.build(),
            verticalDivider = verticalDivider.build(),
        )
    }
}
