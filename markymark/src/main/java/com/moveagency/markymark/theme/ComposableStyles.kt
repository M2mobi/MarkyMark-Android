/*
 * Copyright © 2025 Framna
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

@file:Suppress("unused")

package com.moveagency.markymark.theme

import androidx.compose.runtime.Immutable
import com.moveagency.markymark.theme.code.CodeBlockStyle
import com.moveagency.markymark.theme.heading.HeadingsStyle
import com.moveagency.markymark.theme.image.ImageStyle
import com.moveagency.markymark.theme.list.ListBlockStyle
import com.moveagency.markymark.theme.paragraph.ParagraphStyle
import com.moveagency.markymark.theme.quote.BlockQuoteStyle
import com.moveagency.markymark.theme.rule.RuleStyle
import com.moveagency.markymark.theme.table.TableBlockStyle

/**
 * Collection of style configurations for all composable markdown elements.
 *
 * This class aggregates all the individual style components needed to render
 * a complete markdown document with consistent styling across different element types.
 *
 * @property headings Styling configuration for heading elements (h1-h6).
 * @property image Styling configuration for image elements and their captions.
 * @property paragraph Styling configuration for paragraph text blocks.
 * @property rule Styling configuration for horizontal rule elements.
 * @property codeBlock Styling configuration for code block elements.
 * @property blockQuote Styling configuration for block quote elements.
 * @property tableBlock Styling configuration for table elements.
 * @property listBlock Styling configuration for ordered and unordered list elements.
 */
@Immutable
data class ComposableStyles(
    val headings: HeadingsStyle,
    val image: ImageStyle,
    val paragraph: ParagraphStyle,
    val rule: RuleStyle,
    val codeBlock: CodeBlockStyle,
    val blockQuote: BlockQuoteStyle,
    val tableBlock: TableBlockStyle,
    val listBlock: ListBlockStyle,
) {

    /**
     * Builder class for constructing instances of [ComposableStyles].
     *
     * This builder provides methods to configure all aspects of markdown styling
     * in a type-safe, fluent manner. It allows for incremental style configuration
     * and composition of styles from multiple sources.
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring heading styles (h1-h6).
         */
        private var headings = HeadingsStyle.Builder()

        /**
         * Builder for configuring image element styles.
         */
        private var image = ImageStyle.Builder()

        /**
         * Builder for configuring paragraph text styles.
         */
        private var paragraph = ParagraphStyle.Builder()

        /**
         * Builder for configuring horizontal rule styles.
         */
        private var rule = RuleStyle.Builder()

        /**
         * Builder for configuring code block styles.
         */
        private var codeBlock = CodeBlockStyle.Builder()

        /**
         * Builder for configuring block quote styles.
         */
        private var blockQuote = BlockQuoteStyle.Builder()

        /**
         * Builder for configuring table styles.
         */
        private var tableBlock = TableBlockStyle.Builder()

        /**
         * Builder for configuring list styles.
         */
        private var listBlock = ListBlockStyle.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all style builders with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            headings = builder.headings
            image = builder.image
            paragraph = builder.paragraph
            rule = builder.rule
            codeBlock = builder.codeBlock
            blockQuote = builder.blockQuote
            tableBlock = builder.tableBlock
            listBlock = builder.listBlock
        }

        /**
         * Includes the configuration from an existing [ComposableStyles] object into this builder.
         *
         * This will include all style configurations from the provided [styles] into their
         * respective builders.
         *
         * @param styles The [ComposableStyles] instance whose configuration should be included.
         */
        fun include(styles: ComposableStyles) {
            headings.include(styles.headings)
            image.include(styles.image)
            paragraph.include(styles.paragraph)
            rule.include(styles.rule)
            codeBlock.include(styles.codeBlock)
            blockQuote.include(styles.blockQuote)
            tableBlock.include(styles.tableBlock)
            listBlock.include(styles.listBlock)
        }

        /**
         * Configures heading styles (h1-h6).
         *
         * @param block A lambda to configure the [Builder].
         */
        fun headings(block: HeadingsStyle.Builder.() -> Unit) = block(headings)

        /**
         * Configures image element styles.
         *
         * @param block A lambda to configure the [Builder].
         */
        fun image(block: ImageStyle.Builder.() -> Unit) = block(image)

        /**
         * Configures paragraph text styles.
         *
         * @param block A lambda to configure the [ParagraphStyleBuilder].
         */
        fun paragraph(block: ParagraphStyle.Builder.() -> Unit) = block(paragraph)

        /**
         * Configures horizontal rule styles.
         *
         * @param block A lambda to configure the [RuleStyle.Builder].
         */
        fun rule(block: RuleStyle.Builder.() -> Unit) = block(rule)

        /**
         * Configures code block styles.
         *
         * @param block A lambda to configure the [CodeBlockStyle.Builder].
         */
        fun codeBlock(block: CodeBlockStyle.Builder.() -> Unit) = block(codeBlock)

        /**
         * Configures block quote styles.
         *
         * @param block A lambda to configure the [BlockQuoteStyle.Builder].
         */
        fun blockQuote(block: BlockQuoteStyle.Builder.() -> Unit) = block(blockQuote)

        /**
         * Configures table styles.
         *
         * @param block A lambda to configure the [TableBlockStyle.Builder].
         */
        fun tableBlock(block: TableBlockStyle.Builder.() -> Unit) = block(tableBlock)

        /**
         * Configures list styles.
         *
         * @param block A lambda to configure the [ListBlockStyle.Builder].
         */
        fun listBlock(block: ListBlockStyle.Builder.() -> Unit) = block(listBlock)

        /**
         * Builds a new [ComposableStyles] instance with the current configuration.
         *
         * @return A [ComposableStyles] object with all the configured style components.
         */
        internal fun build() = ComposableStyles(
            headings = headings.build(),
            image = image.build(),
            paragraph = paragraph.build(),
            rule = rule.build(),
            codeBlock = codeBlock.build(),
            blockQuote = blockQuote.build(),
            tableBlock = tableBlock.build(),
            listBlock = listBlock.build(),
        )
    }
}
