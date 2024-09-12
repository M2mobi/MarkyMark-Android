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
 * Represents the color scheme for a table, including the header, body, and outline.
 *
 * @property header The color scheme for the table header rows.
 * @property body The color scheme for the table body rows.
 * @property outline The color scheme for the table outline, including the edges of the table.
 */
@Immutable
data class TableColors private constructor(
    val header: TableRowColors,
    val body: TableRowColors,
    val outline: TableOutlineColors,
) {

    /**
     * Builder class for constructing instances of [TableColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for constructing the color scheme for the table header rows.
         */
        private var header = TableRowColors.Builder()

        /**
         * Builder for constructing the color scheme for the table body rows.
         */
        private var body = TableRowColors.Builder()

        /**
         * Builder for constructing the color scheme for the table outline.
         */
        private var outline = TableOutlineColors.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            header = builder.header
            body = builder.body
            outline = builder.outline
        }

        /**
         * Includes the configuration from an existing [TableColors] object into this builder.
         *
         * This will include the header, body, and outline color schemes from the provided [colors].
         *
         * @param colors The [TableColors] instance whose configuration should be included.
         */
        fun include(colors: TableColors) {
            header.include(colors.header)
            body.include(colors.body)
            outline.include(colors.outline)
        }

        /**
         * Configures the color scheme for the table header rows.
         *
         * @param block A lambda to configure the [TableRowColors.Builder].
         */
        fun header(block: TableRowColors.Builder.() -> Unit) = block(header)

        /**
         * Configures the color scheme for the table body rows.
         *
         * @param block A lambda to configure the [TableRowColors.Builder].
         */
        fun body(block: TableRowColors.Builder.() -> Unit) = block(body)

        /**
         * Configures the color scheme for the table outline.
         *
         * @param block A lambda to configure the [TableOutlineColors.Builder].
         */
        fun outline(block: TableOutlineColors.Builder.() -> Unit) = block(outline)

        /**
         * Builds a new [TableColors] instance with the current configuration.
         *
         * @return A [TableColors] object with the set properties for header, body, and outline.
         */
        internal fun build() = TableColors(
            header = header.build(),
            body = body.build(),
            outline = outline.build(),
        )
    }
}
