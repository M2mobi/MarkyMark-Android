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

@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.moveagency.markymark.theme

import com.moveagency.markymark.theme.RootStyle.RootBuilder

/**
 * Creates a [MarkyMarkTheme] using a builder pattern.
 *
 * This function provides a convenient way to create and configure a [MarkyMarkTheme]
 * instance using a DSL-style builder approach. It allows for fluent, type-safe
 * configuration of all theme components.
 *
 * @param block A lambda with [MarkyMarkThemeBuilder] as receiver that configures the theme.
 * @return A fully configured [MarkyMarkTheme] instance.
 */
fun markyMarkTheme(block: MarkyMarkThemeBuilder.() -> Unit): MarkyMarkTheme {
    val builder = MarkyMarkThemeBuilder()
    block(builder)
    return builder.build()
}

/**
 * Builder class for constructing instances of [MarkyMarkTheme].
 *
 * This builder provides methods to configure all aspects of a markdown theme
 * in a type-safe, fluent manner. It allows for incremental theme configuration
 * and composition of themes from multiple sources.
 */
@MarkyMarkThemeBuilderMarker
class MarkyMarkThemeBuilder {

    /**
     * Builder for configuring root-level styling properties.
     */
    private var root = RootBuilder()

    /**
     * Builder for configuring style components for markdown elements.
     */
    private var styles = MarkyMarkStyles.Builder()

    /**
     * Builder for configuring color schemes for markdown elements.
     */
    private var colors = MarkyMarkColors.Builder()

    /**
     * Includes another [MarkyMarkThemeBuilder] instance's configuration into `this` builder.
     *
     * This will override all builders with the ones from the provided [builder].
     *
     * @param builder The builder whose configuration should be included.
     */
    fun include(builder: MarkyMarkThemeBuilder) {
        root = builder.root
        styles = builder.styles
        colors = builder.colors
    }

    /**
     * Includes the configuration from an existing [MarkyMarkTheme] object into this builder.
     *
     * This will include all components from the provided [theme] into their
     * respective builders.
     *
     * @param theme The [MarkyMarkTheme] instance whose configuration should be included.
     */
    fun include(theme: MarkyMarkTheme) {
        root.include(theme.root)
        styles.include(theme.styles)
        colors.include(theme.colors)
    }

    /**
     * Configures root-level styling properties.
     *
     * @param block A lambda to configure the [RootBuilder].
     */
    fun root(block: RootBuilder.() -> Unit) = block(root)

    /**
     * Configures style components for markdown elements.
     *
     * @param block A lambda to configure the [MarkyMarkStyles.Builder].
     */
    fun styles(block: MarkyMarkStyles.Builder.() -> Unit) = block(styles)

    /**
     * Configures color schemes for markdown elements.
     *
     * @param block A lambda to configure the [MarkyMarkColors.Builder].
     */
    fun colors(block: MarkyMarkColors.Builder.() -> Unit) = block(colors)

    /**
     * Builds a new [MarkyMarkTheme] instance with the current configuration.
     *
     * @return A [MarkyMarkTheme] object with all the configured components.
     */
    internal fun build() = MarkyMarkTheme(
        root = root.build(),
        styles = styles.build(),
        colors = colors.build(),
    )
}
