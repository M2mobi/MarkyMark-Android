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
 * Represents a Markdown image. Mapped from [Image][com.vladsch.flexmark.ast.Image].
 *
 * __Basic Syntax:__
 *
 * ```markdown
 * ![alt text](https://example.com/image.jpg "optional title")
 * ```
 *
 * The syntax consists of:
 * - An exclamation mark `!` to indicate an image
 * - Square brackets `[]` containing the alternative text (displayed if the image cannot be loaded)
 * - Parentheses `()` containing the image URL and an optional title in quotes
 *
 * __Important Notes:__
 *
 * - Inline images (images within paragraph text) are not supported in this implementation
 *
 * For more details, see the [Markdown guide](https://www.markdownguide.org/basic-syntax#images-1).
 *
 * @property metadata Contains information about this image node, including its position in the
 *   document hierarchy and any additional metadata.
 * @property url The URL or path to the image file. Can be an absolute URL, a relative path, or
 *   a data URI.
 * @property altText The alternative text for the image, displayed if the image cannot be loaded
 *   or for accessibility purposes. Can be null if not provided in the Markdown.
 * @property title An optional title for the image, typically displayed as a tooltip when hovering
 *   over the image. Can be null if not provided in the Markdown.
 */
@Immutable
data class Image(
    override val metadata: NodeMetadata,
    val url: String,
    val altText: String?,
    val title: String?,
) : ComposableStableNode()
