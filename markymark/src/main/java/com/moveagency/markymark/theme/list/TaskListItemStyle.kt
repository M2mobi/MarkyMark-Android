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

@file:Suppress("DataClassPrivateConstructor", "unused")

package com.moveagency.markymark.theme.list

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import com.moveagency.markymark.model.composable.ListBlock
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding

/**
 * Theming attributes used when rendering [ListBlock.ListItemType.Task].
 *
 * @property padding The padding around the task list item.
 * @property textStyle The text style for the task list item text.
 * @property indicatorPadding The padding around the task list item indicator.
 */
@Immutable
data class TaskListItemStyle private constructor(
    override val padding: Padding,
    override val textStyle: TextStyle,
    override val indicatorPadding: Padding,
) : ListItemStyle {

    /**
     * Builder class for constructing instances of [TaskListItemStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for constructing the padding around the task list item.
         */
        private var padding = Padding.Builder()

        /**
         * The text style for the task list item text.
         */
        var textStyle = TextStyle()

        /**
         * Builder for constructing the padding around the task list item indicator.
         */
        private var indicatorPadding = Padding.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            padding = builder.padding
            textStyle = builder.textStyle
            indicatorPadding = builder.indicatorPadding
        }

        /**
         * Includes the configuration from an existing [TaskListItemStyle] object into this builder.
         *
         * This will include the padding, text style, and indicator padding from the provided [style].
         *
         * @param style The [TaskListItemStyle] instance whose configuration should be included.
         */
        fun include(style: TaskListItemStyle) {
            padding.include(style.padding)
            textStyle = style.textStyle
            indicatorPadding.include(style.indicatorPadding)
        }

        /**
         * Configures the padding around the task list item.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Configures the padding around the task list item indicator.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun indicatorPadding(block: Padding.Builder.() -> Unit) = block(indicatorPadding)

        /**
         * Builds a new [TaskListItemStyle] instance with the current configuration.
         *
         * @return A [TaskListItemStyle] object with the set properties.
         */
        internal fun build() = TaskListItemStyle(
            padding = padding.build(),
            textStyle = textStyle,
            indicatorPadding = indicatorPadding.build(),
        )
    }
}
