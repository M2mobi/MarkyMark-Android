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
import androidx.compose.runtime.Stable
import com.moveagency.markymark.model.NodeMetadata
import com.moveagency.markymark.model.annotated.AnnotatedStableNode
import com.moveagency.markymark.model.composable.ListBlock.ListEntry.ListItem
import com.moveagency.markymark.model.composable.ListBlock.ListEntry.ListNode
import kotlinx.collections.immutable.ImmutableList

/**
 * Represents a Markdown list. Mapped from [ListBlock][com.vladsch.flexmark.ast.ListBlock].
 *
 * Lists are used to organize content in a structured way, presenting items in a sequential or
 * hierarchical format. Markdown supports three types of lists: ordered (numbered), unordered
 * (bulleted), and task lists (checkboxes).
 *
 * __Basic Syntax:__
 *
 * Lists in Markdown are created by prefixing each item with a specific marker, followed by a space:
 *
 * - Ordered lists use numbers followed by periods (`1.`, `2.`, etc.)
 * - Unordered lists use hyphens (`-`), asterisks (`*`), or plus signs (`+`)
 * - Task lists use unordered list markers followed by square brackets (`- [ ]` or `- [x]`)
 *
 * __Nesting Lists:__
 *
 * Lists can be nested by indenting items with spaces or tabs:
 *
 * ```markdown
 * - First level item
 *   - Second level item
 *     - Third level item
 * ```
 *
 * __Adding Other Elements in Lists:__
 *
 * Lists can contain other Markdown elements like paragraphs, code blocks, or images by
 * indenting them to align with the list item text:
 *
 * ```markdown
 * - List item with multiple paragraphs
 *
 *   Second paragraph indented to align with the text above.
 *
 * - Next list item
 * ```
 *
 * __Rendering Behavior:__
 *
 * This library renders lists with the following characteristics:
 *
 * - Root-level lists have outer padding defined by the theme's `listBlock.padding`
 * - Nested lists are indented using the theme's `listBlock.levelIndent`
 * - Unordered list bullets change style based on nesting level (typically cycling through
 *   disc, circle, and square shapes)
 * - Task list items render with interactive checkboxes that reflect the completed state
 * - Ordered list items display the correct sequence number
 *
 * For more details on list syntax, see the [Markdown guide](https://www.markdownguide.org/basic-syntax#lists-1).
 *
 * @property metadata Contains information about this list node, including its position in the
 *   document hierarchy and nesting level. The [NodeMetadata.listLevel] property is used to
 *   determine indentation and styling for nested lists.
 * @property children The list of child entries contained within this list block. These can be
 *   either [ListItem] instances representing actual list items, or [ListNode] instances
 *   representing other Markdown elements within the list.
 */
