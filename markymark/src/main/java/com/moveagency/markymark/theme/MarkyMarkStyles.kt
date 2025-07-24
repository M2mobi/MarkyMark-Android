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
 * Collection of style configurations for all markdown elements.
 *
 * This class aggregates all the style components needed to render a complete
 * markdown document, including both composable layout elements and annotated text elements.
 *
 * @property composable Styling configuration for composable layout elements like headings, images, paragraphs, etc.
 * @property annotated Styling configuration for annotated text elements like bold, italic, links, etc.
 */
@Immutable
data class MarkyMarkStyles(
    val composable: ComposableStyles,
    val annotated: AnnotatedStyles,
) {

    /**
     * Builder class for constructing instances of [MarkyMarkStyles].
     *
     * This builder provides methods to configure all aspects of markdown styling
     * in a type-safe, fluent manner. It allows for incremental style configuration
     * and composition of styles from multiple sources.
     */
    @MarkyMarkThemeBuilderMarker
    class Builder {

        /**
         * Builder for configuring composable layout elements.
         */
        private var composable = ComposableStyles.Builder()

        /**
         * Builder for configuring annotated text elements.
         */
        private var annotated = AnnotatedStyles.Builder()

        /**
         * Includes another [Builder] instance's configuration into `this` builder.
         *
         * This will override both the composable and annotated builders with the ones from the provided [builder].
         *
         * @param builder The builder whose configuration should be included.
         */
        fun include(builder: MarkyMarkStyles.Builder) {
            composable = builder.composable
            annotated = builder.annotated
        }

        /**
         * Includes the configuration from an existing [MarkyMarkStyles] object into this builder.
         *
         * This will include both the composable and annotated styles from the provided [styles]
         * into their respective builders.
         *
         * @param styles The [MarkyMarkStyles] instance whose configuration should be included.
         */
        fun include(styles: MarkyMarkStyles) {
            composable.include(styles.composable)
            annotated.include(styles.annotated)
        }

        /**
         * Configures composable layout elements.
         *
         * @param block A lambda to configure the [ComposableStyles.Builder].
         */
        fun composable(block: ComposableStyles.Builder.() -> Unit) = block(composable)

        /**
         * Configures annotated text elements.
         *
         * @param block A lambda to configure the [AnnotatedStyles.Builder].
         */
        fun annotated(block: AnnotatedStyles.Builder.() -> Unit) = block(annotated)

        /**
         * Builds a new [MarkyMarkStyles] instance with the current configuration.
         *
         * @return A [MarkyMarkStyles] object with all the configured style components.
         */
        internal fun build() = MarkyMarkStyles(
            composable = composable.build(),
            annotated = annotated.build(),
        )
    }
}
