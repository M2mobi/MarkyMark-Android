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
import com.moveagency.markymark.model.annotated.AnnotatedStableNode

/**
 * Represents a standalone text element in Markdown that is not part of a larger structure.
 *
 * TextNode serves as a wrapper for "free-floating" text content that doesn't belong to a specific
 * block element like a paragraph, heading, or list item. It provides a way to include standalone
 * text elements in the document hierarchy while maintaining the composable structure.
 *
 * __Purpose and Usage:__
 *
 * In the Markdown rendering pipeline, TextNode is used to:
 *
 * - Wrap standalone text fragments that need to be rendered independently
 * - Provide a bridge between the annotated text model ([AnnotatedStableNode]) and the composable
 *   hierarchy ([ComposableStableNode])
 * - Enable consistent rendering of text content regardless of its context
 *
 * __Rendering Behavior:__
 *
 * TextNode instances are rendered using the [MarkyMarkText][com.moveagency.markymark.composable.MarkyMarkText]
 * composable, which:
 *
 * - Wraps the text in a [SelectionContainer][androidx.compose.foundation.text.selection.SelectionContainer]
 *   to enable text selection
 * - Applies the paragraph text style from the current theme
 * - Preserves any inline formatting (bold, italic, links, etc.) contained in the wrapped text
 *
 * __Creation Process:__
 *
 * TextNode instances are typically created during the Markdown parsing process when:
 *
 * - Standalone text is encountered that isn't part of a block element
 * - Text needs to be extracted from a complex node structure
 * - Multiple annotated nodes need to be bundled together
 *
 * __Important Notes:__
 *
 * - TextNode doesn't represent any specific Markdown syntax; it's an implementation detail
 *   of the rendering system
 * - The styling of TextNode content follows the paragraph text style from the current theme
 * - TextNode can wrap any type of [AnnotatedStableNode], including plain [Text][com.moveagency.markymark.model.annotated.Text]
 *   or formatted [ParagraphText][com.moveagency.markymark.model.annotated.ParagraphText]
 *
 * @property metadata Contains information about this text node's position in the document
 *   hierarchy and any additional metadata.
 * @property text The annotated text content wrapped by this node. This can be plain text or
 *   text with formatting (bold, italic, links, etc.).
 */
@Immutable
data class TextNode(
    override val metadata: NodeMetadata,
    val text: AnnotatedStableNode,
) : ComposableStableNode()