@Immutable
data class ListBlock(
    override val metadata: NodeMetadata,
    val children: ImmutableList<ListEntry>,
) : ComposableStableNode() {

    /**
     * Represents a Markdown list entry.
     *
     * This sealed class hierarchy represents the two types of elements that can appear within a list:
     * regular list items ([ListItem]) and other block elements ([ListNode]) that are part of the list
     * but not list items themselves.
     *
     * __List Items:__
     *
     * Regular list items are the primary components of a list, each prefixed with a marker
     * (number, bullet, or checkbox). They can contain text, formatting, and other inline elements.
     *
     * __Other Elements in Lists:__
     *
     * Lists can also contain other block elements like paragraphs, code blocks, or even nested lists.
     * These elements must be properly indented to be considered part of the list.
     *
     * __Rendering Behavior:__
     *
     * - [ListItem] elements are rendered with appropriate markers (numbers, bullets, or checkboxes)
     * - [ListNode] elements are rendered as their respective Markdown elements, but with proper
     *   indentation to align with the list structure
     *
     * For more details on list syntax and structure, see the
     * [Markdown guide](https://www.markdownguide.org/basic-syntax#lists-1).
     */
    @Stable
    sealed class ListEntry {

        /**
         * Represents a Markdown list item. Mapped from [ListItem][com.vladsch.flexmark.ast.ListItem].
         *
         * List items are the primary components of a list, each prefixed with a marker that indicates
         * the type of list (ordered, unordered, or task). The content of a list item can include
         * text, formatting, links, and other inline elements.
         *
         * __Syntax Examples:__
         *
         * ```markdown
         * # Ordered list item
         * 1. First item
         *
         * # Unordered list item
         * - Bullet item
         *
         * # Task list item
         * - [ ] Uncompleted task
         * - [x] Completed task
         * ```
         *
         * __Rendering Behavior:__
         *
         * List items are rendered with:
         * - An appropriate marker based on the [type] (number, bullet, or checkbox)
         * - The marker style may change based on nesting level (especially for unordered lists)
         * - The content is rendered as annotated text with all formatting preserved
         *
         * @property type The type of list item (ordered, unordered, or task), which determines
         *   how the item's marker is rendered.
         * @property children The list of annotated nodes that make up the content of this list item.
         *   These typically include text with various formatting (bold, italic, links, etc.).
         *
         * For more details on list item syntax, see the [Markdown guide](https://www.markdownguide.org/basic-syntax#lists-1).
         */
        @Immutable
        data class ListItem(
            val type: ListItemType,
            val children: ImmutableList<AnnotatedStableNode>,
        ) : ListEntry()

        /**
         * Represents a non-list-item Markdown element within a list.
         *
         * Lists in Markdown can contain other block elements like paragraphs, code blocks, blockquotes,
         * or even nested lists. These elements are not list items themselves (they don't have markers),
         * but they are part of the list structure when properly indented.
         *
         * __Syntax Example:__
         *
         * ```markdown
         * - First list item
         *
         *   This is a paragraph within the first list item.
         *   It must be indented to align with the text above.
         *
         *   ```
         *   A code block within the list item.
         *   Also properly indented.
         *   ```
         *
         * - Second list item
         * ```
         *
         * __Rendering Behavior:__
         *
         * Non-list-item elements within lists are:
         * - Rendered as their respective Markdown element types
         * - Properly indented to align with the list structure
         * - Visually grouped with their parent list item
         *
         * @property node The Markdown node that is contained within the list. This can be any
         *   [ComposableStableNode] type, such as paragraphs, code blocks, or even nested lists.
         *
         * For more details on adding elements to lists, see the
         * [Markdown guide](https://www.markdownguide.org/basic-syntax#adding-elements-in-lists).
         */
        @Immutable
        data class ListNode(val node: ComposableStableNode) : ListEntry()
    }

    /**
     * Represents a type of Markdown list item. Mapped from [ListItem][com.vladsch.flexmark.ast.ListItem].
     *
     * This sealed class hierarchy defines the three types of list items supported in Markdown:
     * ordered (numbered), unordered (bulleted), and task (checkbox) list items. Each type has
     * its own syntax, visual representation, and specific properties.
     *
     * __List Item Types:__
     *
     * - [Ordered]: Numbered list items that follow a sequence (1, 2, 3, etc.)
     * - [Unordered]: Bulleted list items without a specific order
     * - [Task]: Checkbox list items that can be marked as completed or uncompleted
     *
     * __Rendering Behavior:__
     *
     * The library renders each list item type differently:
     * - Ordered items display their sequence number
     * - Unordered items display bullets that change style based on nesting level
     * - Task items display interactive checkboxes that reflect the completed state
     *
     * For specific syntax details, see the documentation for each subclass.
     */
    @Stable
    sealed class ListItemType {

        /**
         * Represents an ordered Markdown list item. Mapped from
         * [OrderedListItem][com.vladsch.flexmark.ast.OrderedListItem].
         *
         * __Syntax:__
         *
         * ```markdown
         * 1. some list item
         *   1. some other item
         * 1. some list item again
         * ```
         *
         * For details see the [Markdown guide](https://www.markdownguide.org/basic-syntax#ordered-lists).
         */
        @Immutable
        data class Ordered(val index: Int) : ListItemType()

        /**
         * Represents an unordered Markdown list item. Mapped from
         * [UnorderedListItem][com.vladsch.flexmark.ast.BulletListItem].
         *
         * __Syntax:__
         *
         * ```markdown
         * - some list item
         *   * other list item
         *     + more list item
         *   * other list item again
         * - some list item again
         * ```
         *
         * For details see the [Markdown guide](https://www.markdownguide.org/basic-syntax#unordered-lists).
         */
        @Immutable
        object Unordered : ListItemType()

        /**
         * Represents a Github Flavoured Markdown task list item. Mapped from
         * [TaskListItem][com.vladsch.flexmark.ext.gfm.tasklist.TaskListItem].
         *
         * __Syntax:__
         *
         * ```markdown
         * - [ ] Unfinished item
         * - [x] Finished item
         * - [X] Other finished item
         * ```
         *
         * For details see the [Github Flavoured Markdown guide](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#task-lists).
         */
        @Immutable
        data class Task(val completed: Boolean) : ListItemType()
    }
}
