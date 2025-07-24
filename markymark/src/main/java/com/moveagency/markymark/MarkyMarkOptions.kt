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

package com.moveagency.markymark

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import com.moveagency.markymark.annotator.DefaultMarkyMarkAnnotator
import com.moveagency.markymark.annotator.MarkyMarkAnnotator
import com.moveagency.markymark.composer.DefaultMarkyMarkComposer
import com.moveagency.markymark.composer.MarkyMarkComposer
import com.moveagency.markymark.model.ImmutableDataHolder
import com.moveagency.markymark.model.toImmutableDataHolder
import com.vladsch.flexmark.ext.autolink.AutolinkExtension
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughSubscriptExtension
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.parser.Parser.EXTENSIONS
import com.vladsch.flexmark.parser.Parser.ParserExtension
import com.vladsch.flexmark.util.data.MutableDataSet

/**
 * Configuration options for the MarkyMark markdown rendering system.
 *
 * This class encapsulates all the configuration needed to customize how markdown
 * content is parsed and rendered in the application. It provides options for
 * customizing the FlexMark parser, the composition of markdown elements, and
 * the annotation of inline text styles.
 *
 * @property flexmarkOptions Configuration options for the FlexMark parser. See [Parser.Builder] for more details.
 *   Use [createDefaultFlexmarkOptions] to get the default set of options, which includes all the necessary
 *   [ParserExtension]s for the supported markdown syntax. When providing custom options, it's recommended
 *   to build upon the defaults rather than starting from scratch.
 *
 * @property composer The composer responsible for rendering markdown elements as Jetpack Compose UI.
 *   The default implementation is [DefaultMarkyMarkComposer], but this can be customized to change
 *   how markdown elements are rendered.
 *
 * @property annotator The annotator responsible for applying text styles to inline markdown elements.
 *   The default implementation is [DefaultMarkyMarkAnnotator], but this can be customized to change
 *   how inline styles are applied to text.
 */
@Stable
data class MarkyMarkOptions(
    val flexmarkOptions: ImmutableDataHolder,
    val composer: MarkyMarkComposer,
    val annotator: MarkyMarkAnnotator,
)

/**
 * CompositionLocal containing the current [MarkyMarkOptions].
 *
 * This provides access to the markdown rendering configuration throughout the composition hierarchy.
 * The default value includes:
 * - Default FlexMark options from [createDefaultFlexmarkOptions]
 * - Default composer implementation [DefaultMarkyMarkComposer]
 * - Default annotator implementation [DefaultMarkyMarkAnnotator]
 *
 * Use this CompositionLocal to access the current options or provide custom options to a part
 * of the composition hierarchy.
 */
val LocalMarkyMarkOptions = compositionLocalOf {
    MarkyMarkOptions(
        flexmarkOptions = createDefaultFlexmarkOptions(),
        composer = DefaultMarkyMarkComposer(),
        annotator = DefaultMarkyMarkAnnotator(),
    )
}

/**
 * Creates a default configuration for the FlexMark markdown parser.
 *
 * This function creates a [MutableDataSet] with all the necessary [ParserExtension]s
 * enabled to support the markdown syntax features used by MarkyMark. The result is
 * converted to an [ImmutableDataHolder] to ensure thread safety and immutability.
 *
 * The following extensions are enabled by default:
 * - [AutolinkExtension] - Automatically converts URLs to clickable links
 *   [Documentation](https://github.com/vsch/flexmark-java/wiki/Extensions#autolink)
 * - [StrikethroughSubscriptExtension] - Supports strikethrough text with `~~text~~` and subscript with `~text~`
 *   [Documentation](https://github.com/vsch/flexmark-java/wiki/Extensions#gfm-strikethroughsubscript)
 * - [TaskListExtension] - Supports GitHub-style task lists with `- [ ]` and `- [x]`
 *   [Documentation](https://github.com/vsch/flexmark-java/wiki/Extensions#gfm-tasklist)
 * - [SuperscriptExtension] - Supports superscript text with `^text^`
 *   [Documentation](https://github.com/vsch/flexmark-java/wiki/Extensions#superscript)
 * - [TablesExtension] - Supports markdown tables
 *   [Documentation](https://github.com/vsch/flexmark-java/wiki/Extensions#tables)
 *
 * @return An [ImmutableDataHolder] containing the default FlexMark configuration.
 */
fun createDefaultFlexmarkOptions() = MutableDataSet().apply {
    set(
        AutolinkExtension.create(),
        StrikethroughSubscriptExtension.create(),
        TaskListExtension.create(),
        SuperscriptExtension.create(),
        TablesExtension.create(),
    )
}.toImmutableDataHolder()

/**
 * Extension function for adding multiple [ParserExtension]s to a [MutableDataSet].
 *
 * This utility function simplifies the process of adding multiple parser extensions
 * to a FlexMark configuration. It sets the [EXTENSIONS] key in the data set to a list
 * containing all the provided extensions.
 *
 * @param extensions The parser extensions to add to the configuration.
 * @return The [MutableDataSet] with the extensions added, allowing for method chaining.
 */
fun MutableDataSet.set(vararg extensions: ParserExtension) = set(
    EXTENSIONS,
    extensions.toList(),
)
