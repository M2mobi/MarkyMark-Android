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

package com.moveagency.markymark.theme.heading

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding

/**
 * Styling configuration for an individual heading level in markdown.
 *
 * This class defines the visual appearance of a specific heading level (h1-h6)
 * including its spacing and typography.
 *
 * @property padding The spacing around the heading element.
 * @property textStyle The typography style applied to the heading text.
 */
@Immutable
data class HeadingLevelStyle(
    val padding: Padding,
    val textStyle: TextStyle,
) {

    /**
     * Builder class for constructing instances of [HeadingLevelStyle].
     *
     * This builder provides methods to configure the styling for an individual
     * heading level in a type-safe, fluent manner.
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the padding around the heading element.
         */
        private var padding = Padding.Builder()

        /**
         * The typography style applied to the heading text.
         */
        var textStyle = TextStyle()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override the padding and text style with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: Builder) {
            padding = builder.padding
            textStyle = builder.textStyle
        }

        /**
         * Includes the configuration from an existing [HeadingLevelStyle] object into this builder.
         *
         * This will include the padding and text style from the provided [style].
         *
         * @param style The [HeadingLevelStyle] instance whose configuration should be included.
         */
        fun include(style: HeadingLevelStyle) {
            padding.include(style.padding)
            textStyle = style.textStyle
        }

        /**
         * Configures the padding around the heading element.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Builds a new [HeadingLevelStyle] instance with the current configuration.
         *
         * @return A [HeadingLevelStyle] object with the configured padding and text style.
         */
        internal fun build() = HeadingLevelStyle(
            padding = padding.build(),
            textStyle = textStyle,
        )
    }
}
