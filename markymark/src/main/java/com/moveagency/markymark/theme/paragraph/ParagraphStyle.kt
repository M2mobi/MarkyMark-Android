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

package com.moveagency.markymark.theme.paragraph

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import com.moveagency.markymark.theme.MarkyMarkThemeBuilderMarker
import com.moveagency.markymark.theme.Padding

/**
 * Styling configuration for paragraph text in markdown.
 *
 * This class defines the visual appearance of paragraph text blocks,
 * including spacing and typography.
 *
 * @property padding The spacing around the paragraph text.
 * @property textStyle The typography style applied to the paragraph text.
 */
@Immutable
data class ParagraphStyle(
    val padding: Padding,
    val textStyle: TextStyle,
) {

    /**
     * Builder class for constructing instances of [ParagraphStyle].
     *
     * This builder provides methods to configure the styling for paragraph text
     * in a type-safe, fluent manner.
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring the padding around the paragraph text.
         */
        private var padding = Padding.Builder()

        /**
         * The typography style applied to the paragraph text.
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
         * Includes the configuration from an existing [ParagraphStyle] object into this builder.
         *
         * This will include the padding and text style from the provided [style].
         *
         * @param style The [ParagraphStyle] instance whose configuration should be included.
         */
        fun include(style: ParagraphStyle) {
            padding.include(style.padding)
            textStyle = style.textStyle
        }

        /**
         * Configures the padding around the paragraph text.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Builds a new [ParagraphStyle] instance with the current configuration.
         *
         * @return A [ParagraphStyle] object with the configured padding and text style.
         */
        internal fun build() = ParagraphStyle(
            padding = padding.build(),
            textStyle = textStyle,
        )
    }
}
