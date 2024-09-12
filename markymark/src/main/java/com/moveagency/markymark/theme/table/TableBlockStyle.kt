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

@file:Suppress("DataClassPrivateConstructor", "MemberVisibilityCanBePrivate", "unused")

package com.moveagency.markymark.theme.table

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moveagency.markymark.composable.MarkyMarkTable
import com.moveagency.markymark.model.composable.TableBlock
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding

/**
 * Theming attributes used for rendering [TableBlock]s with [MarkyMarkTable].
 *
 * The header is always the first row of the table, as defined by the
 * [Github Flavored Markdown](https://github.github.com/gfm/#tables-extension-) specification.
 *
 * The outline refers to the border around the table. The header divider is the horizontal divider below the header row.
 * The body dividers are the horizontal and vertical dividers between cells.
 *
 * @property padding The padding around the table.
 * @property cellMinWidth The minimum width of the cells, defined in [Dp].
 * @property cellMaxWidth The maximum width of the cells, defined in [Dp].
 * @property cellMinHeight The minimum height of the cells, defined in [Dp].
 * @property cellMaxHeight The maximum height of the cells, defined in [Dp].
 * @property header The style applied to the header row of the table.
 * @property body The style applied to the body of the table, including cell and divider styles.
 * @property outline The style applied to the outline (border) around the table.
 */
@Immutable
data class TableBlockStyle private constructor(
    val padding: Padding,
    val cellMinWidth: Dp,
    val cellMaxWidth: Dp,
    val cellMinHeight: Dp,
    val cellMaxHeight: Dp,
    val header: TableHeaderStyle,
    val body: TableBodyStyle,
    val outline: TableOutlineStyle,
) {

    /**
     * Builder class for constructing instances of [TableBlockStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the padding around the table.
         */
        private var padding = Padding.Builder()

        /**
         * The minimum width of the cells, defined in [Dp]. Default is `0.dp`.
         */
        var cellMinWidth = 0.dp

        /**
         * The maximum width of the cells, defined in [Dp]. Default is [Dp.Infinity].
         */
        var cellMaxWidth = Dp.Infinity

        /**
         * The minimum height of the cells, defined in [Dp]. Default is `0.dp`.
         */
        var cellMinHeight = 0.dp

        /**
         * The maximum height of the cells, defined in [Dp]. Default is [Dp.Infinity].
         */
        var cellMaxHeight = Dp.Infinity

        /**
         * Builder for configuring the style of the header row of the table.
         */
        private var header = TableHeaderStyle.Builder()

        /**
         * Builder for configuring the style of the body of the table.
         */
        private var body = TableBodyStyle.Builder()

        /**
         * Builder for configuring the style of the outline (border) around the table.
         */
        private var outline = TableOutlineStyle.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            padding = builder.padding
            cellMinWidth = builder.cellMinWidth
            cellMaxWidth = builder.cellMaxWidth
            cellMinHeight = builder.cellMinHeight
            cellMaxHeight = builder.cellMaxHeight
            header = builder.header
            body = builder.body
            outline = builder.outline
        }

        /**
         * Includes the configuration from an existing [TableBlockStyle] object into this builder.
         *
         * This will include the padding, cell dimensions, header style, body style, and outline style
         * from the provided [style].
         *
         * @param style The [TableBlockStyle] instance whose configuration should be included.
         */
        fun include(style: TableBlockStyle) {
            padding.include(style.padding)
            cellMinWidth = style.cellMinWidth
            cellMaxWidth = style.cellMaxWidth
            cellMinHeight = style.cellMinHeight
            cellMaxHeight = style.cellMaxHeight
            header.include(style.header)
            body.include(style.body)
            outline.include(style.outline)
        }

        /**
         * Configures the padding around the table.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Configures the style of the header row of the table.
         *
         * @param block A lambda to configure the [TableHeaderStyle.Builder].
         */
        fun header(block: TableHeaderStyle.Builder.() -> Unit) = block(header)

        /**
         * Configures the style of the body of the table.
         *
         * @param block A lambda to configure the [TableBodyStyle.Builder].
         */
        fun body(block: TableBodyStyle.Builder.() -> Unit) = block(body)

        /**
         * Configures the style of the outline (border) around the table.
         *
         * @param block A lambda to configure the [TableOutlineStyle.Builder].
         */
        fun outline(block: TableOutlineStyle.Builder.() -> Unit) = block(outline)

        /**
         * Builds a new [TableBlockStyle] instance with the current configuration.
         *
         * @return A [TableBlockStyle] object with the set properties for the table.
         */
        internal fun build() = TableBlockStyle(
            padding = padding.build(),
            cellMinWidth = cellMinWidth,
            cellMaxWidth = cellMaxWidth,
            cellMinHeight = cellMinHeight,
            cellMaxHeight = cellMaxHeight,
            header = header.build(),
            body = body.build(),
            outline = outline.build(),
        )
    }
}
