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

package com.moveagency.markymark.model

/**
 * Tracks the nesting level of markdown elements during the conversion and rendering processes.
 *
 * This class maintains state about the current nesting depth of different types of elements
 * (quotes, lists, paragraphs) while traversing the markdown structure. It helps the converter
 * keep track of how deeply nested the current element is within each type of container.
 *
 * The nesting information is used by composable components to apply appropriate styling:
 * - Block quotes use [quoteLevel] to cycle through available themes for nested quotes
 * - Lists use [listLevel] to determine indentation and styling for nested lists
 * - Paragraphs use [paragraphLevel] to adjust spacing and formatting based on nesting
 *
 * Each level counter is incremented independently when entering the corresponding element type,
 * allowing for precise tracking of nesting within specific element types, while the overall
 * [level] tracks the total nesting depth regardless of element type.
 *
 * @property level The overall nesting level, incremented for any type of nesting.
 * @property quoteLevel The nesting level specifically for block quotes. Used to determine styling
 *   and theming for nested quotes (e.g., different colors or indentation for each level).
 * @property listLevel The nesting level specifically for lists. Used to determine indentation
 *   and styling for nested lists.
 * @property paragraphLevel The nesting level specifically for paragraphs. Used to adjust spacing
 *   and formatting for paragraphs at different nesting levels.
 */
data class NodeMetadata(
    val level: Int = 0,
    val quoteLevel: Int = 0,
    val listLevel: Int = 0,
    val paragraphLevel: Int = 0,
) {

    /**
     * Indicates whether this metadata represents the root level of the document.
     *
     * This property is used by composable components to determine whether to apply
     * root-level styling (such as outer padding or margins) or nested styling.
     *
     * @return `true` if this is root level metadata (level = 0), `false` otherwise.
     */
    val isRootLevel: Boolean
        get() = level == 0

    /**
     * Creates a new [NodeMetadata] with incremented quote level.
     *
     * This method is used when entering a block quote element to track the nesting depth.
     * In the composable components, the [quoteLevel] is used to determine styling for nested quotes,
     * such as cycling through different themes or colors based on the nesting level.
     *
     * For example, in [MarkyMarkQuote], the [quoteLevel] is used to select a theme from a list:
     * ```
     * themeList[node.metadata.quoteLevel % themeList.size]
     * ```
     *
     * @return A new [NodeMetadata] instance with both [level] and [quoteLevel] incremented by 1.
     */
    fun incQuoteLevel() = copy(
        level = level + 1,
        quoteLevel = quoteLevel + 1,
    )

    /**
     * Creates a new [NodeMetadata] with incremented list level.
     *
     * This method is used when entering a list element to track the nesting depth.
     * In the composable components, the [listLevel] is used to determine styling for nested lists,
     * such as indentation or bullet/number styles based on the nesting level.
     *
     * For example, in [MarkyMarkList], the [listLevel] is used to determine padding and indentation:
     * ```
     * .then(if (node.metadata.listLevel == 0) Modifier.padding(style.padding) else Modifier)
     * .then(if (node.metadata.listLevel == 0) Modifier else Modifier.padding(start = style.levelIndent))
     * ```
     *
     * @return A new [NodeMetadata] instance with both [level] and [listLevel] incremented by 1.
     */
    fun incListLevel() = copy(
        level = level + 1,
        listLevel = listLevel + 1,
    )

    /**
     * Creates a new [NodeMetadata] with incremented paragraph level.
     *
     * This method is used when entering a paragraph element to track the nesting depth.
     * In the composable components, the [paragraphLevel] can be used to adjust spacing,
     * indentation, or other styling aspects of paragraphs based on their nesting level.
     *
     * @return A new [NodeMetadata] instance with both [level] and [paragraphLevel] incremented by 1.
     */
    fun incParagraphLevel() = copy(
        level = level + 1,
        paragraphLevel = paragraphLevel + 1,
    )

    /**
     * Creates a new [NodeMetadata] with incremented overall level.
     *
     * This method is used when entering any other type of element that doesn't have a specific
     * level counter but still contributes to the overall nesting depth. This ensures that
     * the general nesting level is tracked even for element types that don't have their own
     * specific counter.
     *
     * The overall [level] can be used by composable components to make styling decisions
     * based on the general nesting depth, regardless of the specific element types involved.
     *
     * @return A new [NodeMetadata] instance with [level] incremented by 1.
     */
    fun incLevel() = copy(level = level + 1)

    companion object {

        /**
         * Predefined metadata instance representing the root level of the document.
         *
         * This constant provides a convenient way to initialize the conversion process
         * with a fresh metadata object at level 0. It is widely used throughout the composable
         * components as a starting point for rendering markdown elements at the root level.
         *
         * In preview functions and tests, this constant is often used to create sample markdown
         * elements with root-level metadata.
         */
        val Root by lazy { NodeMetadata() }
    }
}
