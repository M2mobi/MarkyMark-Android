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
 * Represents a Markdown block quote. Mapped from [BlockQuote][com.vladsch.flexmark.ast.BlockQuote].
 *
 * Block quotes are used to emphasize quoted text, excerpts, or citations. They are rendered with
 * a vertical bar or background color to visually distinguish them from regular text.
 *
 * __Basic Syntax:__
 *
 * ```markdown
 * > This is a block quote
 * ```
 *
 * __Multi-line Syntax:__
 *
 * ```markdown
 * > This is a block quote
 * > spanning multiple lines
 * > with the > character at the start of each line
 * ```
 *
 * __Lazy Syntax (without > on continuation lines):__
 *
 * ```markdown
 * > This is a block quote
 *   that continues on the next line
 *   without needing the > character
 * ```
 *
 * __With Blank Lines:__
 *
 * ```markdown
 * > First paragraph
 * >
 * > Second paragraph
 * ```
 *
 * __Nested Block Quotes:__
 *
 * ```markdown
 * > Outer quote
 * >
 * > > Nested quote
 * > >
 * > > > Deeply nested quote
 * ```
 *
 * Block quotes can contain other Markdown elements including:
 * - Formatted text (bold, italic, etc.)
 * - Lists
 * - Code blocks
 * - Images
 * - Tables
 * - Headers
 * - Other block quotes (nested)
 *
 * The [metadata] property tracks nesting level through [NodeMetadata.quoteLevel], which is used
 * by [MarkyMarkQuote][com.moveagency.markymark.composable.MarkyMarkQuote] to apply different
 * styling for nested quotes.
 *
 * For more details, see the [Markdown guide](https://www.markdownguide.org/basic-syntax#blockquotes-1).
 *
 * @property metadata Contains information about the nesting level of this block quote, including
 *   the overall nesting level and the specific quote level. This is used for styling nested quotes
 *   with different visual treatments.
 * @property children The list of child nodes contained within this block quote. These can be any
 *   type of Markdown elements, as block quotes can contain various nested content.
 */
@Immutable
data class BlockQuote(
    override val metadata: NodeMetadata,
    val children: ImmutableList<ComposableStableNode>,
) : ComposableStableNode()
