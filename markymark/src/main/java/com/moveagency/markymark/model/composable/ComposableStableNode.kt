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

import androidx.compose.runtime.Stable
import com.moveagency.markymark.model.NodeMetadata

/**
 * Base class for all Markdown data models that are rendered as Jetpack Compose UI elements.
 *
 * This sealed class serves as the foundation for the entire Markdown rendering hierarchy and
 * doesn't represent any specific Markdown syntax itself. Instead, it defines the common structure
 * and behavior that all Markdown elements share when they're converted to Composable UI elements.
 *
 * __Purpose and Design:__
 *
 * The ComposableStableNode class is designed with several key principles:
 *
 * - __Stability:__ Marked with the [Stable] annotation to ensure compatibility with Jetpack Compose's
 *   recomposition system. This allows Compose to efficiently track changes to these objects and
 *   minimize unnecessary recompositions.
 *
 * - __Immutability:__ All implementations are immutable data classes, ensuring thread safety and
 *   predictable behavior in the Compose environment.
 *
 * - __Hierarchy:__ Forms the root of a class hierarchy that represents the various Markdown elements
 *   (paragraphs, headings, lists, code blocks, etc.) in a structured, type-safe manner.
 *
 * - __Metadata Tracking:__ Every node carries [metadata] that tracks its position in the document
 *   hierarchy, enabling context-aware rendering decisions.
 *
 * __Usage in the Rendering Pipeline:__
 *
 * The typical flow for Markdown rendering with this class hierarchy is:
 *
 * 1. Parse raw Markdown text into an AST (Abstract Syntax Tree) using a parser like Flexmark
 * 2. Convert the parser's AST nodes into ComposableStableNode subclasses
 * 3. Pass these nodes to corresponding Composable functions that render them as UI elements
 *
 * This separation of concerns allows for:
 * - Clean distinction between data model and UI rendering logic
 * - Type-safe handling of different Markdown elements
 * - Consistent styling and theming across all Markdown elements
 * - Efficient recomposition in the Compose UI system
 *
 * __Extending the Hierarchy:__
 *
 * When adding support for new Markdown syntax elements:
 * 1. Create a new data class that extends ComposableStableNode
 * 2. Implement the corresponding Composable function to render it
 * 3. Add conversion logic in the appropriate converter class
 *
 * @property metadata Contains information about this node's position in the document hierarchy,
 *   including nesting levels for different types of containers (quotes, lists, paragraphs).
 *   This metadata is used by rendering components to apply appropriate styling based on
 *   the node's context within the document.
 *
 * @see NodeMetadata For details on the metadata structure and how it tracks nesting levels
 */
@Stable
@Suppress("MaxLineLength")
sealed class ComposableStableNode {

    abstract val metadata: NodeMetadata
}
