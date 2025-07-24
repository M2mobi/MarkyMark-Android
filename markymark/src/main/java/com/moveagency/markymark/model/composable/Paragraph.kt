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

package com.moveagency.markymark.model.composable

import androidx.compose.runtime.Immutable
import com.moveagency.markymark.model.NodeMetadata
import kotlinx.collections.immutable.ImmutableList

/**
 * Represents a Markdown paragraph. Mapped from [Paragraph][com.vladsch.flexmark.ast.Paragraph].
 *
 * Paragraphs are the most basic element in Markdown, consisting of one or more consecutive
 * lines of text separated by one or more blank lines. They are rendered as blocks of text
 * with appropriate spacing above and below.
 *
 * __Basic Syntax:__
 *
 * ```markdown
 * This is a paragraph. It consists of
 * multiple lines that are treated as
 * a single block of text.
 *
 * This is another paragraph separated by a blank line.
 * ```
 *
 * __Line Breaks:__
 *
 * To create a line break (new line) within a paragraph, end a line with two or more spaces,
 * or use a backslash:
 *
 * ```markdown
 * This line ends with two spaces
 * and continues on a new line.
 *
 * This line ends with a backslash \
 * and continues on a new line.
 * ```
 *
 * __Paragraph Formatting:__
 *
 * Paragraphs can contain inline formatting elements such as:
 * - Emphasis (bold/italic): `**bold**`, `*italic*`
 * - Links: `[link text](URL)`
 * - Inline code: `` `code` ``
 * - Images: `![alt text](image-url)`
 *
 * __Rendering Behavior:__
 *
 * This library renders paragraphs with the following characteristics:
 *
 * - Paragraphs are wrapped in a Box with padding defined by the theme's `paragraph.padding`
 * - Text color is set using the theme's `paragraph.text` color
 * - Typography is controlled by the theme's `paragraph.textStyle`
 * - Line breaks within paragraphs are preserved when using the proper syntax (trailing spaces or backslash)
 * - Inline formatting elements are rendered according to their respective styles
 *
 * __Important Notes:__
 *
 * - Indenting paragraphs with spaces or tabs will not create a code block in this implementation
 *   (unlike standard Markdown where 4+ spaces creates a code block)
 * - Blank lines are required to separate paragraphs; a single line break is not sufficient
 * - HTML tags within paragraphs are not parsed as HTML but treated as literal text
 *
 * For more details, see the [Markdown guide](https://www.markdownguide.org/basic-syntax#paragraphs-1).
 *
 * @property metadata Contains information about this paragraph node, including its position in the
 *   document hierarchy and any additional metadata. The [NodeMetadata.paragraphLevel] property
 *   tracks the nesting level of this paragraph within the document.
 * @property children The list of child nodes contained within this paragraph. These can be
 *   various inline elements like text, emphasis, links, etc., that make up the content of
 *   the paragraph.
 */
@Immutable
data class Paragraph(
    override val metadata: NodeMetadata,
    val children: ImmutableList<ComposableStableNode>,
) : ComposableStableNode()
