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
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Represents the color scheme for different types of list elements: ordered, unordered, and task lists.
 *
 * @property ordered The color scheme for ordered lists.
 * @property unordered The color scheme for unordered lists.
 * @property task The color scheme for task lists.
 */
@Immutable
data class ListColors private constructor(
    val ordered: OrderedListColors,
    val unordered: UnorderedListColors,
    val task: TaskListColors,
) {

    /**
     * Builder class for constructing instances of [ListColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        private var ordered = OrderedListColors.Builder()
        private var unordered = UnorderedListColors.Builder()
        private var task = TaskListColors.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            ordered = builder.ordered
            unordered = builder.unordered
            task = builder.task
        }

        /**
         * Includes the configuration from an existing [ListColors] object into this builder.
         *
         * This will include the color schemes from the provided [colors].
         *
         * @param colors The `ListColors` instance whose configuration should be included.
         */
        fun include(colors: ListColors) {
            ordered.include(colors.ordered)
            unordered.include(colors.unordered)
            task.include(colors.task)
        }

        /**
         * Configures the color scheme for ordered lists.
         *
         * @param block A lambda to configure the [OrderedListColors.Builder].
         */
        fun ordered(block: OrderedListColors.Builder.() -> Unit) = block(ordered)

        /**
         * Configures the color scheme for unordered lists.
         *
         * @param block A lambda to configure the [UnorderedListColors.Builder].
         */
        fun unordered(block: UnorderedListColors.Builder.() -> Unit) = block(unordered)

        /**
         * Configures the color scheme for task lists.
         *
         * @param block A lambda to configure the [TaskListColors.Builder].
         */
        fun task(block: TaskListColors.Builder.() -> Unit) = block(task)

        /**
         * Builds a new [ListColors] instance with the current configuration.
         *
         * @return A `ListColors` object with the set properties.
         */
        internal fun build() = ListColors(
            ordered = ordered.build(),
            unordered = unordered.build(),
            task = task.build(),
        )
    }
}
