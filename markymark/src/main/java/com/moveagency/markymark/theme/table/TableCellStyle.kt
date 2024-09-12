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
import androidx.compose.ui.text.TextStyle
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding

/**
 * Theming attributes used when rendering table cells.
 *
 * @property padding The padding around the text inside the cell.
 * @property textStyle The text style used for the text inside the cell.
 */
@Immutable
data class TableCellStyle private constructor(
    val padding: Padding,
    val textStyle: TextStyle,
) {

    /**
     * Builder class for constructing instances of [TableCellStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the padding around the text inside the cell.
         */
        private var padding = Padding.Builder()

        /**
         * The text style used for the text inside the cell.
         */
        var textStyle = TextStyle()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            padding = builder.padding
            textStyle = builder.textStyle
        }

        /**
         * Includes the configuration from an existing [TableCellStyle] object into this builder.
         *
         * This will include the padding and text style from the provided [style].
         *
         * @param style The [TableCellStyle] instance whose configuration should be included.
         */
        fun include(style: TableCellStyle) {
            padding.include(style.padding)
            textStyle = style.textStyle
        }

        /**
         * Configures the padding around the text inside the cell.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Builds a new [TableCellStyle] instance with the current configuration.
         *
         * @return A [TableCellStyle] object with the set properties.
         */
        internal fun build() = TableCellStyle(
            padding = padding.build(),
            textStyle = textStyle,
        )
    }
}
