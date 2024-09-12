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

@file:Suppress("DataClassPrivateConstructor", "MemberVisibilityCanBePrivate")

package com.moveagency.markymark.theme.list

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moveagency.markymark.composable.MarkyMarkList
import com.moveagency.markymark.model.composable.ListBlock
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding

/**
 * Theming attributes used when rendering [ListBlock]s with [MarkyMarkList].
 *
 * @property padding The padding around the entire list block.
 * @property levelIndent The amount of indentation to apply at each nesting level within the list.
 * @property ordered The style attributes for ordered list items.
 * @property unordered The style attributes for unordered list items.
 * @property task The style attributes for task list items.
 */
@Immutable
data class ListBlockStyle private constructor(
    val padding: Padding,
    val levelIndent: Dp,
    val ordered: OrderedListItemStyle,
    val unordered: UnorderedListItemStyle,
    val task: TaskListItemStyle,
) {

    /**
     * Builder class for constructing instances of [ListBlockStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the padding around the entire list block.
         */
        private var padding = Padding.Builder()

        /**
         * The amount of indentation to apply at each nesting level within the list. Default is `0.dp`.
         */
        var levelIndent = 0.dp

        /**
         * Builder for configuring the style attributes for ordered list items.
         */
        private var ordered = OrderedListItemStyle.Builder()

        /**
         * Builder for configuring the style attributes for unordered list items.
         */
        private var unordered = UnorderedListItemStyle.Builder()

        /**
         * Builder for configuring the style attributes for task list items.
         */
        private var task = TaskListItemStyle.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            padding = builder.padding
            levelIndent = builder.levelIndent
            ordered = builder.ordered
            unordered = builder.unordered
            task = builder.task
        }

        /**
         * Includes the configuration from an existing [ListBlockStyle] object into this builder.
         *
         * This will include the padding, level indent, and styles for ordered, unordered, and task list items
         * from the provided [style].
         *
         * @param style The [ListBlockStyle] instance whose configuration should be included.
         */
        fun include(style: ListBlockStyle) {
            padding.include(style.padding)
            levelIndent = style.levelIndent
            ordered.include(style.ordered)
            unordered.include(style.unordered)
            task.include(style.task)
        }

        /**
         * Configures the padding around the entire list block.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Configures the style attributes for ordered list items.
         *
         * @param block A lambda to configure the [OrderedListItemStyle.Builder].
         */
        fun ordered(block: OrderedListItemStyle.Builder.() -> Unit) = block(ordered)

        /**
         * Configures the style attributes for unordered list items.
         *
         * @param block A lambda to configure the [UnorderedListItemStyle.Builder].
         */
        fun unordered(block: UnorderedListItemStyle.Builder.() -> Unit) = block(unordered)

        /**
         * Configures the style attributes for task list items.
         *
         * @param block A lambda to configure the [TaskListItemStyle.Builder].
         */
        fun task(block: TaskListItemStyle.Builder.() -> Unit) = block(task)

        /**
         * Builds a new [ListBlockStyle] instance with the current configuration.
         *
         * @return A [ListBlockStyle] object with the set properties.
         */
        internal fun build() = ListBlockStyle(
            padding = padding.build(),
            levelIndent = levelIndent,
            ordered = ordered.build(),
            unordered = unordered.build(),
            task = task.build(),
        )
    }
}
