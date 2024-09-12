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

package com.moveagency.markymark.theme.paragraph

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker

/**
 * Represents the color scheme for paragraphs.
 *
 * @property text The color of the text in a paragraph.
 */
@Immutable
data class ParagraphColors private constructor(
    val text: Color,
) {

    /**
     * Builder class for constructing instances of [ParagraphColors].
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * The color of the paragraph text. Default is [Black].
         */
        var text = Black

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override the all properties with the one from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            text = builder.text
        }

        /**
         * Includes the configuration from an existing [ParagraphColors] object into this builder.
         *
         * This will override the all properties with the one from the provided [colors].
         *
         * @param colors The `ParagraphColors` instance whose configuration should be included.
         */
        fun include(colors: ParagraphColors) {
            text = colors.text
        }

        /**
         * Builds a new [ParagraphColors] instance with the current configuration.
         *
         * @return A `ParagraphColors` object with the set properties.
         */
        internal fun build() = ParagraphColors(
            text = text,
        )
    }
}
