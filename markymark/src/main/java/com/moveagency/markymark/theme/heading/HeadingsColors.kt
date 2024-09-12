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

package com.moveagency.markymark.theme.heading

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Represents the color scheme for heading elements.
 *
 * Every level of heading can have its own text color.
 *
 * @property h1 The text color for H1.
 * @property h2 The text color for H2.
 * @property h3 The text color for H3.
 * @property h4 The text color for H4.
 * @property h5 The text color for H5.
 * @property h6 The text color for H6.
 */
@Immutable
data class HeadingsColors private constructor(
    val h1: Color,
    val h2: Color,
    val h3: Color,
    val h4: Color,
    val h5: Color,
    val h6: Color,
) {

    /**
     * Builder class for constructing instances of [HeadingsColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The text color for H1, when set will override [all].
         */
        var h1: Color? = null

        /**
         * The text color for H2, when set will override [all].
         */
        var h2: Color? = null

        /**
         * The text color for H3, when set will override [all].
         */
        var h3: Color? = null

        /**
         * The text color for H4, when set will override [all].
         */
        var h4: Color? = null

        /**
         * The text color for H5, when set will override [all].
         */
        var h5: Color? = null

        /**
         * The text color for H6, when set will override [all].
         */
        var h6: Color? = null

        /**
         * Default color to be applied to all headings if not individually specified.
         * Default is [Black].
         */
        var all = Black

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override all heading colors with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            h1 = builder.h1
            h2 = builder.h2
            h3 = builder.h3
            h4 = builder.h4
            h5 = builder.h5
            h6 = builder.h6
        }

        /**
         * Includes the configuration from an existing [HeadingsColors] object into this builder.
         *
         * This will override all heading colors with the ones from the provided [colors].
         *
         * @param colors The `HeadingsColors` instance whose configuration should be included.
         */
        fun include(colors: HeadingsColors) {
            h1 = colors.h1
            h2 = colors.h2
            h3 = colors.h3
            h4 = colors.h4
            h5 = colors.h5
            h6 = colors.h6
        }

        /**
         * Builds a new [HeadingsColors] instance with the current configuration.
         *
         * @return A `HeadingsColors` object with the set properties.
         */
        internal fun build() = HeadingsColors(
            h1 = h1 ?: all,
            h2 = h2 ?: all,
            h3 = h3 ?: all,
            h4 = h4 ?: all,
            h5 = h5 ?: all,
            h6 = h6 ?: all,
        )
    }
}
