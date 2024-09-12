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

@file:Suppress("DataClassPrivateConstructor", "MemberVisibilityCanBePrivate", "unused")

package com.moveagency.markymark.theme.list

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Represents the color scheme for task list elements.
 *
 * @property text The text color for the task list items.
 * @property checked The color of the checkbox when it is checked.
 * @property unchecked The color of the checkbox when it is unchecked.
 * @property checkmark The color of the checkmark inside the checkbox when it is checked.
 */
@Immutable
data class TaskListColors private constructor(
    override val text: Color,
    val checked: Color,
    val unchecked: Color,
    val checkmark: Color,
) : ListItemColors {

    /**
     * Builder class for constructing instances of [TaskListColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The text color for the task list items. Default is [Black].
         */
        var text = Black

        /**
         * The color of the checkbox when it is checked. Default is [Black].
         */
        var checked = Black

        /**
         * The color of the checkbox when it is unchecked. Default is [Black].
         */
        var unchecked = Black

        /**
         * The color of the checkmark inside the checkbox when it is checked. Default is [White].
         */
        var checkmark = White

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            text = builder.text
            checked = builder.checked
            unchecked = builder.unchecked
            checkmark = builder.checkmark
        }

        /**
         * Includes the configuration from an existing [TaskListColors] object into this builder.
         *
         * This will override all properties with the ones from the provided [colors].
         *
         * @param colors The `TaskListColors` instance whose configuration should be included.
         */
        fun include(colors: TaskListColors) {
            text = colors.text
            checked = colors.checked
            unchecked = colors.unchecked
            checkmark = colors.checkmark
        }

        /**
         * Builds a new [TaskListColors] instance with the current configuration.
         *
         * @return A `TaskListColors` object with the set properties.
         */
        internal fun build() = TaskListColors(
            text = text,
            checked = checked,
            unchecked = unchecked,
            checkmark = checkmark,
        )
    }
}
