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

package com.moveagency.markymark.theme

import androidx.compose.runtime.Immutable

/**
 * Theming attributes used for the root level of the markdown content.
 *
 * This class defines styling properties that apply to the entire markdown content.
 *
 * @property screenPadding The padding applied to the entire markdown content.
 */
@Immutable
data class RootStyle(
    val screenPadding: Padding,
) {

    /**
     * Builder class for constructing instances of [RootStyle].
     */
    @MarkyMarkThemeBuilderMarker
    class RootBuilder {

        /**
         * Builder for configuring the padding applied to the entire markdown content.
         */
        private var padding: Padding.Builder = Padding.Builder()

        /**
         * Includes another [RootBuilder] instance's configuration into `this` builder.
         *
         * This will override the padding configuration with the one from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: RootBuilder) {
            padding = builder.padding
        }

        /**
         * Includes the configuration from an existing [RootStyle] object into this builder.
         *
         * This will include the screen padding from the provided [root].
         *
         * @param root The [RootStyle] instance whose configuration should be included.
         */
        fun include(root: RootStyle) {
            padding.include(root.screenPadding)
        }

        /**
         * Configures the padding applied to the entire markdown content.
         *
         * @param block A lambda to configure the [Padding.Builder].
         */
        fun padding(block: Padding.Builder.() -> Unit) = block(padding)

        /**
         * Builds a new [RootStyle] instance with the current configuration.
         *
         * @return A [RootStyle] object with the set properties.
         */
        internal fun build() = RootStyle(
            screenPadding = padding.build(),
        )
    }
}
