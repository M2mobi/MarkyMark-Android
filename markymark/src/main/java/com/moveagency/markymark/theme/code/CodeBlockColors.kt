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

@file:Suppress("DataClassPrivateConstructor")

package com.moveagency.markymark.theme.code

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Represents the color scheme for code block elements.
 *
 * @property background The background color for the code block.
 * @property text The text color for the code block.
 */
@Immutable
data class CodeBlockColors private constructor(
    val background: Color,
    val text: Color,
) {

    /**
     * Builder class for constructing instances of [CodeBlockColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The background color for the code block. Default is [Transparent].
         */
        var background = Transparent

        /**
         * The text color for the code block. Default is [Black].
         */
        var text = Black

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all properties with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            background = builder.background
            text = builder.text
        }

        /**
         * Includes the configuration from an existing [CodeBlockColors] object into this builder.
         *
         * This will override all properties with the ones from the provided [colors].
         *
         * @param colors The `CodeBlockColors` instance whose configuration should be included.
         */
        fun include(colors: CodeBlockColors) {
            background = colors.background
            text = colors.text
        }

        /**
         * Builds a new [CodeBlockColors] instance with the current configuration.
         *
         * @return A `CodeBlockColors` object with the set properties.
         */
        internal fun build() = CodeBlockColors(
            background = background,
            text = text,
        )
    }
}
