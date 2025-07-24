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

/**
 * Represents a Markdown horizontal rule. Mapped from [Rule][com.vladsch.flexmark.ast.ThematicBreak].
 *
 * Horizontal rules (also called thematic breaks) are used to visually separate content sections
 * with a horizontal line. They create a clear division between different parts of a document
 * without requiring a heading.
 *
 * __Basic Syntax:__
 *
 * Horizontal rules can be created using three or more of the following characters on a line by themselves:
 *
 * ```markdown
 * # Using hyphens
 * ---
 *
 * # Using asterisks
 * ***
 *
 * # Using underscores
 * ___
 * ```
 *
 * __Syntax Rules:__
 *
 * - You must use at least three characters (hyphens, asterisks, or underscores)
 * - You can use more than three characters if desired
 * - You can optionally add spaces between the characters
 * - The line must not contain any other characters except spaces
 * - The line must be preceded and followed by a blank line (except at the beginning or end of the document)
 *
 * __Examples with Spaces:__
 *
 * ```markdown
 * # Spaced hyphens
 * - - -
 *
 * # Spaced asterisks
 * * * *
 *
 * # Spaced underscores
 * _ _ _
 * ```
 *
 * __Rendering Behavior:__
 *
 * This library renders horizontal rules with the following characteristics:
 *
 * - Rules are rendered as a Spacer with a height defined by the theme's `rule.thickness` (default: 1dp)
 * - The rule has padding around it defined by the theme's `rule.padding`
 * - The rule's color is set using the theme's `rule.background` color (default: Black)
 * - The rule's shape is defined by the theme's `rule.shape` (default: RectangleShape)
 *
 * __Important Notes:__
 *
 * - To avoid confusion with headings, do not use hyphens with a blank line directly above a text line,
 *   as this might be interpreted as a heading in some Markdown parsers
 * - For better compatibility across Markdown parsers, it's recommended to use blank lines before
 *   and after horizontal rules
 *
 * For more details, see the [Markdown guide](https://www.markdownguide.org/basic-syntax#horizontal-rules).
 *
 * @property metadata Contains information about this rule node, including its position in the
 *   document hierarchy and any additional metadata.
 */
@Immutable
data class Rule(
    override val metadata: NodeMetadata,
) : ComposableStableNode()
