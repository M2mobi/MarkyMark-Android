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

package com.moveagency.markymark.theme

import androidx.compose.runtime.Immutable
import com.moveagency.markymark.theme.code.CodeBlockColors
import com.moveagency.markymark.theme.heading.HeadingsColors
import com.moveagency.markymark.theme.image.ImageColors
import com.moveagency.markymark.theme.list.ListColors
import com.moveagency.markymark.theme.paragraph.ParagraphColors
import com.moveagency.markymark.theme.rule.RuleColors
import com.moveagency.markymark.theme.table.TableColors

/**
 * Represents the overall color scheme for the Markdown renderer, including all elements such as headings, images,
 * paragraphs, rules, tables, lists, and code blocks.
 *
 * @property headings The color scheme for headings (H1 through H6).
 * @property image The color scheme for images, specifically the caption text.
 * @property paragraph The color scheme for paragraphs.
 * @property rule The color scheme for horizontal rules.
 * @property table The color scheme for tables, including the header, body, and outline.
 * @property list The color scheme for unordered, ordered, and task lists.
 * @property codeBlock The color scheme for code blocks.
 */
@Immutable
data class MarkyMarkColors private constructor(
    val headings: HeadingsColors,
    val image: ImageColors,
    val paragraph: ParagraphColors,
    val rule: RuleColors,
    val table: TableColors,
    val list: ListColors,
    val codeBlock: CodeBlockColors,
) {

    /**
     * Builder class for constructing instances of [MarkyMarkColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for constructing the color scheme for headings (H1 through H6).
         */
        private var headings = HeadingsColors.Builder()

        /**
         * Builder for constructing the color scheme for images.
         */
        private var image = ImageColors.Builder()

        /**
         * Builder for constructing the color scheme for paragraphs.
         */
        private var paragraph = ParagraphColors.Builder()

        /**
         * Builder for constructing the color scheme for horizontal rules.
         */
        private var rule = RuleColors.Builder()

        /**
         * Builder for constructing the color scheme for tables.
         */
        private var table = TableColors.Builder()

        /**
         * Builder for constructing the color scheme for lists.
         */
        private var list = ListColors.Builder()

        /**
         * Builder for constructing the color scheme for code blocks.
         */
        private var codeBlock = CodeBlockColors.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            headings = builder.headings
            image = builder.image
            paragraph = builder.paragraph
            rule = builder.rule
            table = builder.table
            list = builder.list
            codeBlock = builder.codeBlock
        }

        /**
         * Includes the configuration from an existing [MarkyMarkColors] object into this builder.
         *
         * This will include the color schemes for headings, images, paragraphs, rules, tables, lists, and code blocks
         * from the provided [colors].
         *
         * @param colors The [MarkyMarkColors] instance whose configuration should be included.
         */
        fun include(colors: MarkyMarkColors) {
            headings.include(colors.headings)
            image.include(colors.image)
            paragraph.include(colors.paragraph)
            rule.include(colors.rule)
            table.include(colors.table)
            list.include(colors.list)
            codeBlock.include(colors.codeBlock)
        }

        /**
         * Configures the color scheme for headings.
         *
         * @param block A lambda to configure the [HeadingsColors.Builder].
         */
        fun headings(block: HeadingsColors.Builder.() -> Unit) = block(headings)

        /**
         * Configures the color scheme for images.
         *
         * @param block A lambda to configure the [ImageColors.Builder].
         */
        fun image(block: ImageColors.Builder.() -> Unit) = block(image)

        /**
         * Configures the color scheme for paragraphs.
         *
         * @param block A lambda to configure the [ParagraphColors.Builder].
         */
        fun paragraph(block: ParagraphColors.Builder.() -> Unit) = block(paragraph)

        /**
         * Configures the color scheme for horizontal rules.
         *
         * @param block A lambda to configure the [RuleColors.Builder].
         */
        fun rule(block: RuleColors.Builder.() -> Unit) = block(rule)

        /**
         * Configures the color scheme for tables.
         *
         * @param block A lambda to configure the [TableColors.Builder].
         */
        fun table(block: TableColors.Builder.() -> Unit) = block(table)

        /**
         * Configures the color scheme for lists.
         *
         * @param block A lambda to configure the [ListColors.Builder].
         */
        fun list(block: ListColors.Builder.() -> Unit) = block(list)

        /**
         * Configures the color scheme for code blocks.
         *
         * @param block A lambda to configure the [CodeBlockColors.Builder].
         */
        fun codeBlock(block: CodeBlockColors.Builder.() -> Unit) = block(codeBlock)

        /**
         * Builds a new [MarkyMarkColors] instance with the current configuration.
         *
         * @return A [MarkyMarkColors] object with the set properties for all Markdown elements.
         */
        internal fun build() = MarkyMarkColors(
            headings = headings.build(),
            image = image.build(),
            paragraph = paragraph.build(),
            rule = rule.build(),
            table = table.build(),
            list = list.build(),
            codeBlock = codeBlock.build(),
        )
    }
}
